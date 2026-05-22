"use client";

import React from "react";
import { motion } from "framer-motion";
import { ArrowRight, Play, LayoutGrid, CheckCircle } from "lucide-react";

export default function Hero() {
  return (
    <section className="relative min-h-screen flex items-center pt-20 overflow-hidden bg-black">
      {/* Background with Gradient Overlay */}
      <div 
        className="absolute inset-0 z-0 bg-cover bg-center bg-no-repeat opacity-60 scale-105 transition-transform duration-1000 ease-out" 
        style={{ 
          backgroundImage: `url('https://images.unsplash.com/photo-1613490493576-7fde63acd811?q=80&w=2071&auto=format&fit=crop')`,
        }}
      />
      <div className="absolute inset-0 z-10 bg-gradient-to-tr from-black via-black/80 to-transparent" />
      <div className="absolute inset-0 z-10 bg-gradient-to-b from-transparent via-transparent to-black" />

      {/* Content Container */}
      <div className="max-w-7xl mx-auto px-6 relative z-20 w-full grid md:grid-cols-2 gap-12 items-center">
        <motion.div
          initial={{ opacity: 0, x: -50 }}
          animate={{ opacity: 1, x: 0 }}
          transition={{ duration: 1, ease: "easeOut" }}
        >
          <div className="flex items-center gap-2 mb-6">
            <span className="h-[1px] w-8 bg-accent" />
            <span className="text-accent uppercase tracking-[0.3em] text-xs font-bold">
              Tejas Nirman • Luxury Real Estate Ecosystem
            </span>
          </div>

          <h1 className="text-white text-5xl md:text-7xl font-display font-bold leading-[1.1] mb-6">
            Architecting Your <br />
            <span className="text-gradient">Premier Future</span>
          </h1>

          <p className="text-white/70 text-lg md:text-xl max-w-xl mb-10 leading-relaxed">
            The world's first all-in-one property platform. Build ultra-luxury homes, 
            lease properties for passive income, and connect with global design curators.
          </p>

          <div className="flex flex-wrap gap-5">
            <button className="group bg-accent hover:bg-accent/90 text-black font-bold px-8 py-4 rounded-xl flex items-center gap-3 transition-all transform hover:scale-105 shadow-[0_0_30px_rgba(197,168,128,0.4)]">
              Build Your Home
              <ArrowRight className="group-hover:translate-x-1 transition-transform" />
            </button>
            <button className="bg-white/5 hover:bg-white/10 backdrop-blur-md text-white border border-white/20 font-bold px-8 py-4 rounded-xl flex items-center gap-3 transition-all">
              Watch Experience
              <div className="w-10 h-10 bg-white text-black rounded-full flex items-center justify-center translate-x-3">
                <Play fill="currentColor" size={18} />
              </div>
            </button>
          </div>

          {/* Quick Stats */}
          <div className="grid grid-cols-3 gap-8 mt-16 pt-12 border-t border-white/10">
            <div>
              <div className="text-white text-3xl font-bold mb-1">500+</div>
              <div className="text-white/40 text-xs uppercase tracking-widest font-bold">Luxury Designs</div>
            </div>
            <div>
              <div className="text-white text-3xl font-bold mb-1">12%</div>
              <div className="text-white/40 text-xs uppercase tracking-widest font-bold">Avg. Rental ROI</div>
            </div>
            <div>
              <div className="text-white text-3xl font-bold mb-1">85+</div>
              <div className="text-white/40 text-xs uppercase tracking-widest font-bold">Global Vendors</div>
            </div>
          </div>
        </motion.div>

        {/* Floating Card Design (Visual Element) */}
        <motion.div
          initial={{ opacity: 0, scale: 0.8, y: 50 }}
          animate={{ opacity: 1, scale: 1, y: 0 }}
          transition={{ duration: 1.2, ease: "easeOut", delay: 0.2 }}
          className="relative hidden md:block"
        >
          <div className="glass-dark p-8 rounded-3xl border border-white/20 shadow-2xl relative z-10">
            <div className="flex items-center justify-between mb-8">
              <div className="flex items-center gap-3">
                <div className="w-12 h-12 bg-accent/20 rounded-2xl flex items-center justify-center text-accent">
                  <LayoutGrid />
                </div>
                <div>
                  <h3 className="text-white font-bold">Configurator V2</h3>
                  <p className="text-white/40 text-xs">A.I. Design Assistant</p>
                </div>
              </div>
              <div className="text-accent flex items-center gap-1 text-sm font-bold">
                <span className="w-2 h-2 bg-accent rounded-full animate-pulse" />
                Live
              </div>
            </div>

            <div className="space-y-6">
              <div className="bg-white/5 rounded-2xl p-4 border border-white/10">
                <div className="flex justify-between text-xs text-white/40 uppercase font-bold mb-2 tracking-widest">
                  Style Selection
                  <span className="text-accent italic tracking-normal lowercase">Modern Minimalist</span>
                </div>
                <div className="h-1.5 w-full bg-white/10 rounded-full overflow-hidden">
                  <motion.div 
                    initial={{ width: 0 }}
                    animate={{ width: "85%" }}
                    transition={{ duration: 1.5, delay: 1 }}
                    className="h-full bg-accent" 
                  />
                </div>
              </div>

              <div className="flex items-center gap-4">
                <div className="flex -space-x-3">
                  {[1, 2, 3, 4].map((i) => (
                    <div key={i} className="w-10 h-10 border-2 border-black rounded-full overflow-hidden bg-gray-800">
                      <img src={`https://i.pravatar.cc/100?u=${i}`} alt="User" />
                    </div>
                  ))}
                </div>
                <div className="text-white/60 text-sm">
                  <span className="text-white font-bold">1.2k+</span> active builders
                </div>
              </div>
              
              <div className="pt-4 flex gap-4">
                <div className="flex items-center gap-2 text-white/80 text-sm">
                  <CheckCircle size={16} className="text-accent" />
                  Grievance-free
                </div>
                <div className="flex items-center gap-2 text-white/80 text-sm">
                  <CheckCircle size={16} className="text-accent" />
                  Bank-Ready
                </div>
              </div>
            </div>
          </div>

          {/* Decorative Elements */}
          <div className="absolute -top-10 -right-10 w-40 h-40 bg-accent/20 blur-[100px] rounded-full" />
          <div className="absolute -bottom-10 -left-10 w-60 h-60 bg-blue-500/10 blur-[120px] rounded-full" />
        </motion.div>
      </div>

      {/* Bottom Scroll Indicator */}
      <motion.div 
        animate={{ y: [0, 10, 0] }}
        transition={{ repeat: Infinity, duration: 2 }}
        className="absolute bottom-10 left-1/2 -translate-x-1/2 z-20 flex flex-col items-center gap-2 text-white/40"
      >
        <span className="text-[10px] uppercase font-bold tracking-[0.4em]">Discover More</span>
        <div className="w-[1px] h-12 bg-gradient-to-b from-accent/50 to-transparent" />
      </motion.div>
    </section>
  );
}
