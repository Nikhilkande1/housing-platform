"use client";

import React, { useState, useEffect } from "react";
import Navbar from "@/components/Navbar";
import { motion } from "framer-motion";
import { 
  Landmark, CreditCard, Calculator, PieChart, 
  Calendar, Percent, ShieldCheck, ChevronRight,
  ArrowUpRight
} from "lucide-react";
import { cn } from "@/utils/cn";
import { apiCall } from "@/lib/auth";

const FALLBACK_BANKS = [
  { id: 1, name: "HDFC Home Loans", interestRate: 8.35, maxLoanAmount: 15000000, features: "Instant digital sanction, Nil prepayment charges", logoUrl: "https://logo.clearbit.com/hdfcbank.com" },
  { id: 2, name: "SBI Housing Finance", interestRate: 8.50, maxLoanAmount: 20000000, features: "Lowest interest rates, concession for women", logoUrl: "https://logo.clearbit.com/sbi.co.in" },
];

export default function FinancePage() {
  const [loanAmount, setLoanAmount] = useState(25000000); // 2.5 Cr
  const [tenure, setTenure] = useState(20); // Years
  const [interestRate, setInterestRate] = useState(8.5); // %

  const [banks, setBanks] = useState<any[]>([]);
  const [isLoadingBanks, setIsLoadingBanks] = useState(true);
  const [selectedBank, setSelectedBank] = useState<any>(null);
  const [applyStatus, setApplyStatus] = useState<{ type: 'success' | 'error', message: string } | null>(null);
  const [isApplying, setIsApplying] = useState(false);

  useEffect(() => {
    async function fetchBanks() {
      try {
        const res = await apiCall("/banks");
        if (res.ok) {
          const data = await res.json();
          setBanks(data);
          if (data.length > 0) {
            setSelectedBank(data[0]);
            setInterestRate(data[0].interestRate);
          }
        }
      } catch (error) {
        console.error("Error fetching banks:", error);
      } finally {
        setIsLoadingBanks(false);
      }
    }
    fetchBanks();
  }, []);

  const calculateEMI = () => {
    const r = interestRate / (12 * 100);
    const n = tenure * 12;
    const emi = (loanAmount * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
    return emi.toFixed(0);
  };

  const handleSelectBank = (bank: any) => {
    setSelectedBank(bank);
    setInterestRate(bank.interestRate);
  };

  const handleApply = async () => {
    const bankId = selectedBank?.id || 1;
    setIsApplying(true);
    setApplyStatus(null);
    try {
      const res = await apiCall("/loans", {
        method: "POST",
        body: JSON.stringify({
          bankId,
          amount: loanAmount,
          tenure
        })
      });

      if (res.ok) {
        const data = await res.json();
        setApplyStatus({
          type: "success",
          message: `Application submitted! Reference ID: #${data.id}. Status: PENDING.`
        });
      } else {
        const err = await res.json().catch(() => ({}));
        throw new Error(err.message || "Failed to submit loan application. Please verify your login status.");
      }
    } catch (err: any) {
      setApplyStatus({
        type: "error",
        message: err.message || "An unexpected error occurred during submission."
      });
    } finally {
      setIsApplying(false);
    }
  };

  const displayedBanks = banks.length > 0 ? banks : FALLBACK_BANKS;

  // Sync initial selection if banks were empty on mount but loaded later
  useEffect(() => {
    if (displayedBanks.length > 0 && !selectedBank) {
      setSelectedBank(displayedBanks[0]);
      setInterestRate(displayedBanks[0].interestRate);
    }
  }, [banks]);

  return (
    <main className="min-h-screen bg-black pt-24 px-6 md:px-12 pb-20">
      <Navbar />

      <div className="max-w-7xl mx-auto space-y-12">
        {/* Header Section */}
        <div className="space-y-4">
           <h1 className="text-white text-5xl font-bold tracking-tight">Banking & <span className="text-accent italic">Finance</span>.</h1>
           <p className="text-white/40 text-lg">Check loan eligibility, calculate EMIs, and connect with premier banking partners.</p>
        </div>

        <div className="grid lg:grid-cols-12 gap-12">
           {/* Left: EMI Calculator */}
           <div className="lg:col-span-8 space-y-8">
              <div className="glass p-10 rounded-[3rem] border border-white/10 space-y-12">
                 <div className="flex items-center gap-4">
                    <div className="w-12 h-12 bg-accent/10 rounded-2xl flex items-center justify-center text-accent">
                       <Calculator size={24} />
                    </div>
                    <h2 className="text-white text-2xl font-bold tracking-tight">Luxury Loan Calculator</h2>
                 </div>

                 <div className="space-y-12">
                    {/* Loan Amount Slider */}
                    <div className="space-y-6">
                       <div className="flex justify-between items-center">
                          <label htmlFor="loanAmount" className="text-white/40 text-xs uppercase font-bold tracking-widest">Principal Amount (₹)</label>
                          <span className="text-white text-2xl font-bold tracking-tighter italic">₹ {(loanAmount / 10000000).toFixed(2)} Cr</span>
                       </div>
                       <input 
                         id="loanAmount"
                         type="range" 
                         min="1000000" 
                         max="100000000" 
                         step="500000"
                         value={loanAmount}
                         onChange={(e) => setLoanAmount(parseInt(e.target.value))}
                         className="w-full h-2 bg-white/10 rounded-full appearance-none cursor-pointer accent-accent" 
                       />
                       <div className="flex justify-between text-[10px] text-white/20 font-bold tracking-widest uppercase">
                          <span>₹10L</span>
                          <span>₹10Cr</span>
                       </div>
                    </div>

                    <div className="grid md:grid-cols-2 gap-12">
                       {/* Tenure Slider */}
                       <div className="space-y-6">
                          <div className="flex justify-between items-center">
                             <label htmlFor="tenure" className="text-white/40 text-xs uppercase font-bold tracking-widest">Tenure (Years)</label>
                             <span className="text-white font-bold">{tenure} Yrs</span>
                          </div>
                          <input 
                            id="tenure"
                            type="range" 
                            min="1" 
                            max="30" 
                            value={tenure}
                            onChange={(e) => setTenure(parseInt(e.target.value))}
                            className="w-full h-2 bg-white/10 rounded-full appearance-none cursor-pointer accent-accent" 
                          />
                       </div>

                       {/* Interest Slider */}
                       <div className="space-y-6">
                          <div className="flex justify-between items-center">
                             <label htmlFor="interestRate" className="text-white/40 text-xs uppercase font-bold tracking-widest">Interest Rate (%)</label>
                             <span className="text-white font-bold">{interestRate}%</span>
                          </div>
                          <input 
                            id="interestRate"
                            type="range" 
                            min="5" 
                            max="15" 
                            step="0.1"
                            value={interestRate}
                            onChange={(e) => setInterestRate(parseFloat(e.target.value))}
                            className="w-full h-2 bg-white/10 rounded-full appearance-none cursor-pointer accent-accent" 
                          />
                       </div>
                    </div>
                 </div>

                 {applyStatus && (
                   <div className={cn(
                     "p-6 rounded-2xl border text-sm font-semibold tracking-tight",
                     applyStatus.type === "success" 
                       ? "bg-emerald-500/10 border-emerald-500/20 text-emerald-400" 
                       : "bg-red-500/10 border-red-500/20 text-red-400"
                   )}>
                     {applyStatus.message}
                   </div>
                 )}

                 {/* Results Banner */}
                 <div className="bg-accent p-8 rounded-3xl flex flex-col md:flex-row justify-between items-center gap-8 shadow-[0_0_50px_rgba(197,168,128,0.2)]">
                    <div className="text-center md:text-left">
                       <div className="text-black/60 text-xs font-bold uppercase tracking-widest mb-1">Estimated Monthly EMI</div>
                       <div className="text-black text-4xl font-bold tracking-tighter italic">₹ {parseInt(calculateEMI()).toLocaleString()} /mo</div>
                    </div>
                    <button 
                      disabled={isApplying}
                      onClick={handleApply}
                      className={cn(
                        "bg-black text-white font-bold px-10 py-4 rounded-2xl transition-all flex items-center gap-2",
                        isApplying ? "opacity-50 scale-95 cursor-not-allowed" : "hover:scale-105"
                      )}
                    >
                       {isApplying ? "Submitting Application..." : "Apply Now"} <ChevronRight size={18} />
                    </button>
                 </div>
              </div>
           </div>

           {/* Right: Banking Partners */}
           <div className="lg:col-span-4 space-y-8">
              <div className="glass p-8 rounded-[3rem] border border-white/10 space-y-8">
                 <div className="flex items-center gap-4">
                    <div className="w-10 h-10 bg-blue-500/10 rounded-xl flex items-center justify-center text-blue-500">
                       <Landmark size={20} />
                    </div>
                    <h3 className="text-white font-bold tracking-tight">Preferred Lenders</h3>
                 </div>

                 <div className="space-y-4">
                    {displayedBanks.map(bank => (
                       <div 
                         key={bank.id} 
                         onClick={() => handleSelectBank(bank)}
                         className={cn(
                           "flex justify-between items-center p-4 bg-white/5 rounded-2xl border transition-all cursor-pointer",
                           selectedBank?.id === bank.id ? "border-accent bg-accent/10" : "border-white/5 hover:border-accent/40 hover:bg-white/10"
                         )}
                       >
                          <div className="flex items-center gap-4">
                             <div className="w-10 h-10 bg-white rounded-lg flex items-center justify-center font-bold text-black text-[10px] overflow-hidden shrink-0">
                                {bank.logoUrl ? (
                                  <img src={bank.logoUrl} alt={bank.name} className="w-full h-full object-contain p-1" />
                                ) : (
                                  bank.name.substring(0, 4).toUpperCase()
                                )}
                             </div>
                             <div className="text-left">
                                <span className="text-white text-sm font-bold tracking-tight block">{bank.name}</span>
                                <span className="text-white/40 text-[10px] block truncate max-w-[180px]">{bank.features}</span>
                             </div>
                          </div>
                          <span className="text-accent text-xs font-bold shrink-0">{bank.interestRate}%*</span>
                       </div>
                    ))}
                 </div>

                 <div className="pt-6 border-t border-white/5 space-y-4">
                    <div className="flex gap-3">
                       <div className="w-10 h-10 bg-emerald-500/10 rounded-xl flex items-center justify-center text-emerald-500 shrink-0">
                          <ShieldCheck size={20} />
                       </div>
                       <div className="space-y-1">
                          <h4 className="text-white font-bold text-sm tracking-tight italic">Low-Doc Approval</h4>
                          <p className="text-white/40 text-[10px] leading-relaxed uppercase tracking-widest font-bold">Exclusive for Tejas Nirman Users</p>
                       </div>
                    </div>
                 </div>
              </div>

              <div className="bg-gradient-to-br from-[#111] to-[#000] p-8 rounded-[3rem] border border-white/10 shadow-2xl relative overflow-hidden group">
                 <PieChart className="absolute -right-6 -bottom-6 w-32 h-32 text-white/5 transform -rotate-12 group-hover:scale-110 transition-transform duration-700" />
                 <h4 className="text-white font-bold text-lg mb-2 italic">Tax Benefits Analysis</h4>
                 <p className="text-white/40 text-xs mb-6">Save up to ₹ 3.5 Lakhs annually via Sec 80C & Sec 24(b) under Indian luxury housing schemes.</p>
                 <button className="text-accent text-xs font-bold uppercase tracking-[0.2em] flex items-center gap-2 group-hover:gap-4 transition-all">
                    View Tax Plan <ArrowUpRight size={14} />
                 </button>
              </div>
           </div>
        </div>
      </div>
    </main>
  );
}
