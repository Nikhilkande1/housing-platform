"use client";

import React, { useEffect, useState } from "react";
import DashboardLayout from "@/components/DashboardLayout";
import { motion } from "framer-motion";
import {
  Home, CreditCard, Calendar, TrendingUp,
  ArrowUpRight, Clock, CheckCircle, RefreshCw
} from "lucide-react";
import ScrollReveal from "@/components/effects/ScrollReveal";
import { apiCall } from "@/lib/auth";

interface StatItem {
  label: string;
  value: string;
  icon: any;
  change: string;
  color: string;
}

interface ProjectData {
  id: number;
  name: string;
  architecturalStyle: string;
  status: string;
  completionPercentage: number;
  budget: number;
}

interface BookingData {
  id: number;
  propertyTitle: string;
  status: string;
  totalAmount: number;
  createdAt: string;
}

const STYLE_IMAGES: Record<string, string> = {
  modern: "https://images.unsplash.com/photo-1613490493576-7fde63acd811?q=80&w=2071&auto=format&fit=crop",
  indian: "https://images.unsplash.com/photo-1600585154340-be6199f7a096?q=80&w=2070&auto=format&fit=crop",
  bali: "https://images.unsplash.com/photo-1540541338287-41700207dee6?q=80&w=2070&auto=format&fit=crop",
  euro: "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?q=80&w=2070&auto=format&fit=crop",
};

const STYLE_NAMES: Record<string, string> = {
  modern: "Modern Minimalist",
  indian: "Indian Traditional",
  bali: "Bali Resort",
  euro: "European Classic",
};

function formatPrice(n: number): string {
  if (n >= 10000000) return `₹${(n / 10000000).toFixed(1)} Cr`;
  if (n >= 100000) return `₹${(n / 100000).toFixed(1)}L`;
  return `₹${n.toLocaleString("en-IN")}`;
}

