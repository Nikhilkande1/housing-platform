"use client";

import React, { useState } from "react";
import { motion } from "framer-motion";
import { MoveRight, Ruler, Wallet, Building2, ChevronRight } from "lucide-react";
import { cn } from "@/utils/cn";

const PROPERTY_TYPES = [
  { id: "villa", label: "Luxury Villa", icon: Building2 },
  { id: "apartment", label: "Smart Apartment", icon: Building2 },
  { id: "penthouse", label: "Penthouse", icon: Building2 },
];

export default function ConfiguratorPreview() {
  const [activeType, setActiveType] = useState("villa");
  const [budget, setBudget] = useState(50); // In Crores or Millions

  return (
    <div className="w-full">
      <div className="grid lg:grid-cols-12 gap-12 items-center">
        {/* Left: Interactive Controls */}
        <div className="lg:col-span-5 space-y-10">
          <div className="space-y-4">
            <h2 className="text-white text-4xl font-bold tracking-tight">
              Build Your <span className="text-accent italic">Dream</span>
            </h2>
            <p className="text-white/50 leading-relaxed">
              Our intelligent configurator analyzes your plot size, budget, 
              and style preferences to suggest the perfect architectural blueprint.
            </p>
          </div>

          <div className="space-y-8">
            {/* Property Type */}
            <div className="space-y-4">
              <label className="text-white/40 text-xs uppercase font-bold tracking-widest flex items-center gap-2">
                <Building2 size={14} className="text-accent" />
                Property Type
              </label>
              <div className="grid grid-cols-3 gap-3">
                {PROPERTY_TYPES.map((type) => {
                  const Icon = type.icon;
                  return (
                    <button
                      key={type.id}
                      onClick={() => setActiveType(type.id)}
                      className={cn(
                        "p-4 rounded-2xl border transition-all flex flex-col items-center gap-3",
                        activeType === type.id
                          ? "bg-accent/10 border-accent text-accent shadow-[0_0_20px_rgba(197,168,128,0.1)]"
                          : "bg-white/5 border-white/10 text-white/40 hover:bg-white/10 hover:border-white/20"
                      )}
                    >
                      <Icon size={20} />
                      <span className="text-[10px] font-bold uppercase tracking-wider">{type.label}</span>
                    </button>
                  );
                })}
              </div>
            </div>

            {/* Plot Size (Slider Placeholder) */}
            <div className="space-y-4">
              <div className="flex justify-between items-center">
                <label htmlFor="previewBudget" className="text-white/40 text-xs uppercase font-bold tracking-widest flex items-center gap-2">
                  <Ruler size={14} className="text-accent" />
                  Estimated Budget
                </label>
                <span className="text-accent font-bold">₹{budget}L - ₹{budget + 20}L</span>
              </div>
              <input 
                id="previewBudget"
                type="range" 
                min="10" 
                max="500" 
                value={budget}
                onChange={(e) => setBudget(parseInt(e.target.value))}
                className="w-full h-1.5 bg-white/10 rounded-full appearance-none cursor-pointer accent-accent" 
              />
              <div className="flex justify-between text-[10px] text-white/20 font-bold tracking-widest uppercase">
                <span>Starter</span>
                <span>Ultra Luxury</span>
              </div>
            </div>

            <button className="w-full bg-white text-black font-bold py-4 rounded-xl flex items-center justify-center gap-3 hover:bg-accent transition-colors group">
              Start Full Configuration
              <MoveRight className="group-hover:translate-x-1 transition-transform" />
            </button>
          </div>
        </div>

        {/* Right: Visual Preview Card */}
        <div className="lg:col-span-7 relative">
          <motion.div 
            key={activeType}
            initial={{ opacity: 0, scale: 0.95 }}
            animate={{ opacity: 1, scale: 1 }}
            transition={{ duration: 0.5 }}
            className="aspect-[16/10] bg-white/5 rounded-[2rem] border border-white/10 overflow-hidden relative group"
          >
            <img 
              src={activeType === 'villa' 
                ? "https://images.unsplash.com/photo-1600585154340-be6199f7a096?q=80&w=2070&auto=format&fit=crop" 
                : "https://images.unsplash.com/photo-1545324418-f1d3c5b5a272?q=80&w=1935&auto=format&fit=crop"
              } 
              alt="Preview"
              className="w-full h-full object-cover opacity-80 group-hover:scale-105 transition-transform duration-700"
            />
            <div className="absolute inset-0 bg-gradient-to-t from-black via-black/20 to-transparent" />
            
            {/* Overlay Tags */}
            <div className="absolute bottom-8 left-8 right-8 flex justify-between items-end">
              <div className="space-y-4">
                <div className="flex gap-2">
                  <span className="bg-accent/20 backdrop-blur-md text-accent text-[10px] font-bold px-3 py-1 rounded-full border border-accent/30 uppercase tracking-widest">
                    Recommended Style: Modern Minimalist
                  </span>
                </div>
                <div>
                  <h3 className="text-white text-3xl font-bold">Horizon Villa</h3>
                  <p className="text-white/60 text-sm">Estimated Completion: 8 Months</p>
                </div>
              </div>
              <div className="bg-white/10 backdrop-blur-xl p-4 rounded-2xl border border-white/20">
                <div className="text-white/40 text-[10px] font-bold uppercase tracking-widest mb-1">ROI Est.</div>
                <div className="text-accent text-xl font-bold">14.2%</div>
              </div>
            </div>
          </motion.div>

          {/* Decorative floating elements */}
          <div className="absolute -top-6 -right-6 bg-white/10 backdrop-blur-3xl p-4 rounded-2xl border border-white/20 transform rotate-6 hidden sm:block">
            <div className="flex items-center gap-3">
              <div className="w-8 h-8 bg-green-500/20 rounded-lg flex items-center justify-center text-green-400">
                <CheckCircle size={16} />
              </div>
              <span className="text-white font-bold text-sm tracking-tight">Smart Ready</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

function CheckCircle({ size, className }: { size: number, className?: string }) {
  return (
    <svg 
      xmlns="http://www.w3.org/2000/svg" 
      width={size} 
      height={size} 
      viewBox="0 0 24 24" 
      fill="none" 
      stroke="currentColor" 
      strokeWidth="3" 
      strokeLinecap="round" 
      strokeLinejoin="round" 
      className={className}
    >
      <path d="M20 6 9 17l-5-5" />
    </svg>
  );
}
