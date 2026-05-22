"use client";

import React, { useEffect, useState } from "react";
import DashboardLayout from "@/components/DashboardLayout";
import { motion, AnimatePresence } from "framer-motion";
import {
  Landmark, FileCheck, Clock, TrendingUp,
  ArrowUpRight, CheckCircle, XCircle, AlertCircle, RefreshCw
} from "lucide-react";
import { apiCall } from "@/lib/auth";

interface LoanApplicationData {
  id: number;
  userName: string;
  propertyTitle: string;
  amount: number;
  tenure: number;
  interestRate: number;
  emi: number;
  status: string;
  appliedAt: string;
}

const statusConfig: Record<string, { label: string; icon: React.ElementType; className: string }> = {
  PENDING: { label: "Pending", icon: Clock, className: "bg-amber-500/10 text-amber-500 border-amber-500/20" },
  APPROVED: { label: "Approved", icon: CheckCircle, className: "bg-emerald-500/10 text-emerald-500 border-emerald-500/20" },
  UNDER_REVIEW: { label: "Under Review", icon: AlertCircle, className: "bg-blue-500/10 text-blue-500 border-blue-500/20" },
  REJECTED: { label: "Rejected", icon: XCircle, className: "bg-red-500/10 text-red-500 border-red-500/20" },
  DISBURSED: { label: "Disbursed", icon: CheckCircle, className: "bg-teal-500/10 text-teal-500 border-teal-500/20" },
};

function formatPrice(n: number): string {
  if (n >= 10000000) return `₹${(n / 10000000).toFixed(1)} Cr`;
  if (n >= 100000) return `₹${(n / 100000).toFixed(1)}L`;
  return `₹${n.toLocaleString("en-IN")}`;
}

