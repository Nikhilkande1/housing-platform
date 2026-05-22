"use client";

import React, { useState, useEffect } from "react";
import Link from "next/link";
import { usePathname } from "next/navigation";
import { motion, AnimatePresence } from "framer-motion";
import { Menu, X, User, ChevronDown, LogOut, LayoutDashboard } from "lucide-react";
import { cn } from "@/utils/cn";
import { useAuth, getRoleDashboardPath, getRoleDisplayName } from "@/lib/auth";

const NAV_LINKS = [
  { name: "Build Home", href: "/build" },
  { name: "Lease Property", href: "/lease" },
  { name: "Marketplace", href: "/marketplace" },
  { name: "Finance", href: "/finance" },
];

export default function Navbar() {
  const [isScrolled, setIsScrolled] = useState(false);
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
  const [userMenuOpen, setUserMenuOpen] = useState(false);
  const { user, isAuthenticated, logout } = useAuth();
  const pathname = usePathname();

  useEffect(() => {
    const handleScroll = () => {
      setIsScrolled(window.scrollY > 20);
    };
    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  // Close user menu on click outside
  useEffect(() => {
    const handleClick = () => setUserMenuOpen(false);
    if (userMenuOpen) {
      document.addEventListener("click", handleClick);
      return () => document.removeEventListener("click", handleClick);
    }
  }, [userMenuOpen]);

  return (
    <nav
      className={cn(
        "fixed top-0 left-0 right-0 z-50 transition-all duration-300 px-6 py-4",
        isScrolled
          ? "bg-black/60 backdrop-blur-md border-b border-white/10 py-3"
          : "bg-transparent"
      )}
    >
      <div className="max-w-7xl mx-auto flex items-center justify-between">
        {/* Logo */}
        <Link href="/" className="flex items-center gap-2 group">
          <div className="w-10 h-10 bg-accent rounded-lg flex items-center justify-center transform group-hover:rotate-12 transition-transform">
            <span className="text-black font-bold text-xl">TN</span>
          </div>
          <span className="text-white font-display text-2xl font-semibold tracking-tight">
            Tejas <span className="text-accent">Nirman</span>
          </span>
        </Link>

        {/* Desktop Navigation */}
        <div className="hidden md:flex items-center gap-8">
          {NAV_LINKS.map((link) => (
            <Link
              key={link.name}
              href={link.href}
              className={cn(
                "text-sm font-medium tracking-wide uppercase transition-colors",
                pathname === link.href
                  ? "text-accent"
                  : "text-white/80 hover:text-accent"
              )}
            >
              {link.name}
            </Link>
          ))}
        </div>

        {/* Action Buttons */}
        <div className="hidden md:flex items-center gap-4">
          {isAuthenticated && user ? (
            <div className="relative">
              <button
                onClick={(e) => {
                  e.stopPropagation();
                  setUserMenuOpen(!userMenuOpen);
                }}
                className="flex items-center gap-3 text-white/90 hover:text-white border border-white/20 px-4 py-2 rounded-full hover:bg-white/10 transition-all"
              >
                <div className="w-7 h-7 bg-accent rounded-full flex items-center justify-center text-black text-xs font-bold">
                  {user.name.charAt(0).toUpperCase()}
                </div>
                <span className="text-sm font-medium">{user.name}</span>
                <ChevronDown size={14} className={cn("transition-transform", userMenuOpen && "rotate-180")} />
              </button>

              <AnimatePresence>
                {userMenuOpen && (
                  <motion.div
                    initial={{ opacity: 0, y: 8, scale: 0.95 }}
                    animate={{ opacity: 1, y: 0, scale: 1 }}
                    exit={{ opacity: 0, y: 8, scale: 0.95 }}
                    className="absolute right-0 top-full mt-2 w-64 glass-dark rounded-2xl border border-white/10 p-2 shadow-2xl"
                  >
                    <div className="px-4 py-3 border-b border-white/5">
                      <div className="text-white font-bold text-sm">{user.name}</div>
                      <div className="text-white/40 text-xs">{user.email}</div>
                      <div className="mt-1">
                        <span className="text-[10px] bg-accent/20 text-accent font-bold px-2 py-0.5 rounded-full border border-accent/20 uppercase tracking-widest">
                          {getRoleDisplayName(user.role)}
                        </span>
                      </div>
                    </div>
                    <div className="py-1">
                      <Link
                        href={getRoleDashboardPath(user.role)}
                        className="flex items-center gap-3 px-4 py-3 text-white/70 hover:text-white hover:bg-white/5 rounded-xl transition-all text-sm"
                      >
                        <LayoutDashboard size={16} />
                        Dashboard
                      </Link>
                      <button
                        onClick={() => {
                          logout();
                          setUserMenuOpen(false);
                        }}
                        className="flex items-center gap-3 px-4 py-3 text-white/70 hover:text-red-400 hover:bg-red-500/5 rounded-xl transition-all text-sm w-full text-left"
                      >
                        <LogOut size={16} />
                        Sign Out
                      </button>
                    </div>
                  </motion.div>
                )}
              </AnimatePresence>
            </div>
          ) : (
            <>
              <Link
                href="/auth/login"
                className="text-white/90 hover:text-white flex items-center gap-2 text-sm font-medium border border-white/20 px-4 py-2 rounded-full hover:bg-white/10 transition-all"
              >
                <User size={18} />
                Login
              </Link>
              <Link
                href="/auth/register"
                className="bg-accent text-black font-semibold text-sm px-6 py-2 rounded-full hover:bg-accent/90 transition-all shadow-[0_0_20px_rgba(197,168,128,0.3)]"
              >
                Get Started
              </Link>
            </>
          )}
        </div>

        {/* Mobile Menu Toggle */}
        <button
          className="md:hidden text-white"
          onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
        >
          {mobileMenuOpen ? <X size={28} /> : <Menu size={28} />}
        </button>
      </div>

      {/* Mobile Menu */}
      <AnimatePresence>
        {mobileMenuOpen && (
          <motion.div
            initial={{ opacity: 0, y: -20 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -20 }}
            className="absolute top-full left-0 right-0 bg-black/95 backdrop-blur-xl border-b border-white/10 p-6 flex flex-col gap-6 md:hidden"
          >
            {NAV_LINKS.map((link) => (
              <Link
                key={link.name}
                href={link.href}
                onClick={() => setMobileMenuOpen(false)}
                className={cn(
                  "text-lg font-medium",
                  pathname === link.href ? "text-accent" : "text-white"
                )}
              >
                {link.name}
              </Link>
            ))}
            <div className="flex flex-col gap-4 pt-4 border-t border-white/10">
              {isAuthenticated && user ? (
                <>
                  <div className="flex items-center gap-3 mb-2">
                    <div className="w-10 h-10 bg-accent rounded-full flex items-center justify-center text-black font-bold">
                      {user.name.charAt(0).toUpperCase()}
                    </div>
                    <div>
                      <div className="text-white font-bold">{user.name}</div>
                      <div className="text-accent text-xs">{getRoleDisplayName(user.role)}</div>
                    </div>
                  </div>
                  <Link
                    href={getRoleDashboardPath(user.role)}
                    onClick={() => setMobileMenuOpen(false)}
                    className="text-white text-center py-3 border border-white/20 rounded-xl"
                  >
                    Dashboard
                  </Link>
                  <button
                    onClick={() => {
                      logout();
                      setMobileMenuOpen(false);
                    }}
                    className="text-red-400 text-center py-3 border border-red-500/20 rounded-xl"
                  >
                    Sign Out
                  </button>
                </>
              ) : (
                <>
                  <Link
                    href="/auth/login"
                    onClick={() => setMobileMenuOpen(false)}
                    className="text-white text-center py-3 border border-white/20 rounded-xl"
                  >
                    Login
                  </Link>
                  <Link
                    href="/auth/register"
                    onClick={() => setMobileMenuOpen(false)}
                    className="bg-accent text-black font-bold py-3 rounded-xl text-center"
                  >
                    Get Started
                  </Link>
                </>
              )}
            </div>
          </motion.div>
        )}
      </AnimatePresence>
    </nav>
  );
}
