"use client";

import React, { useState, useEffect } from "react";
import Navbar from "@/components/Navbar";
import { motion } from "framer-motion";
import { 
  TrendingUp, DollarSign, PieChart, Users, 
  ArrowUpRight, Calculator, Plus, Star, MapPin
} from "lucide-react";
import { cn } from "@/utils/cn";
import BookingModal from "@/components/BookingModal";
import { apiCall } from "@/lib/auth";

const PROPERTY_STATS = [
  { label: "Active Listings", value: "3", icon: MapPin },
  { label: "Total Revenue", value: "₹45.2L", icon: DollarSign },
  { label: "Avg. ROI", value: "12.4%", icon: TrendingUp },
  { label: "Current Occupancy", value: "88%", icon: Users },
];

const LEASE_TYPES = [
  { id: "airbnb", label: "Airbnb Hosting", roi: "15-20%", color: "rose" },
  { id: "office", label: "Office / Commercial", roi: "8-12%", color: "blue" },
  { id: "rental", label: "Residential Rental", roi: "5-7%", color: "emerald" },
  { id: "oldage", label: "Old Age Home", roi: "10-14%", color: "amber" },
];

const FALLBACK_LEASES = [
  {
    id: 1,
    propertyId: 1,
    propertyTitle: "Grand Tejas Villa",
    location: "Lonavala, Pune",
    leaseType: "RENTAL",
    monthlyIncome: 120000,
    estimatedRoi: 12.5,
    occupancyRate: 90.0,
    imageUrl: "https://images.unsplash.com/photo-1613490493576-7fde63acd811?q=80&w=2071&auto=format&fit=crop",
    ownerName: "Tejas Developers",
    isActive: true
  },
  {
    id: 2,
    propertyId: 2,
    propertyTitle: "Tejas Commercial Plaza",
    location: "Hitec City, Hyderabad",
    leaseType: "OFFICE",
    monthlyIncome: 350000,
    estimatedRoi: 14.2,
    occupancyRate: 95.0,
    imageUrl: "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?q=80&w=2070&auto=format&fit=crop",
    ownerName: "Tejas Developers",
    isActive: true
  }
];

