"use client";

import React, { useEffect, useState } from "react";
import DashboardLayout from "@/components/DashboardLayout";
import { motion } from "framer-motion";
import {
  Building2, TrendingUp, DollarSign, Users,
  ArrowUpRight, Plus, Star, MapPin, PieChart, RefreshCw
} from "lucide-react";
import { apiCall } from "@/lib/auth";
import Link from "next/link";

interface PropertyData {
  id: number;
  title: string;
  location: string;
  type: string;
  monthlyRent: number;
  occupancyRate: number;
  estimatedRoi: number;
  imageUrl: string;
}

const INCOME_DATA = [
  { month: "Jan", amount: 4.5 },
  { month: "Feb", amount: 5.2 },
  { month: "Mar", amount: 5.8 },
  { month: "Apr", amount: 6.9 },
  { month: "May", amount: 7.8 },
  { month: "Jun", amount: 8.4 },
];

function formatPrice(n: number): string {
  if (n >= 10000000) return `₹${(n / 10000000).toFixed(1)} Cr`;
  if (n >= 100000) return `₹${(n / 100000).toFixed(1)}L`;
  return `₹${n.toLocaleString("en-IN")}`;
}

export default function OwnerDashboard() {
  const [stats, setStats] = useState<any>(null);
  const [properties, setProperties] = useState<PropertyData[]>([]);
  const [loading, setLoading] = useState(true);
  const [refreshing, setRefreshing] = useState(false);

  const fetchData = async () => {
    try {
      // Fetch stats
      const statsRes = await apiCall("/dashboard/stats");
      if (statsRes.ok) {
        const statsData = await statsRes.json();
        setStats(statsData);
      }

      // Fetch properties
      const propertiesRes = await apiCall("/properties/me");
      if (propertiesRes.ok) {
        const propertiesData = await propertiesRes.json();
        setProperties(propertiesData);
      }
    } catch (err) {
      console.error("Error fetching owner dashboard data", err);
    } finally {
      setLoading(false);
      setRefreshing(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleRefresh = () => {
    setRefreshing(true);
    fetchData();
  };

  const maxAmount = Math.max(...INCOME_DATA.map(d => d.amount));

  const statItems = [
    {
      label: "Listed Properties",
      value: stats ? String(stats.properties) : "0",
      icon: Building2,
      color: "text-blue-500 bg-blue-500/10"
    },
    {
      label: "Total Earnings",
      value: stats && stats.totalPayments ? formatPrice(Number(stats.totalPayments)) : "₹0",
      icon: DollarSign,
      color: "text-emerald-500 bg-emerald-500/10"
    },
    {
      label: "Pending Enquiries",
      value: stats ? String(stats.pendingEnquiries) : "0",
      icon: Users,
      color: "text-amber-500 bg-amber-500/10"
    },
    {
      label: "Annual ROI",
      value: stats && stats.estimatedRoi ? `${stats.estimatedRoi.toFixed(1)}%` : "12.5%",
      icon: TrendingUp,
      color: "text-accent bg-accent/10"
    },
  ];

  return (
    <DashboardLayout>
      <div className="space-y-10 max-w-7xl">
        <div className="flex flex-col md:flex-row justify-between items-start md:items-end gap-4">
          <div className="space-y-2">
            <h1 className="text-white text-4xl font-bold tracking-tight">
              Owner <span className="text-accent italic">Dashboard</span>.
            </h1>
            <p className="text-white/40">Manage your high-yield property portfolio.</p>
          </div>
          <div className="flex gap-3">
            <button
              onClick={handleRefresh}
              disabled={refreshing}
              className="glass px-4 py-2 rounded-xl border border-white/10 hover:border-accent/30 text-white/70 hover:text-white text-xs font-bold uppercase tracking-widest flex items-center gap-2 transition-all"
            >
              <RefreshCw size={14} className={refreshing ? "animate-spin" : ""} />
              {refreshing ? "Refreshing..." : "Refresh"}
            </button>
            <Link
              href="/lease"
              className="bg-accent text-black font-bold px-6 py-3 rounded-xl flex items-center gap-2 hover:scale-105 transition-all text-sm"
            >
              <Plus size={18} /> List New Lease
            </Link>
          </div>
        </div>

        {loading ? (
          <div className="flex flex-col items-center justify-center py-20 space-y-4">
            <RefreshCw size={40} className="text-accent animate-spin" />
            <p className="text-white/40 text-sm">Syncing portfolio with digital vault...</p>
          </div>
        ) : (
          <>
            {/* Stats */}
            <div className="grid grid-cols-2 lg:grid-cols-4 gap-4">
              {statItems.map((stat, idx) => {
                const Icon = stat.icon;
                return (
                  <motion.div
                    key={stat.label}
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ delay: idx * 0.1 }}
                    className="glass p-6 rounded-2xl border border-white/10"
                  >
                    <div className={`w-10 h-10 rounded-xl flex items-center justify-center mb-4 ${stat.color}`}>
                      <Icon size={20} />
                    </div>
                    <div className="text-white/40 text-xs font-bold uppercase tracking-widest mb-1">{stat.label}</div>
                    <div className="text-white text-2xl font-bold tracking-tighter">{stat.value}</div>
                  </motion.div>
                );
              })}
            </div>

            <div className="grid lg:grid-cols-12 gap-8">
              {/* Income Chart */}
              <div className="lg:col-span-5 glass p-8 rounded-3xl border border-white/10 space-y-6">
                <div className="flex items-center gap-3">
                  <div className="w-10 h-10 bg-accent/10 rounded-xl flex items-center justify-center text-accent">
                    <PieChart size={20} />
                  </div>
                  <div>
                    <h3 className="text-white font-bold">Income Trend</h3>
                    <p className="text-white/40 text-xs">Last 6 months</p>
                  </div>
                </div>
                <div className="flex items-end gap-3 h-48">
                  {INCOME_DATA.map((d, i) => (
                    <div key={d.month} className="flex-1 flex flex-col items-center gap-2">
                      <span className="text-white/40 text-[10px] font-bold">₹{d.amount}L</span>
                      <motion.div
                        initial={{ height: 0 }}
                        animate={{ height: `${(d.amount / maxAmount) * 100}%` }}
                        transition={{ duration: 0.8, delay: i * 0.1 }}
                        className="w-full bg-accent/20 rounded-lg relative overflow-hidden"
                      >
                        <div className="absolute inset-0 bg-gradient-to-t from-accent/40 to-accent/10" />
                      </motion.div>
                      <span className="text-white/20 text-[10px] font-bold uppercase">{d.month}</span>
                    </div>
                  ))}
                </div>
              </div>

              {/* Property List */}
              <div className="lg:col-span-7 space-y-4">
                <h2 className="text-white text-xl font-bold">Active Properties</h2>
                {properties.length === 0 ? (
                  <div className="glass rounded-3xl border border-white/10 p-12 text-center space-y-4">
                    <div className="w-16 h-16 bg-white/5 rounded-2xl flex items-center justify-center text-white/30 mx-auto">
                      <Building2 size={30} />
                    </div>
                    <div className="space-y-1">
                      <h3 className="text-white font-bold text-lg">No listed properties</h3>
                      <p className="text-white/40 text-sm max-w-md mx-auto">List your luxury properties to start earning high rental income and tracking booking requests.</p>
                    </div>
                  </div>
                ) : (
                  properties.map((prop) => (
                    <div
                      key={prop.id}
                      className="glass rounded-2xl border border-white/10 overflow-hidden flex flex-col sm:flex-row group hover:border-accent/20 transition-all"
                    >
                      <div className="sm:w-48 aspect-video sm:aspect-auto overflow-hidden bg-white/5">
                        <img
                          src={prop.imageUrl || "https://images.unsplash.com/photo-1545324418-f1d3c5b5a272?q=80&w=1935&auto=format&fit=crop"}
                          alt={prop.title}
                          className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-700"
                        />
                      </div>
                      <div className="flex-1 p-5 flex flex-col justify-between gap-3">
                        <div className="flex justify-between items-start">
                          <div>
                            <div className="flex items-center gap-2 mb-1">
                              <span className="text-[10px] font-bold px-2 py-0.5 rounded-full border uppercase tracking-widest bg-emerald-500/10 text-emerald-500 border-emerald-500/20">
                                {prop.type || "VILLA"}
                              </span>
                              <div className="flex items-center gap-1 text-amber-400 text-xs">
                                <Star size={10} fill="currentColor" /> 4.9
                              </div>
                            </div>
                            <h3 className="text-white font-bold text-base">{prop.title}</h3>
                            <p className="text-white/40 text-xs flex items-center gap-1 mt-1">
                              <MapPin size={12} /> {prop.location}
                            </p>
                          </div>
                          <div className="text-right">
                            <div className="text-accent font-bold">{formatPrice(prop.monthlyRent || 0)}/mo</div>
                            <div className="text-white/20 text-[10px] uppercase tracking-widest font-bold mt-1">
                              Occupancy {prop.occupancyRate || 90}%
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  ))
                )}
              </div>
            </div>
          </>
        )}
      </div>
    </DashboardLayout>
  );
}
