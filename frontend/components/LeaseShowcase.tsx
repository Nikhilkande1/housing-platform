"use client";

import React from "react";
import { motion } from "framer-motion";
import { Home, Briefcase, Users, Warehouse, TrendingUp, Calculator } from "lucide-react";

const LEASE_OPTIONS = [
  { 
    title: "Airbnb Hosting", 
    icon: Home, 
    roi: "12-18%", 
    desc: "Short-term luxury rentals with premium occupancy.",
    color: "bg-rose-500/10 text-rose-500 border-rose-500/20"
  },
  { 
    title: "Office Leasing", 
    icon: Briefcase, 
    roi: "8-10%", 
    desc: "Premium commercial spaces for scaling startups.",
    color: "bg-blue-500/10 text-blue-500 border-blue-500/20"
  },
  { 
    title: "Rental Apartments", 
    icon: Users, 
    roi: "5-7%", 
    desc: "Long-term residential leasing for steady cashflow.",
    color: "bg-emerald-500/10 text-emerald-500 border-emerald-500/20"
  },
  { 
    title: "Old Age Homes", 
    icon: Warehouse, 
    roi: "10-15%", 
    desc: "Specialized assisted living communities.",
    color: "bg-amber-500/10 text-amber-500 border-amber-500/20"
  },
];

export function LeaseShowcase() {
  return (
    <div className="w-full">
      <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-6">
        {LEASE_OPTIONS.map((option, idx) => {
          const Icon = option.icon;
          return (
            <motion.div
              key={option.title}
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.5, delay: idx * 0.1 }}
              viewport={{ once: true }}
              className="glass p-8 rounded-3xl border border-white/10 hover:border-accent/30 transition-all group"
            >
              <div className={`w-14 h-14 rounded-2xl flex items-center justify-center mb-6 border ${option.color}`}>
                <Icon size={28} />
              </div>
              
              <div className="flex justify-between items-start mb-4">
                <h3 className="text-white text-xl font-bold">{option.title}</h3>
                <div className="flex items-center gap-1 text-accent font-bold">
                  <TrendingUp size={14} />
                  {option.roi}
                </div>
              </div>
              
              <p className="text-white/40 text-sm leading-relaxed mb-6">
                {option.desc}
              </p>
              
              <button className="w-full py-3 rounded-xl border border-white/10 text-white/60 text-xs uppercase font-bold tracking-widest hover:bg-white hover:text-black transition-all flex items-center justify-center gap-2">
                <Calculator size={14} />
                Calculate ROI
              </button>
            </motion.div>
          );
        })}
      </div>

      <div className="mt-16 bg-gradient-to-r from-accent/20 via-transparent to-transparent p-12 rounded-[3rem] border border-accent/10 relative overflow-hidden group">
        <div className="relative z-10 grid md:grid-cols-2 gap-12 items-center">
          <div>
            <h3 className="text-white text-3xl font-bold mb-4 italic">
              "Turn your idle property into a high-yield asset."
            </h3>
            <p className="text-white/50 mb-8 max-w-md">
              Our end-to-end management ensures 95% occupancy rates and 
              hassle-free monthly income directly to your vault.
            </p>
            <div className="flex gap-4">
              <div className="flex flex-col">
                <span className="text-accent text-2xl font-bold tracking-tighter">₹4.2Cr+</span>
                <span className="text-white/20 text-[10px] font-bold uppercase tracking-widest">Payouts Sent</span>
              </div>
              <div className="w-[1px] h-10 bg-white/10" />
              <div className="flex flex-col">
                <span className="text-accent text-2xl font-bold tracking-tighter">150+</span>
                <span className="text-white/20 text-[10px] font-bold uppercase tracking-widest">Active Owners</span>
              </div>
            </div>
          </div>
          <div className="flex justify-end">
             <button className="bg-accent text-black font-bold px-10 py-5 rounded-2xl hover:scale-105 transition-transform shadow-[0_0_40px_rgba(197,168,128,0.2)]">
               List Your Property
             </button>
          </div>
        </div>
        
        {/* Animated Glow */}
        <div className="absolute top-1/2 left-0 -translate-y-1/2 w-64 h-64 bg-accent/20 blur-[120px] rounded-full group-hover:scale-150 transition-transform duration-1000" />
      </div>
    </div>
  );
}
