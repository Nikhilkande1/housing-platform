"use client";

import React, { useState } from "react";
import Link from "next/link";
import { usePathname } from "next/navigation";
import { motion, AnimatePresence } from "framer-motion";
import {
  LayoutDashboard, Home, Building2, Store, Landmark,
  HelpCircle, LogOut, Settings, Bell, Menu, X,
  ChevronLeft, User as UserIcon
} from "lucide-react";
import { cn } from "@/utils/cn";
import { useAuth, getRoleDisplayName } from "@/lib/auth";

const SIDEBAR_ITEMS: Record<string, { label: string; href: string; icon: React.ElementType }[]> = {
  ROLE_USER: [
    { label: "Overview", href: "/dashboard/user", icon: LayoutDashboard },
    { label: "My Projects", href: "/dashboard/user#projects", icon: Home },
    { label: "Bookings", href: "/dashboard/user#bookings", icon: Building2 },
    { label: "Payments", href: "/dashboard/user#payments", icon: Landmark },
    { label: "Settings", href: "/dashboard/user#settings", icon: Settings },
  ],
  ROLE_OWNER: [
    { label: "Overview", href: "/dashboard/owner", icon: LayoutDashboard },
    { label: "My Properties", href: "/dashboard/owner#properties", icon: Building2 },
    { label: "Lease Income", href: "/dashboard/owner#income", icon: Landmark },
    { label: "Analytics", href: "/dashboard/owner#analytics", icon: Store },
    { label: "Settings", href: "/dashboard/owner#settings", icon: Settings },
  ],
  ROLE_VENDOR: [
    { label: "Overview", href: "/dashboard/vendor", icon: LayoutDashboard },
    { label: "Products", href: "/dashboard/vendor#products", icon: Store },
    { label: "Enquiries", href: "/dashboard/vendor#enquiries", icon: HelpCircle },
    { label: "Reviews", href: "/dashboard/vendor#reviews", icon: UserIcon },
    { label: "Settings", href: "/dashboard/vendor#settings", icon: Settings },
  ],
  ROLE_BANK: [
    { label: "Overview", href: "/dashboard/bank", icon: LayoutDashboard },
    { label: "Loan Requests", href: "/dashboard/bank#loans", icon: Landmark },
    { label: "Approvals", href: "/dashboard/bank#approvals", icon: Building2 },
    { label: "Disbursements", href: "/dashboard/bank#disbursements", icon: Store },
    { label: "Settings", href: "/dashboard/bank#settings", icon: Settings },
  ],
  ROLE_ENQUIRY: [
    { label: "Overview", href: "/dashboard/enquiry", icon: LayoutDashboard },
    { label: "Browse", href: "/dashboard/enquiry#browse", icon: Home },
    { label: "My Enquiries", href: "/dashboard/enquiry#enquiries", icon: HelpCircle },
    { label: "Settings", href: "/dashboard/enquiry#settings", icon: Settings },
  ],
};

export default function DashboardLayout({ children }: { children: React.ReactNode }) {
  const { user, logout } = useAuth();
  const pathname = usePathname();
  const [sidebarOpen, setSidebarOpen] = useState(false);

  const role = user?.role || "ROLE_USER";
  const items = SIDEBAR_ITEMS[role] || SIDEBAR_ITEMS.ROLE_USER;

  return (
    <div className="min-h-screen bg-black flex">
      {/* Mobile sidebar overlay */}
      <AnimatePresence>
        {sidebarOpen && (
          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            className="fixed inset-0 bg-black/60 z-40 lg:hidden"
            onClick={() => setSidebarOpen(false)}
          />
        )}
      </AnimatePresence>

      {/* Sidebar */}
      <aside
        className={cn(
          "fixed lg:sticky top-0 left-0 h-screen w-72 bg-[#0a0a0a] border-r border-white/5 flex flex-col z-50 transition-transform duration-300 lg:translate-x-0",
          sidebarOpen ? "translate-x-0" : "-translate-x-full"
        )}
      >
        {/* Logo */}
        <div className="p-6 border-b border-white/5">
          <Link href="/" className="flex items-center gap-2 group">
            <div className="w-10 h-10 bg-accent rounded-lg flex items-center justify-center transform group-hover:rotate-12 transition-transform">
              <span className="text-black font-bold text-xl">TN</span>
            </div>
            <span className="text-white text-xl font-semibold tracking-tight">
              Tejas <span className="text-accent">Nirman</span>
            </span>
          </Link>
        </div>

        {/* User info */}
        <div className="p-6 border-b border-white/5">
          <div className="flex items-center gap-3">
            <div className="w-12 h-12 bg-accent/20 rounded-xl flex items-center justify-center text-accent font-bold text-lg">
              {user?.name?.charAt(0)?.toUpperCase() || "U"}
            </div>
            <div className="flex-1 min-w-0">
              <div className="text-white font-bold text-sm truncate">{user?.name || "User"}</div>
              <div className="text-[10px] bg-accent/10 text-accent font-bold px-2 py-0.5 rounded-full border border-accent/20 uppercase tracking-widest inline-block mt-1">
                {getRoleDisplayName(role as any)}
              </div>
            </div>
          </div>
        </div>

        {/* Navigation */}
        <nav className="flex-1 p-4 space-y-1 overflow-y-auto">
          {items.map((item) => {
            const Icon = item.icon;
            const isActive = pathname === item.href.split("#")[0];
            return (
              <Link
                key={item.label}
                href={item.href}
                onClick={() => setSidebarOpen(false)}
                className={cn(
                  "flex items-center gap-3 px-4 py-3 rounded-xl transition-all text-sm font-medium",
                  isActive
                    ? "bg-accent/10 text-accent border border-accent/20"
                    : "text-white/40 hover:text-white hover:bg-white/5"
                )}
              >
                <Icon size={18} />
                {item.label}
              </Link>
            );
          })}
        </nav>

        {/* Bottom actions */}
        <div className="p-4 border-t border-white/5 space-y-1">
          <Link
            href="/"
            className="flex items-center gap-3 px-4 py-3 rounded-xl text-white/40 hover:text-white hover:bg-white/5 transition-all text-sm"
          >
            <ChevronLeft size={18} />
            Back to Home
          </Link>
          <button
            onClick={logout}
            className="flex items-center gap-3 px-4 py-3 rounded-xl text-white/40 hover:text-red-400 hover:bg-red-500/5 transition-all text-sm w-full text-left"
          >
            <LogOut size={18} />
            Sign Out
          </button>
        </div>
      </aside>

      {/* Main Content */}
      <main className="flex-1 min-h-screen">
        {/* Top bar */}
        <header className="sticky top-0 z-30 bg-black/60 backdrop-blur-md border-b border-white/5 px-6 py-4 flex items-center justify-between">
          <button
            onClick={() => setSidebarOpen(true)}
            className="lg:hidden text-white/60 hover:text-white transition-colors"
          >
            <Menu size={24} />
          </button>
          <div className="flex-1" />
          <div className="flex items-center gap-4">
            <button className="relative text-white/40 hover:text-white transition-colors p-2">
              <Bell size={20} />
              <span className="absolute top-1 right-1 w-2 h-2 bg-accent rounded-full" />
            </button>
          </div>
        </header>

        {/* Page content */}
        <div className="p-6 md:p-10">
          {children}
        </div>
      </main>
    </div>
  );
}
