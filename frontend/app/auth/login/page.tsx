"use client";

import React, { useState } from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { motion } from "framer-motion";
import { Mail, Lock, ArrowRight, Eye, EyeOff, Sparkles } from "lucide-react";
import { useAuth, getRoleDashboardPath } from "@/lib/auth";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");
  const { login } = useAuth();
  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);
    setError("");
    try {
      await login(email, password);
      // Use a small delay to ensure state is updated before reading
      setTimeout(() => {
        try {
          const storedUser = window.localStorage.getItem("tn_user");
          if (storedUser) {
            const u = JSON.parse(storedUser);
            router.push(getRoleDashboardPath(u.role));
          } else {
            router.push("/dashboard/user");
          }
        } catch {
          router.push("/dashboard/user");
        }
      }, 100);
    } catch (err) {
      setError("Invalid credentials. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <main className="min-h-screen bg-black flex items-center justify-center px-6 relative overflow-hidden">
      {/* Background Effects */}
      <div className="absolute inset-0">
        <div className="absolute top-1/4 left-1/4 w-96 h-96 bg-accent/10 blur-[200px] rounded-full" />
        <div className="absolute bottom-1/4 right-1/4 w-96 h-96 bg-blue-500/5 blur-[200px] rounded-full" />
        <div
          className="absolute inset-0 opacity-20"
          style={{
            backgroundImage: `url('https://images.unsplash.com/photo-1613490493576-7fde63acd811?q=80&w=2071&auto=format&fit=crop')`,
            backgroundSize: "cover",
            backgroundPosition: "center",
            filter: "blur(60px) saturate(0.5)",
          }}
        />
      </div>

      <motion.div
        initial={{ opacity: 0, y: 30, scale: 0.95 }}
        animate={{ opacity: 1, y: 0, scale: 1 }}
        transition={{ duration: 0.8, ease: [0.25, 0.4, 0.25, 1] }}
        className="relative z-10 w-full max-w-md"
      >
        {/* Logo */}
        <Link href="/" className="flex items-center gap-2 mb-12 justify-center group">
          <div className="w-12 h-12 bg-accent rounded-xl flex items-center justify-center transform group-hover:rotate-12 transition-transform">
            <span className="text-black font-bold text-2xl">TN</span>
          </div>
          <span className="text-white font-display text-3xl font-semibold tracking-tight">
            Tejas <span className="text-accent">Nirman</span>
          </span>
        </Link>

        {/* Glass Card */}
        <div className="glass-dark p-10 rounded-[2.5rem] border border-white/10 shadow-2xl">
          <div className="space-y-2 mb-10">
            <h1 className="text-white text-3xl font-bold tracking-tight">
              Welcome <span className="text-accent italic">back</span>.
            </h1>
            <p className="text-white/40 text-sm">
              Sign in to your Tejas Nirman account
            </p>
          </div>

          {error && (
            <motion.div
              initial={{ opacity: 0, y: -10 }}
              animate={{ opacity: 1, y: 0 }}
              className="bg-red-500/10 border border-red-500/20 text-red-400 text-sm p-4 rounded-xl mb-6"
            >
              {error}
            </motion.div>
          )}

          <form onSubmit={handleSubmit} className="space-y-6">
            <div className="space-y-2">
              <label htmlFor="login-email" className="text-white/40 text-xs uppercase font-bold tracking-widest">
                Email Address
              </label>
              <div className="relative">
                <Mail className="absolute left-4 top-1/2 -translate-y-1/2 text-white/20" size={18} />
                <input
                  id="login-email"
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  placeholder="you@example.com"
                  required
                  className="w-full bg-white/5 border border-white/10 rounded-xl py-4 pl-12 pr-4 text-white outline-none focus:border-accent transition-all placeholder:text-white/20"
                />
              </div>
            </div>

            <div className="space-y-2">
              <label htmlFor="login-password" className="text-white/40 text-xs uppercase font-bold tracking-widest">
                Password
              </label>
              <div className="relative">
                <Lock className="absolute left-4 top-1/2 -translate-y-1/2 text-white/20" size={18} />
                <input
                  id="login-password"
                  type={showPassword ? "text" : "password"}
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  placeholder="••••••••"
                  required
                  className="w-full bg-white/5 border border-white/10 rounded-xl py-4 pl-12 pr-12 text-white outline-none focus:border-accent transition-all placeholder:text-white/20"
                />
                <button
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-4 top-1/2 -translate-y-1/2 text-white/20 hover:text-white/40 transition-colors"
                >
                  {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
                </button>
              </div>
            </div>

            <div className="flex justify-between items-center">
              <label className="flex items-center gap-2 cursor-pointer">
                <input type="checkbox" className="w-4 h-4 rounded border-white/10 bg-white/5 accent-accent" />
                <span className="text-white/40 text-xs">Remember me</span>
              </label>
              <button type="button" className="text-accent text-xs font-bold hover:underline">
                Forgot password?
              </button>
            </div>

            <button
              type="submit"
              disabled={isLoading}
              className="w-full bg-accent text-black font-bold py-4 rounded-xl flex items-center justify-center gap-3 hover:scale-[1.02] transition-all shadow-[0_0_40px_rgba(197,168,128,0.2)] disabled:opacity-50 disabled:scale-100"
            >
              {isLoading ? (
                <div className="w-5 h-5 border-2 border-black/30 border-t-black rounded-full animate-spin" />
              ) : (
                <>
                  Sign In
                  <ArrowRight size={18} />
                </>
              )}
            </button>
          </form>

          <div className="mt-8 pt-8 border-t border-white/5 text-center">
            <p className="text-white/40 text-sm">
              Don&apos;t have an account?{" "}
              <Link href="/auth/register" className="text-accent font-bold hover:underline">
                Create one
              </Link>
            </p>
          </div>
        </div>

        {/* Demo Notice */}
        <motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 1 }}
          className="mt-6 flex items-center justify-center gap-2 text-white/20 text-xs"
        >
          <Sparkles size={12} className="text-accent/50" />
          <span>Demo mode: Use any email/password to explore</span>
        </motion.div>
      </motion.div>
    </main>
  );
}
