"use client";

import React, { useState } from "react";
import { motion, AnimatePresence } from "framer-motion";
import { X, CreditCard, ShieldCheck, CheckCircle, ArrowRight, Wallet } from "lucide-react";
import { cn } from "@/utils/cn";
import { apiCall } from "@/lib/auth";

export default function BookingModal({ 
  isOpen, 
  onClose, 
  property 
}: { 
  isOpen: boolean, 
  onClose: () => void, 
  property: { id?: number, propertyId?: number, title: string, price: string } 
}) {
  const [step, setStep] = useState(1);
  const [isProcessing, setIsProcessing] = useState(false);
  const [error, setError] = useState<string | null>(null);

  // Default dates: tomorrow to tomorrow + 30 days
  const tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() + 1);
  const tomorrowStr = tomorrow.toISOString().split("T")[0];

  const nextMonth = new Date();
  nextMonth.setDate(nextMonth.getDate() + 31);
  const nextMonthStr = nextMonth.toISOString().split("T")[0];

  const [checkIn, setCheckIn] = useState(tomorrowStr);
  const [checkOut, setCheckOut] = useState(nextMonthStr);

  const handlePayment = async () => {
    setIsProcessing(true);
    setError(null);
    try {
      const pId = property.propertyId || property.id;
      if (!pId) {
        throw new Error("Property ID not found.");
      }

      // 1. Create booking
      const bookingRes = await apiCall("/bookings", {
        method: "POST",
        body: JSON.stringify({
          propertyId: pId,
          checkIn,
          checkOut
        })
      });

      if (!bookingRes.ok) {
        const errorData = await bookingRes.json().catch(() => ({}));
        throw new Error(errorData.message || "Failed to create booking. Please check your dates and login status.");
      }

      // 2. Create payment
      const paymentRes = await apiCall("/payments", {
        method: "POST",
        body: JSON.stringify({
          propertyId: pId,
          amount: new Intl.NumberFormat('en-IN').format(50000), // Standard amount
          gateway: "VAULT"
        })
      });

      if (!paymentRes.ok) {
        const errorData = await paymentRes.json().catch(() => ({}));
        throw new Error(errorData.message || "Booking created but deposit payment failed.");
      }

      setStep(3);
    } catch (err: any) {
      setError(err.message || "An unexpected error occurred during checkout.");
    } finally {
      setIsProcessing(false);
    }
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 z-[100] flex items-center justify-center p-6">
      <motion.div 
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        exit={{ opacity: 0 }}
        onClick={onClose}
        className="absolute inset-0 bg-black/80 backdrop-blur-sm"
      />
      
      <motion.div 
        initial={{ opacity: 0, scale: 0.9, y: 20 }}
        animate={{ opacity: 1, scale: 1, y: 0 }}
        exit={{ opacity: 0, scale: 0.9, y: 20 }}
        className="relative w-full max-w-lg glass-dark border border-white/10 rounded-[2.5rem] overflow-hidden shadow-2xl"
      >
        <button onClick={onClose} aria-label="Close" className="absolute top-6 right-6 text-white/40 hover:text-white transition-colors">
          <X size={24} />
        </button>
 
        <div className="p-10">
          <AnimatePresence mode="wait">
            {step === 1 && (
              <motion.div 
                key="step1"
                initial={{ opacity: 0, x: 20 }}
                animate={{ opacity: 1, x: 0 }}
                exit={{ opacity: 0, x: -20 }}
                className="space-y-6"
              >
                <div className="space-y-2">
                  <h2 className="text-white text-3xl font-bold tracking-tight">Confirm <span className="text-accent italic">Booking</span>.</h2>
                  <p className="text-white/40 text-sm">Secure your priority in the Tejas Nirman ecosystem.</p>
                </div>
 
                <div className="bg-white/5 rounded-2xl p-6 border border-white/5 space-y-4">
                  <div className="flex justify-between items-center">
                    <span className="text-white/60 text-sm">{property.title}</span>
                    <span className="text-white font-bold">{property.price} / mo</span>
                  </div>
                  <div className="flex justify-between items-center pt-4 border-t border-white/5">
                    <span className="text-white font-bold">Booking Deposit</span>
                    <span className="text-accent font-bold text-xl">₹ 50,000</span>
                  </div>
                </div>

                <div className="grid grid-cols-2 gap-4">
                  <div className="space-y-2">
                    <label className="text-white/40 text-xs font-bold uppercase tracking-widest">Check-In</label>
                    <input 
                      type="date" 
                      value={checkIn}
                      onChange={(e) => setCheckIn(e.target.value)}
                      className="w-full bg-white/5 border border-white/10 rounded-xl p-3 text-white text-sm outline-none focus:border-accent"
                    />
                  </div>
                  <div className="space-y-2">
                    <label className="text-white/40 text-xs font-bold uppercase tracking-widest">Check-Out</label>
                    <input 
                      type="date" 
                      value={checkOut}
                      onChange={(e) => setCheckOut(e.target.value)}
                      className="w-full bg-white/5 border border-white/10 rounded-xl p-3 text-white text-sm outline-none focus:border-accent"
                    />
                  </div>
                </div>
 
                <div className="space-y-4 pt-2">
                   <div className="flex items-center gap-3 text-white/40 text-xs">
                      <ShieldCheck size={16} className="text-accent" />
                      100% Refundable within 24 hours of site visit.
                   </div>
                   <button 
                     onClick={() => setStep(2)}
                     className="w-full bg-accent text-black font-bold py-5 rounded-2xl flex items-center justify-center gap-3 hover:scale-105 transition-transform"
                   >
                     Proceed to Payment
                     <ArrowRight size={20} />
                   </button>
                </div>
              </motion.div>
            )}
 
            {step === 2 && (
              <motion.div 
                key="step2"
                initial={{ opacity: 0, x: 20 }}
                animate={{ opacity: 1, x: 0 }}
                exit={{ opacity: 0, x: -20 }}
                className="space-y-6"
              >
                <div className="space-y-2">
                  <h2 className="text-white text-3xl font-bold tracking-tight">Secure <span className="text-accent italic">Checkout</span>.</h2>
                  <p className="text-white/40 text-sm">Integrate with Stripe/Razorpay (Mock Preview).</p>
                </div>
 
                <div className="space-y-6">
                    {error && (
                      <div className="bg-red-500/10 border border-red-500/20 text-red-400 p-4 rounded-xl text-sm">
                        {error}
                      </div>
                    )}

                    <div className="bg-white/5 p-4 rounded-xl border border-white/10 flex items-center justify-between opacity-50 grayscale cursor-not-allowed">
                       <div className="flex items-center gap-4">
                         <CreditCard className="text-white/40" />
                         <span className="text-white/40 font-bold">**** **** **** 4242</span>
                       </div>
                       <span className="text-white/20 text-[10px] font-bold uppercase tracking-widest">Expires 12/28</span>
                    </div>
                    
                    <button className="w-full bg-white/10 border border-white/10 text-white font-bold py-4 rounded-xl flex items-center justify-center gap-3 hover:bg-white/20 transition-all">
                       <Wallet size={18} />
                       Pay with Digital Vault
                    </button>
 
                    <div className="relative py-2">
                       <div className="absolute inset-0 flex items-center"><span className="w-full border-t border-white/5" /></div>
                       <div className="relative flex justify-center text-[10px] uppercase font-bold tracking-widest text-white/20"><span className="bg-[#0a0a0a] px-4">OR</span></div>
                    </div>
 
                    <button 
                      disabled={isProcessing}
                      onClick={handlePayment}
                      className={cn(
                        "w-full bg-white text-black font-bold py-5 rounded-2xl flex items-center justify-center gap-3 transition-all",
                        isProcessing ? "opacity-50 scale-95 cursor-not-allowed" : "hover:scale-105"
                      )}
                    >
                      {isProcessing ? "Verifying Transaction..." : "Complete Booking"}
                    </button>
                </div>
              </motion.div>
            )}
 
            {step === 3 && (
              <motion.div 
                key="step3"
                initial={{ opacity: 0, scale: 0.9 }}
                animate={{ opacity: 1, scale: 1 }}
                className="text-center space-y-8 py-10"
              >
                <div className="flex justify-center">
                  <div className="w-24 h-24 bg-accent/20 rounded-full flex items-center justify-center text-accent">
                    <CheckCircle size={56} />
                  </div>
                </div>
                <div className="space-y-3">
                  <h2 className="text-white text-4xl font-bold tracking-tight italic">Success!</h2>
                  <p className="text-white/40 text-sm max-w-sm mx-auto">
                    Your luxury booking is confirmed. A dedicated relationship manager will reach out within 15 minutes.
                  </p>
                </div>
                <button 
                  onClick={onClose}
                  className="bg-white/5 hover:bg-white/10 border border-white/10 text-white font-bold px-10 py-4 rounded-xl transition-all"
                >
                   Return to Dashboard
                </button>
              </motion.div>
            )}
          </AnimatePresence>
        </div>
      </motion.div>
    </div>
  );
}