export default function LeasePage() {
  const [activeTab, setActiveTab] = useState("dashboard");
  const [isBookingOpen, setIsBookingOpen] = useState(false);
  const [selectedProperty, setSelectedProperty] = useState<{ id?: number, propertyId?: number, title: string, price: string }>({ title: "", price: "" });

  const [leases, setLeases] = useState<any[]>([]);
  const [isLoadingLeases, setIsLoadingLeases] = useState(true);

  useEffect(() => {
    async function fetchLeases() {
      try {
        const res = await apiCall("/leases");
        if (res.ok) {
          const data = await res.json();
          setLeases(data);
        }
      } catch (error) {
        console.error("Error fetching leases:", error);
      } finally {
        setIsLoadingLeases(false);
      }
    }
    fetchLeases();
  }, []);

  const openBooking = (id: number, propertyId: number, title: string, price: string) => {
    setSelectedProperty({ id, propertyId, title, price });
    setIsBookingOpen(true);
  };

  const displayedLeases = leases.length > 0 ? leases : FALLBACK_LEASES;

  return (
    <main className="min-h-screen bg-black pt-24 px-6 md:px-12 pb-20">
      <Navbar />

      <div className="max-w-7xl mx-auto space-y-12">
        {/* Header Section */}
        <div className="flex flex-col md:flex-row justify-between items-start md:items-end gap-6">
          <div className="space-y-4">
             <h1 className="text-white text-5xl font-bold tracking-tight">Owner <span className="text-accent italic">Dashboard</span>.</h1>
             <p className="text-white/40 text-lg">Manage your high-yield property portfolio and track real-time returns.</p>
          </div>
          <button className="bg-accent text-black font-bold px-8 py-4 rounded-2xl flex items-center gap-3 hover:scale-105 transition-transform shadow-[0_0_30px_rgba(197,168,128,0.2)] whitespace-nowrap">
            <Plus size={20} />
            List New Property
          </button>
        </div>

        {/* Stats Grid */}
        <div className="grid grid-cols-2 lg:grid-cols-4 gap-6">
          {PROPERTY_STATS.map((stat, idx) => {
             const Icon = stat.icon;
             return (
               <motion.div 
                 key={stat.label}
                 initial={{ opacity: 0, y: 20 }}
                 animate={{ opacity: 1, y: 0 }}
                 transition={{ delay: idx * 0.1 }}
                 className="glass p-8 rounded-3xl border border-white/10"
               >
                 <div className="w-10 h-10 bg-white/5 rounded-xl flex items-center justify-center text-accent mb-6 border border-white/5">
                   <Icon size={20} />
                 </div>
                 <div className="text-white/40 text-xs font-bold uppercase tracking-widest mb-1">{stat.label}</div>
                 <div className="text-white text-3xl font-bold tracking-tighter">{stat.value}</div>
               </motion.div>
             );
          })}
        </div>

        <div className="grid lg:grid-cols-12 gap-12">
            {/* Left: Project List */}
            <div className="lg:col-span-8 space-y-8">
               <div className="flex items-center justify-between">
                 <h2 className="text-white text-2xl font-bold">Active Assets</h2>
                 <div className="flex gap-2">
                    <button className="text-xs font-bold uppercase tracking-widest text-accent p-2">View All</button>
                 </div>
               </div>

               {isLoadingLeases ? (
                 <div className="text-white/40 text-sm italic font-medium p-8 glass rounded-3xl text-center">Loading live lease options...</div>
               ) : displayedLeases.map((lease) => (
                 <div key={lease.id} className="glass overflow-hidden rounded-[2.5rem] border border-white/10 group">
                    <div className="flex flex-col md:flex-row">
                       <div className="md:w-1/3 aspect-[4/3] md:aspect-auto overflow-hidden">
                          <img 
                            src={lease.imageUrl || "https://images.unsplash.com/photo-1613490493576-7fde63acd811?q=80&w=2071&auto=format&fit=crop"} 
                            className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-700"
                            alt={lease.propertyTitle}
                          />
                       </div>
                       <div className="md:w-2/3 p-8 flex flex-col justify-between space-y-6">
                          <div className="flex justify-between items-start">
                             <div>
                                <div className="flex items-center gap-2 mb-2">
                                   <span className={cn(
                                     "text-[10px] font-bold px-2 py-0.5 rounded border uppercase tracking-widest",
                                     lease.leaseType === "RENTAL" ? "bg-emerald-500/10 text-emerald-500 border-emerald-500/20" : "bg-blue-500/10 text-blue-500 border-blue-500/20"
                                   )}>
                                     {lease.leaseType}
                                   </span>
                                   <div className="flex items-center gap-1 text-amber-400">
                                      <Star size={12} fill="currentColor" />
                                      <span className="text-xs font-bold">4.9</span>
                                   </div>
                                </div>
                                <h3 className="text-white text-2xl font-bold">{lease.propertyTitle}</h3>
                                <p className="text-white/40 text-sm flex items-center gap-1 mt-1"><MapPin size={14} /> {lease.location}</p>
                             </div>
                             <div className="text-right">
                                <div className="text-accent text-2xl font-bold italic">₹ {(lease.monthlyIncome / 100000).toFixed(1)}L</div>
                                <div className="text-white/20 text-[10px] font-bold uppercase tracking-widest">Monthly Income</div>
                             </div>
                          </div>

                          <div className="grid grid-cols-3 gap-6 pt-6 border-t border-white/5">
                             <div>
                                <div className="text-white/20 text-[10px] font-bold uppercase tracking-widest mb-1">Occupancy</div>
                                <div className="text-white font-bold uppercase tracking-tighter">{lease.occupancyRate}%</div>
                             </div>
                             <div>
                                <div className="text-white/20 text-[10px] font-bold uppercase tracking-widest mb-1">Status</div>
                                <div className="text-emerald-500 font-bold uppercase tracking-tighter text-xs flex items-center gap-1">
                                  <div className="w-1.5 h-1.5 bg-emerald-500 rounded-full animate-pulse"/> {lease.isActive ? "Active" : "Pending"}
                                </div>
                             </div>
                             <div className="flex justify-end items-end">
                                <button 
                                  onClick={() => openBooking(lease.id, lease.propertyId, lease.propertyTitle, `₹ ${(lease.monthlyIncome / 100000).toFixed(1)}L`)}
                                  className="bg-white/5 hover:bg-accent hover:text-black p-3 rounded-xl border border-white/10 transition-all group/btn"
                                  aria-label="View property details and book"
                                >
                                   <ArrowUpRight size={18} className="group-hover/btn:scale-110" />
                                </button>
                             </div>
                          </div>
                       </div>
                    </div>
                 </div>
               ))}
            </div>

            <BookingModal 
              isOpen={isBookingOpen} 
              onClose={() => setIsBookingOpen(false)} 
              property={selectedProperty} 
            />

            {/* Right: ROI Calculator Tool */}
            <div className="lg:col-span-4 space-y-8">
               <div className="glass p-8 rounded-[2.5rem] border border-white/10 space-y-8 sticky top-32">
                  <div className="flex items-center gap-3">
                     <div className="w-12 h-12 bg-accent/10 rounded-2xl flex items-center justify-center text-accent">
                        <Calculator size={24} />
                     </div>
                     <div>
                        <div className="text-white font-bold">ROI Estimator</div>
                        <p className="text-white/40 text-xs">Analyze potential yield</p>
                     </div>
                  </div>

                  <div className="space-y-6">
                     <div className="space-y-3">
                        <label htmlFor="investmentValue" className="text-white/40 text-xs uppercase font-bold tracking-widest">Investment Value</label>
                        <input id="investmentValue" type="text" defaultValue="₹ 5.5 Cr" className="w-full bg-white/5 border border-white/10 rounded-xl p-4 text-white font-bold outline-none focus:border-accent transition-colors" />
                     </div>

                     <div className="space-y-3">
                        <label className="text-white/40 text-xs uppercase font-bold tracking-widest">Deployment Model</label>
                        <div className="grid grid-cols-2 gap-2">
                           {LEASE_TYPES.map(type => (
                              <button key={type.id} className="p-3 rounded-xl border border-white/5 bg-white/5 text-white/40 text-[10px] font-bold uppercase tracking-widest hover:border-accent/40 hover:text-white transition-all text-center">
                                 {type.label}
                              </button>
                           ))}
                        </div>
                     </div>

                     <div className="pt-6 border-t border-white/5 space-y-4">
                        <div className="flex justify-between items-center">
                           <span className="text-white/60 text-sm">Estimated Monthly</span>
                           <span className="text-white font-bold">₹ 4.2 L - 5.1 L</span>
                        </div>
                        <div className="flex justify-between items-center">
                           <span className="text-white/60 text-sm">Annual Yield %</span>
                           <span className="text-accent text-2xl font-bold tracking-tighter italic">13.2%</span>
                        </div>
                     </div>

                     <button className="w-full bg-accent text-black font-bold py-4 rounded-xl flex items-center justify-center gap-2 hover:scale-105 transition-transform">
                        Generate Detailed Report
                     </button>
                  </div>
               </div>
            </div>
        </div>
      </div>
    </main>
  );
}