export default function BankDashboard() {
  const [stats, setStats] = useState<any>(null);
  const [bankProfile, setBankProfile] = useState<any>(null);
  const [applications, setApplications] = useState<LoanApplicationData[]>([]);
  const [loading, setLoading] = useState(true);
  const [refreshing, setRefreshing] = useState(false);
  const [actioningId, setActioningId] = useState<number | null>(null);

  const fetchData = async () => {
    try {
      // Fetch stats
      const statsRes = await apiCall("/dashboard/stats");
      if (statsRes.ok) {
        const statsData = await statsRes.json();
        setStats(statsData);
      }

      // Fetch bank profile
      const bankRes = await apiCall("/banks/me");
      if (bankRes.ok) {
        const profile = await bankRes.json();
        setBankProfile(profile);

        // Fetch loan applications for this bank
        const loansRes = await apiCall(`/loans/bank/${profile.id}`);
        if (loansRes.ok) {
          const loansData = await loansRes.json();
          setApplications(loansData);
        }
      }
    } catch (err) {
      console.error("Error fetching bank dashboard data", err);
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

  const handleUpdateStatus = async (id: number, status: string) => {
    setActioningId(id);
    try {
      const res = await apiCall(`/loans/${id}/status?status=${status}`, {
        method: "PUT"
      });
      if (res.ok) {
        fetchData();
      }
    } catch (err) {
      console.error("Error updating loan status", err);
    } finally {
      setActioningId(null);
    }
  };

  const statItems = [
    {
      label: "Total Applications",
      value: stats ? String(stats.totalBookings) : "0",
      icon: Landmark,
      color: "text-blue-500 bg-blue-500/10"
    },
    {
      label: "Pending Review",
      value: stats ? String(stats.pendingLoans) : "0",
      icon: Clock,
      color: "text-amber-500 bg-amber-500/10"
    },
    {
      label: "Disbursed Amount",
      value: stats && stats.totalPayments ? formatPrice(Number(stats.totalPayments)) : "₹0",
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
              Banking <span className="text-accent italic">Portal</span>.
            </h1>
            <p className="text-white/40">
              {bankProfile ? `${bankProfile.bankName} partner office` : "Review loan applications and manage disbursements."}
            </p>
          </div>
          <button
            onClick={handleRefresh}
            disabled={refreshing}
            className="glass px-4 py-2 rounded-xl border border-white/10 hover:border-accent/30 text-white/70 hover:text-white text-xs font-bold uppercase tracking-widest flex items-center gap-2 transition-all"
          >
            <RefreshCw size={14} className={refreshing ? "animate-spin" : ""} />
            {refreshing ? "Refreshing..." : "Refresh"}
          </button>
        </div>

        {loading ? (
          <div className="flex flex-col items-center justify-center py-20 space-y-4">
            <RefreshCw size={40} className="text-accent animate-spin" />
            <p className="text-white/40 text-sm">Syncing with banking pool...</p>
          </div>
        ) : (
          <>
            {/* Stats */}
            <div className="grid grid-cols-2 lg:grid-cols-3 gap-4">
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

            {/* Applications Table */}
            <div className="space-y-4">
              <h2 className="text-white text-xl font-bold">Loan Applications</h2>
              {applications.length === 0 ? (
                <div className="glass rounded-3xl border border-white/10 p-12 text-center space-y-4">
                  <div className="w-16 h-16 bg-white/5 rounded-2xl flex items-center justify-center text-white/30 mx-auto">
                    <Landmark size={30} />
                  </div>
                  <div className="space-y-1">
                    <h3 className="text-white font-bold text-lg">No loan applications</h3>
                    <p className="text-white/40 text-sm max-w-md mx-auto">Applications submitted by homebuilders seeking financing will populate here dynamically.</p>
                  </div>
                </div>
              ) : (
                <div className="glass rounded-2xl border border-white/10 overflow-hidden">
                  {/* Header */}
                  <div className="hidden md:grid grid-cols-7 gap-4 px-6 py-4 border-b border-white/5 text-white/30 text-[10px] font-bold uppercase tracking-widest">
                    <span className="col-span-2">Applicant & Property</span>
                    <span>Amount</span>
                    <span>Tenure</span>
                    <span>Rate</span>
                    <span>Status</span>
                    <span className="text-right">Actions</span>
                  </div>
                  {/* Rows */}
                  <div className="divide-y divide-white/5">
                    {applications.map((app) => {
                      const status = statusConfig[app.status] || statusConfig.PENDING;
                      const StatusIcon = status.icon;
                      return (
                        <div
                          key={app.id}
                          className="grid grid-cols-1 md:grid-cols-7 gap-2 md:gap-4 px-6 py-5 hover:bg-white/5 transition-colors items-center"
                        >
                          <div className="col-span-2">
                            <div className="text-white font-bold text-sm">{app.userName || "Applicant"}</div>
                            <div className="text-white/40 text-xs">{app.propertyTitle || "Tejas Nirman Project"}</div>
                          </div>
                          <div className="text-white font-bold text-sm">{formatPrice(app.amount)}</div>
                          <div className="text-white/60 text-sm">{app.tenure} yrs</div>
                          <div className="text-accent font-bold text-sm">{app.interestRate.toFixed(2)}%</div>
                          <div>
                            <span
                              className={`inline-flex items-center gap-1 text-[10px] font-bold px-2 py-1 rounded-full border uppercase tracking-widest ${status.className}`}
                            >
                              <StatusIcon size={10} /> {status.label}
                            </span>
                          </div>
                          <div className="flex items-center justify-end gap-2">
                            {app.status === "PENDING" ? (
                              <>
                                <button
                                  onClick={() => handleUpdateStatus(app.id, "REJECTED")}
                                  disabled={actioningId === app.id}
                                  className="px-3 py-1.5 rounded-lg border border-red-500/30 text-red-400 hover:bg-red-500/10 text-xs font-bold transition-all disabled:opacity-50"
                                >
                                  Reject
                                </button>
                                <button
                                  onClick={() => handleUpdateStatus(app.id, "APPROVED")}
                                  disabled={actioningId === app.id}
                                  className="px-3 py-1.5 rounded-lg bg-accent text-black hover:scale-105 text-xs font-bold transition-all disabled:opacity-50"
                                >
                                  Approve
                                </button>
                              </>
                            ) : (
                              <span className="text-white/20 text-xs">
                                Completed on {new Date(app.appliedAt).toLocaleDateString()}
                              </span>
                            )}
                          </div>
                        </div>
                      );
                    })}
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
