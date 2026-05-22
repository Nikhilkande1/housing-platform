"use client";

import React, { useState, useEffect } from "react";
import Navbar from "@/components/Navbar";
import { motion, AnimatePresence } from "framer-motion";
import {
  CreditCard, ShieldCheck, CheckCircle, ArrowRight,
  Wallet, Clock, ArrowLeft, Receipt, Download
} from "lucide-react";
import { cn } from "@/utils/cn";
import { apiCall } from "@/lib/auth";

const FALLBACK_TRANSACTIONS = [
  { id: "TXN001", propertyTitle: "Grand Tejas Villa", amount: 50000, createdAt: "2024-12-15T10:00:00", paymentStatus: "SUCCESS", gateway: "Razorpay" },
  { id: "TXN002", propertyTitle: "Heritage Home Booking", amount: 35000, createdAt: "2024-12-10T11:30:00", paymentStatus: "SUCCESS", gateway: "Stripe" },
];

export default function PaymentPage() {
  const [step, setStep] = useState<"select" | "processing" | "success">("select");
  const [selectedGateway, setSelectedGateway] = useState<string | null>(null);

  const [transactions, setTransactions] = useState<any[]>([]);
  const [properties, setProperties] = useState<any[]>([]);
  const [selectedPropertyId, setSelectedPropertyId] = useState<string>("");
  const [customAmount, setCustomAmount] = useState<string>("50000");
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const fetchPayments = async () => {
    try {
      const res = await apiCall("/payments/me");
      if (res.ok) {
        const data = await res.json();
        setTransactions(data);
      }
    } catch (e) {
      console.error("Error fetching payments:", e);
    }
  };

  const fetchProperties = async () => {
    try {
      const res = await apiCall("/properties");
      if (res.ok) {
        const data = await res.json();
        setProperties(data);
      }
    } catch (e) {
      console.error("Error fetching properties:", e);
    }
  };

  useEffect(() => {
    async function loadData() {
      setIsLoading(true);
      await Promise.all([fetchPayments(), fetchProperties()]);
      setIsLoading(false);
    }
    loadData();
  }, []);

  const handlePayment = async () => {
    setError(null);
    setStep("processing");
    try {
      const payload: any = {
        amount: parseFloat(customAmount) || 50000,
        gateway: (selectedGateway || "Razorpay").toUpperCase()
      };
      if (selectedPropertyId) {
        payload.propertyId = parseInt(selectedPropertyId);
      }

      const res = await apiCall("/payments", {
        method: "POST",
        body: JSON.stringify(payload)
      });

      if (res.ok) {
        setStep("success");
        fetchPayments(); // Refresh list!
      } else {
        const data = await res.json().catch(() => ({}));
        throw new Error(data.message || "Failed to process payment. Please verify your login session.");
      }
    } catch (e: any) {
      setError(e.message || "An unexpected error occurred during payment.");
      setStep("select");
    }
  };

  const displayedTransactions = transactions.length > 0 ? transactions : FALLBACK_TRANSACTIONS;

  return (
    <main className="min-h-screen bg-black pt-24 px-6 md:px-12 pb-20">
      <Navbar />
      <div className="max-w-5xl mx-auto space-y-12">
        <div className="space-y-4">
          <h1 className="text-white text-5xl font-bold tracking-tight">
            Payment <span className="text-accent italic">Center</span>.
          </h1>
          <p className="text-white/40 text-lg">Secure payments via Razorpay & Stripe integration.</p>
        </div>

        <div className="grid lg:grid-cols-12 gap-8">
          {/* Payment Form */}
          <div className="lg:col-span-7">
            <div className="glass p-8 rounded-3xl border border-white/10 space-y-8">
              <AnimatePresence mode="wait">
                {step === "select" && (
                  <motion.div key="select" initial={{ opacity: 0 }} animate={{ opacity: 1 }} exit={{ opacity: 0 }} className="space-y-6">
                    <div className="space-y-2">
                      <h2 className="text-white text-2xl font-bold">Select Payment Method</h2>
                      <p className="text-white/40 text-sm">All transactions are encrypted and secure.</p>
                    </div>

                    {error && (
                      <div className="bg-red-500/10 border border-red-500/20 text-red-400 p-4 rounded-xl text-sm">
                        {error}
                      </div>
                    )}

                    <div className="grid grid-cols-2 gap-4">
                      <button
                        onClick={() => setSelectedGateway("razorpay")}
                        className={cn("p-5 rounded-2xl border flex flex-col items-center gap-2 transition-all text-center",
                          selectedGateway === "razorpay" ? "bg-blue-500/10 border-blue-500/30" : "bg-white/5 border-white/10 hover:border-white/20"
                        )}
                      >
                        <div className="w-12 h-12 bg-blue-500/20 rounded-xl flex items-center justify-center text-blue-400 font-bold text-sm">RP</div>
                        <div className="text-white font-bold text-sm">Razorpay</div>
                        <div className="text-white/40 text-[9px] leading-tight">UPI, Net Banking, Cards</div>
                      </button>

                      <button
                        onClick={() => setSelectedGateway("stripe")}
                        className={cn("p-5 rounded-2xl border flex flex-col items-center gap-2 transition-all text-center",
                          selectedGateway === "stripe" ? "bg-purple-500/10 border-purple-500/30" : "bg-white/5 border-white/10 hover:border-white/20"
                        )}
                      >
                        <div className="w-12 h-12 bg-purple-500/20 rounded-xl flex items-center justify-center text-purple-400 font-bold text-sm">S</div>
                        <div className="text-white font-bold text-sm">Stripe</div>
                        <div className="text-white/40 text-[9px] leading-tight">International Cards, Wallets</div>
                      </button>
                    </div>

                    {/* Setup targets */}
                    <div className="space-y-4 pt-4 border-t border-white/5">
                      <div className="space-y-2">
                        <label htmlFor="propertySelect" className="text-white/40 text-xs uppercase font-bold tracking-widest">Select Asset / Purpose</label>
                        <select 
                          id="propertySelect"
                          value={selectedPropertyId}
                          onChange={(e) => setSelectedPropertyId(e.target.value)}
                          className="w-full bg-white/5 border border-white/10 rounded-xl p-4 text-white text-sm outline-none focus:border-accent transition-all"
                        >
                          <option value="" className="bg-[#0a0a0a] text-white">General Vault Deposit</option>
                          {properties.map(p => (
                            <option key={p.id} value={p.id} className="bg-[#0a0a0a] text-white">
                              {p.title}
                            </option>
                          ))}
                        </select>
                      </div>

                      <div className="space-y-2">
                        <label htmlFor="amountInput" className="text-white/40 text-xs uppercase font-bold tracking-widest">Amount (₹)</label>
                        <input 
                          id="amountInput"
                          type="number" 
                          value={customAmount}
                          onChange={(e) => setCustomAmount(e.target.value)}
                          className="w-full bg-white/5 border border-white/10 rounded-xl p-4 text-white text-sm font-bold outline-none focus:border-accent transition-all" 
                          placeholder="50000"
                        />
                      </div>
                    </div>

                    {/* Mock Card Input */}
                    <div className="space-y-4 pt-4 border-t border-white/5">
                      <div className="space-y-2">
                        <label className="text-white/40 text-xs uppercase font-bold tracking-widest">Card Details</label>
                        <div className="relative">
                          <CreditCard className="absolute left-4 top-1/2 -translate-y-1/2 text-white/20" size={18} />
                          <input type="text" defaultValue="4242 4242 4242 4242" className="w-full bg-white/5 border border-white/10 rounded-xl py-4 pl-12 pr-4 text-white text-sm outline-none focus:border-accent transition-all" readOnly />
                        </div>
                      </div>
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-2">
                          <input type="text" defaultValue="12/28" className="w-full bg-white/5 border border-white/10 rounded-xl py-4 px-4 text-white text-sm outline-none focus:border-accent transition-all" readOnly />
                        </div>
                        <div className="space-y-2">
                          <input type="text" defaultValue="•••" className="w-full bg-white/5 border border-white/10 rounded-xl py-4 px-4 text-white text-sm outline-none focus:border-accent transition-all" readOnly />
                        </div>
                      </div>
                    </div>

                    <button
                      onClick={handlePayment}
                      disabled={!selectedGateway}
                      className="w-full bg-accent text-black font-bold py-5 rounded-2xl flex items-center justify-center gap-3 hover:scale-[1.02] transition-all disabled:opacity-30 disabled:scale-100 shadow-[0_0_30px_rgba(197,168,128,0.2)]"
                    >
                      Pay ₹ {Number(customAmount || 0).toLocaleString()} Securely <ArrowRight size={20} />
                    </button>

                    <div className="flex items-center justify-center gap-2 text-white/20 text-xs">
                      <ShieldCheck size={14} /> 256-bit SSL encrypted • PCI DSS compliant
                    </div>
                  </motion.div>
                )}

                {step === "processing" && (
                  <motion.div key="processing" initial={{ opacity: 0 }} animate={{ opacity: 1 }} exit={{ opacity: 0 }} className="py-20 text-center space-y-6">
                    <div className="w-16 h-16 border-4 border-accent/30 border-t-accent rounded-full animate-spin mx-auto" />
                    <div>
                      <h2 className="text-white text-2xl font-bold">Processing Payment...</h2>
                      <p className="text-white/40 text-sm mt-2">Verifying with {selectedGateway === "razorpay" ? "Razorpay" : "Stripe"} gateway</p>
                    </div>
                  </motion.div>
                )}

                {step === "success" && (
                  <motion.div key="success" initial={{ opacity: 0, scale: 0.9 }} animate={{ opacity: 1, scale: 1 }} className="py-16 text-center space-y-8">
                    <div className="w-24 h-24 bg-emerald-500/20 rounded-full flex items-center justify-center mx-auto">
                      <CheckCircle size={48} className="text-emerald-500" />
                    </div>
                    <div>
                      <h2 className="text-white text-3xl font-bold italic">Payment Successful!</h2>
                      <p className="text-white/40 text-sm mt-2 max-w-sm mx-auto">Transaction ID: TXN-{Date.now().toString(36).toUpperCase()}</p>
                    </div>
                    <div className="flex gap-4 justify-center">
                      <button className="bg-white/5 border border-white/10 text-white font-bold px-6 py-3 rounded-xl flex items-center gap-2 hover:bg-white/10 transition-all">
                        <Download size={16} /> Download Receipt
                      </button>
                      <button onClick={() => { setStep("select"); setSelectedGateway(null); }} className="bg-accent text-black font-bold px-6 py-3 rounded-xl flex items-center gap-2 hover:scale-105 transition-transform">
                        New Payment
                      </button>
                    </div>
                  </motion.div>
                )}
              </AnimatePresence>
            </div>
          </div>

          {/* Transaction History */}
          <div className="lg:col-span-5 space-y-4">
            <h2 className="text-white text-xl font-bold flex items-center gap-2">
              <Receipt size={20} className="text-accent" /> Transaction History
            </h2>
            <div className="space-y-3">
              {displayedTransactions.map((txn) => (
                <div key={txn.id || txn.transactionId} className="glass p-5 rounded-2xl border border-white/10 space-y-3 hover:border-accent/20 transition-all">
                  <div className="flex justify-between items-start">
                    <div>
                      <div className="text-white font-bold text-sm">{txn.propertyTitle || "Custom Deposit"}</div>
                      <div className="text-white/30 text-xs mt-0.5 flex items-center gap-1">
                        <Clock size={10} /> {txn.createdAt ? new Date(txn.createdAt).toLocaleDateString() : "Recently"}
                      </div>
                    </div>
                    <div className="text-right">
                      <div className="text-white font-bold">₹ {Number(txn.amount).toLocaleString()}</div>
                      <span className={`text-[10px] font-bold px-2 py-0.5 rounded-full border uppercase tracking-widest ${txn.paymentStatus === "SUCCESS" || txn.paymentStatus === "SUCCESS" ? "bg-emerald-500/10 text-emerald-500 border-emerald-500/20" : "bg-amber-500/10 text-amber-500 border-amber-500/20"}`}>
                        {txn.paymentStatus}
                      </span>
                    </div>
                  </div>
                  <div className="flex justify-between items-center text-xs text-white/20">
                    <span>{txn.transactionId || txn.id}</span>
                    <span>via {txn.gateway}</span>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </main>
  );
}