export default function UserDashboard() {
  const [stats, setStats] = useState<any>(null);
  const [projects, setProjects] = useState<ProjectData[]>([]);
  const [bookings, setBookings] = useState<BookingData[]>([]);
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

      // Fetch projects
      const projectsRes = await apiCall("/projects/me");
      if (projectsRes.ok) {
        const projectsData = await projectsRes.json();
        setProjects(projectsData);
      }

      // Fetch bookings
      const bookingsRes = await apiCall("/bookings/me");
      if (bookingsRes.ok) {
        const bookingsData = await bookingsRes.json();
        setBookings(bookingsData);
      }
    } catch (err) {
      console.error("Error fetching dashboard data", err);
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

  const statItems: StatItem[] = [
    {
      label: "Active Projects",
      value: stats ? String(stats.activeProjects) : "0",
      icon: Home,
      change: "Real-time track",
      color: "text-blue-500 bg-blue-500/10",
    },
    {
      label: "Total Bookings",
      value: stats ? String(stats.totalBookings) : "0",
      icon: Calendar,
      change: "Verified assets",
      color: "text-emerald-500 bg-emerald-500/10",
    },
    {
      label: "Amount Invested",
      value: stats && stats.totalPayments ? formatPrice(Number(stats.totalPayments)) : "₹0",
      icon: CreditCard,
      change: "Secure Vault payments",
      color: "text-amber-500 bg-amber-500/10",
    },
    {
      label: "Estimated ROI",
      value: stats && stats.estimatedRoi ? `${stats.estimatedRoi.toFixed(1)}%` : "12.5%",
      icon: TrendingUp,
      change: "Portfolio average",
      color: "text-accent bg-accent/10",
    },
  ];

  return (
    <DashboardLayout>
      <div className="space-y-10 max-w-7xl">
        {/* Header */}
        <ScrollReveal>
          <div className="flex flex-col md:flex-row md:items-center justify-between gap-4">
            <div className="space-y-2">
              <h1 className="text-white text-4xl font-bold tracking-tight">
                Welcome <span className="text-accent italic">back</span>.
              </h1>
              <p className="text-white/40">Here&apos;s an overview of your projects and bookings.</p>
            </div>
            <button
              onClick={handleRefresh}
              disabled={refreshing}
              className="self-start glass px-4 py-2 rounded-xl border border-white/10 hover:border-accent/30 text-white/70 hover:text-white text-xs font-bold uppercase tracking-widest flex items-center gap-2 transition-all"
            >
              <RefreshCw size={14} className={refreshing ? "animate-spin" : ""} />
              {refreshing ? "Refreshing..." : "Refresh"}
            </button>
          </div>
        </ScrollReveal>

        {loading ? (
          <div className="flex flex-col items-center justify-center py-20 space-y-4">
            <RefreshCw size={40} className="text-accent animate-spin" />
            <p className="text-white/40 text-sm">Syncing with secure ledger...</p>
          </div>
        ) : (
          <>
            {/* Stats Grid */}
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
                    <div className="text-white/20 text-xs mt-1">{stat.change}</div>
                  </motion.div>
                );
              })}
            </div>

            {/* Projects */}
            <div className="space-y-6">
              <div className="flex justify-between items-center">
                <h2 className="text-white text-2xl font-bold">My Projects</h2>
              </div>

              {projects.length === 0 ? (
                <div className="glass rounded-3xl border border-white/10 p-12 text-center space-y-4">
                  <div className="w-16 h-16 bg-white/5 rounded-2xl flex items-center justify-center text-white/30 mx-auto">
                    <Home size={30} />
                  </div>
                  <div className="space-y-1">
                    <h3 className="text-white font-bold text-lg">No active build projects</h3>
                    <p className="text-white/40 text-sm max-w-md mx-auto">Configure your dream villa using our state-of-the-art 3D AI builder to start tracking your construction journey.</p>
                  </div>
                </div>
              ) : (
                <div className="grid md:grid-cols-2 gap-6">
                  {projects.map((project) => {
                    const styleKey = project.architecturalStyle || "modern";
                    const image = STYLE_IMAGES[styleKey] || STYLE_IMAGES.modern;
                    const styleName = STYLE_NAMES[styleKey] || project.architecturalStyle;

                    return (
                      <div key={project.id} className="glass rounded-3xl border border-white/10 overflow-hidden group">
                        <div className="aspect-video relative overflow-hidden">
                          <img src={image} alt={project.name} className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-700" />
                          <div className="absolute inset-0 bg-gradient-to-t from-black via-black/30 to-transparent" />
                          <div className="absolute bottom-4 left-4 right-4">
                            <span className="bg-accent/20 backdrop-blur-md text-accent text-[10px] font-bold px-2 py-0.5 rounded-full border border-accent/20 uppercase tracking-widest">
                              {styleName}
                            </span>
                          </div>
                        </div>
                        <div className="p-6 space-y-4">
                          <div className="flex justify-between items-start">
                            <div>
                              <h3 className="text-white font-bold text-lg">{project.name}</h3>
                              <div className="flex items-center gap-2 mt-1">
                                <span className={`text-[10px] font-bold px-2 py-0.5 rounded-full border uppercase tracking-widest ${
                                  project.status === "COMPLETED"
                                    ? "bg-emerald-500/10 text-emerald-500 border-emerald-500/20"
                                    : project.status === "CONSTRUCTION"
                                    ? "bg-blue-500/10 text-blue-500 border-blue-500/20"
                                    : "bg-amber-500/10 text-amber-500 border-amber-500/20"
                                }`}>
                                  {project.status || "PLANNING"}
                                </span>
                              </div>
                            </div>
                            <div className="text-right">
                              <div className="text-accent font-bold">{formatPrice(project.budget || 0)}</div>
                              <div className="text-white/20 text-[10px] uppercase tracking-widest font-bold">Budget</div>
                            </div>
                          </div>
                          <div className="space-y-2">
                            <div className="flex justify-between text-xs">
                              <span className="text-white/40">Completion</span>
                              <span className="text-white font-bold">{project.completionPercentage || 0}%</span>
                            </div>
                            <div className="h-1.5 bg-white/10 rounded-full overflow-hidden">
                              <motion.div
                                initial={{ width: 0 }}
                                animate={{ width: `${project.completionPercentage || 0}%` }}
                                transition={{ duration: 1, delay: 0.5 }}
                                className="h-full bg-accent rounded-full"
                              />
                            </div>
                          </div>
                        </div>
                      </div>
                    );
                  })}
                </div>
              )}
            </div>

            {/* Recent Bookings */}
            <div className="space-y-6">
              <h2 className="text-white text-2xl font-bold">Recent Bookings</h2>
              {bookings.length === 0 ? (
                <div className="glass rounded-3xl border border-white/10 p-12 text-center space-y-4">
                  <div className="w-16 h-16 bg-white/5 rounded-2xl flex items-center justify-center text-white/30 mx-auto">
                    <Calendar size={30} />
                  </div>
                  <div className="space-y-1">
                    <h3 className="text-white font-bold text-lg">No bookings recorded</h3>
                    <p className="text-white/40 text-sm max-w-md mx-auto">Lease luxury properties or make deposits to see your secure transaction logs listed here.</p>
                  </div>
                </div>
              ) : (
                <div className="glass rounded-2xl border border-white/10 overflow-hidden">
                  <div className="divide-y divide-white/5">
                    {bookings.map((booking) => (
                      <div key={booking.id} className="p-6 flex flex-col md:flex-row md:items-center justify-between gap-4 hover:bg-white/5 transition-colors">
                        <div className="flex items-center gap-4">
                          <div className="w-10 h-10 bg-white/5 rounded-xl flex items-center justify-center text-white/40">
                            <Calendar size={18} />
                          </div>
                          <div>
                            <div className="text-white font-bold text-sm">{booking.propertyTitle}</div>
                            <div className="text-white/40 text-xs flex items-center gap-1 mt-0.5">
                              <Clock size={12} /> Booked on {new Date(booking.createdAt).toLocaleDateString()}
                            </div>
                          </div>
                        </div>
                        <div className="flex items-center gap-6">
                          <span className={`text-[10px] font-bold px-2 py-0.5 rounded-full border uppercase tracking-widest ${
                            booking.status === "CONFIRMED" || booking.status === "SUCCESS"
                              ? "bg-emerald-500/10 text-emerald-500 border-emerald-500/20"
                              : booking.status === "PENDING"
                              ? "bg-amber-500/10 text-amber-500 border-amber-500/20"
                              : "bg-red-500/10 text-red-500 border-red-500/20"
                          }`}>
                            {booking.status}
                          </span>
                          <span className="text-white font-bold text-sm">{formatPrice(booking.totalAmount)}</span>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              )}
            </div>
          </>
        )}
      </div>
    </DashboardLayout>
  );
}
