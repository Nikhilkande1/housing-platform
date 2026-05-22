"use client";

import React, { useState } from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { motion, AnimatePresence } from "framer-motion";
import {
  Mail, Lock, User, Phone, ArrowRight, ArrowLeft,
  Eye, EyeOff, ChevronDown, Sparkles, Check,
  Home, Building2, Store, Landmark, HelpCircle
} from "lucide-react";
import { useAuth, getRoleDashboardPath, type UserRole } from "@/lib/auth";
import { cn } from "@/utils/cn";

const ROLES: { id: UserRole; label: string; desc: string; icon: React.ElementType; color: string }[] = [
  { id: "ROLE_USER", label: "User", desc: "Buy, Build or Rent properties", icon: Home, color: "bg-blue-500/10 text-blue-500 border-blue-500/20" },
  { id: "ROLE_OWNER", label: "Property Owner", desc: "Lease your property for income", icon: Building2, color: "bg-emerald-500/10 text-emerald-500 border-emerald-500/20" },
  { id: "ROLE_VENDOR", label: "Vendor / Manufacturer", desc: "Sell materials & services", icon: Store, color: "bg-amber-500/10 text-amber-500 border-amber-500/20" },
  { id: "ROLE_BANK", label: "Bank / Finance Provider", desc: "Offer loans & financial services", icon: Landmark, color: "bg-purple-500/10 text-purple-500 border-purple-500/20" },
  { id: "ROLE_ENQUIRY", label: "Enquiry / Visitor", desc: "Browse and explore the platform", icon: HelpCircle, color: "bg-rose-500/10 text-rose-500 border-rose-500/20" },
];

export default function RegisterPage() {
  const [step, setStep] = useState(1);
  const [formData, setFormData] = useState({
    role: "" as UserRole | "",
    name: "",
    email: "",
    phone: "",
    password: "",
    confirmPassword: "",
  });
  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");
  const { register } = useAuth();
  const router = useRouter();

  const handleSubmit = async () => {
    if (formData.password !== formData.confirmPassword) {
      setError("Passwords do not match");
      return;
    }
    setIsLoading(true);
    setError("");
    try {
      await register({
        name: formData.name,
        email: formData.email,
        password: formData.password,
        role: formData.role as UserRole,
        phoneNumber: formData.phone,
      });
      router.push(getRoleDashboardPath(formData.role as UserRole));
    } catch (err) {
      setError("Registration failed. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <main className="min-h-screen bg-black flex items-center justify-center px-6 relative overflow-hidden">
      {/* Background Effects */}
      <div className="absolute inset-0">
        <div className="absolute top-1/3 right-1/4 w-96 h-96 bg-accent/10 blur-[200px] rounded-full" />
        <div className="absolute bottom-1/3 left-1/4 w-96 h-96 bg-purple-500/5 blur-[200px] rounded-full" />
        <div
          className="absolute inset-0 opacity-15"
          style={{
            backgroundImage: `url('https://images.unsplash.com/photo-1600585154340-be6199f7a096?q=80&w=2070&auto=format&fit=crop')`,
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
        className="relative z-10 w-full max-w-lg"
      >
        {/* Logo */}
        <Link href="/" className="flex items-center gap-2 mb-10 justify-center group">
          <div className="w-12 h-12 bg-accent rounded-xl flex items-center justify-center transform group-hover:rotate-12 transition-transform">
            <span className="text-black font-bold text-2xl">TN</span>
          </div>
          <span className="text-white font-display text-3xl font-semibold tracking-tight">
            Tejas <span className="text-accent">Nirman</span>
          </span>
        </Link>

        {/* Progress Steps */}
        <div className="flex items-center justify-center gap-3 mb-8">
          {[1, 2, 3].map((s) => (
            <div key={s} className="flex items-center gap-3">
              <div
                className={cn(
                  "w-8 h-8 rounded-full flex items-center justify-center text-xs font-bold transition-all",
                  step >= s
                    ? "bg-accent text-black"
                    : "bg-white/5 text-white/20 border border-white/10"
                )}
              >
                {step > s ? <Check size={14} /> : s}
              </div>
              {s < 3 && (
                <div className={cn("w-16 h-[2px] rounded-full transition-all", step > s ? "bg-accent" : "bg-white/10")} />
              )}
            </div>
          ))}
        </div>

        {/* Glass Card */}
        <div className="glass-dark p-10 rounded-[2.5rem] border border-white/10 shadow-2xl">
          {error && (
            <motion.div
              initial={{ opacity: 0, y: -10 }}
              animate={{ opacity: 1, y: 0 }}
              className="bg-red-500/10 border border-red-500/20 text-red-400 text-sm p-4 rounded-xl mb-6"
            >
              {error}
            </motion.div>
          )}

          <AnimatePresence mode="wait">
            {/* Step 1: Choose Role */}
            {step === 1 && (
              <motion.div
                key="step1"
                initial={{ opacity: 0, x: 20 }}
                animate={{ opacity: 1, x: 0 }}
                exit={{ opacity: 0, x: -20 }}
                className="space-y-8"
              >
                <div className="space-y-2">
                  <h1 className="text-white text-3xl font-bold tracking-tight">
                    Choose your <span className="text-accent italic">role</span>.
                  </h1>
                  <p className="text-white/40 text-sm">
                    Select how you want to use Tejas Nirman
                  </p>
                </div>

                <div className="space-y-3">
                  {ROLES.map((role) => {
                    const Icon = role.icon;
                    return (
                      <button
                        key={role.id}
                        onClick={() => setFormData({ ...formData, role: role.id })}
                        className={cn(
                          "w-full p-4 rounded-2xl border transition-all flex items-center gap-4 text-left",
                          formData.role === role.id
                            ? "bg-accent/10 border-accent shadow-[0_0_20px_rgba(197,168,128,0.1)]"
                            : "bg-white/5 border-white/10 hover:bg-white/10 hover:border-white/20"
                        )}
                      >
                        <div className={cn("w-12 h-12 rounded-xl flex items-center justify-center border", role.color)}>
                          <Icon size={22} />
                        </div>
                        <div className="flex-1">
                          <div className={cn("font-bold text-sm", formData.role === role.id ? "text-accent" : "text-white")}>
                            {role.label}
                          </div>
                          <div className="text-white/40 text-xs">{role.desc}</div>
                        </div>
                        {formData.role === role.id && (
                          <div className="w-6 h-6 bg-accent rounded-full flex items-center justify-center text-black">
                            <Check size={14} />
                          </div>
                        )}
                      </button>
                    );
                  })}
                </div>

                <button
                  onClick={() => formData.role && setStep(2)}
                  disabled={!formData.role}
                  className="w-full bg-accent text-black font-bold py-4 rounded-xl flex items-center justify-center gap-3 hover:scale-[1.02] transition-all disabled:opacity-30 disabled:scale-100"
                >
                  Continue <ArrowRight size={18} />
                </button>
              </motion.div>
            )}

            {/* Step 2: Personal Details */}
            {step === 2 && (
              <motion.div
                key="step2"
                initial={{ opacity: 0, x: 20 }}
                animate={{ opacity: 1, x: 0 }}
                exit={{ opacity: 0, x: -20 }}
                className="space-y-6"
              >
                <div className="space-y-2">
                  <h1 className="text-white text-3xl font-bold tracking-tight">
                    Your <span className="text-accent italic">details</span>.
                  </h1>
                  <p className="text-white/40 text-sm">Tell us about yourself</p>
                </div>

                <div className="space-y-4">
                  <div className="space-y-2">
                    <label htmlFor="reg-name" className="text-white/40 text-xs uppercase font-bold tracking-widest">Full Name</label>
                    <div className="relative">
                      <User className="absolute left-4 top-1/2 -translate-y-1/2 text-white/20" size={18} />
                      <input
                        id="reg-name"
                        type="text"
                        value={formData.name}
                        onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                        placeholder="John Doe"
                        required
                        className="w-full bg-white/5 border border-white/10 rounded-xl py-4 pl-12 pr-4 text-white outline-none focus:border-accent transition-all placeholder:text-white/20"
                      />
                    </div>
                  </div>

                  <div className="space-y-2">
                    <label htmlFor="reg-email" className="text-white/40 text-xs uppercase font-bold tracking-widest">Email Address</label>
                    <div className="relative">
                      <Mail className="absolute left-4 top-1/2 -translate-y-1/2 text-white/20" size={18} />
                      <input
                        id="reg-email"
                        type="email"
                        value={formData.email}
                        onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                        placeholder="you@example.com"
                        required
                        className="w-full bg-white/5 border border-white/10 rounded-xl py-4 pl-12 pr-4 text-white outline-none focus:border-accent transition-all placeholder:text-white/20"
                      />
                    </div>
                  </div>

                  <div className="space-y-2">
                    <label htmlFor="reg-phone" className="text-white/40 text-xs uppercase font-bold tracking-widest">Phone Number</label>
                    <div className="relative">
                      <Phone className="absolute left-4 top-1/2 -translate-y-1/2 text-white/20" size={18} />
                      <input
                        id="reg-phone"
                        type="tel"
                        value={formData.phone}
                        onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
                        placeholder="+91 9999999999"
                        className="w-full bg-white/5 border border-white/10 rounded-xl py-4 pl-12 pr-4 text-white outline-none focus:border-accent transition-all placeholder:text-white/20"
                      />
                    </div>
                  </div>
                </div>

                <div className="flex gap-4">
                  <button
                    onClick={() => setStep(1)}
                    className="flex items-center gap-2 text-white/40 hover:text-white transition-colors px-4"
                  >
                    <ArrowLeft size={18} /> Back
                  </button>
                  <button
                    onClick={() => formData.name && formData.email && setStep(3)}
                    disabled={!formData.name || !formData.email}
                    className="flex-1 bg-accent text-black font-bold py-4 rounded-xl flex items-center justify-center gap-3 hover:scale-[1.02] transition-all disabled:opacity-30 disabled:scale-100"
                  >
                    Continue <ArrowRight size={18} />
                  </button>
                </div>
              </motion.div>
            )}

            {/* Step 3: Password */}
            {step === 3 && (
              <motion.div
                key="step3"
                initial={{ opacity: 0, x: 20 }}
                animate={{ opacity: 1, x: 0 }}
                exit={{ opacity: 0, x: -20 }}
                className="space-y-6"
              >
                <div className="space-y-2">
                  <h1 className="text-white text-3xl font-bold tracking-tight">
                    Secure your <span className="text-accent italic">account</span>.
                  </h1>
                  <p className="text-white/40 text-sm">Create a strong password</p>
                </div>

                <div className="space-y-4">
                  <div className="space-y-2">
                    <label htmlFor="reg-password" className="text-white/40 text-xs uppercase font-bold tracking-widest">Password</label>
                    <div className="relative">
                      <Lock className="absolute left-4 top-1/2 -translate-y-1/2 text-white/20" size={18} />
                      <input
                        id="reg-password"
                        type={showPassword ? "text" : "password"}
                        value={formData.password}
                        onChange={(e) => setFormData({ ...formData, password: e.target.value })}
                        placeholder="••••••••"
                        required
                        className="w-full bg-white/5 border border-white/10 rounded-xl py-4 pl-12 pr-12 text-white outline-none focus:border-accent transition-all placeholder:text-white/20"
                      />
                      <button
                        type="button"
                        onClick={() => setShowPassword(!showPassword)}
                        className="absolute right-4 top-1/2 -translate-y-1/2 text-white/20 hover:text-white/40"
                      >
                        {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
                      </button>
                    </div>
                  </div>

                  <div className="space-y-2">
                    <label htmlFor="reg-confirm" className="text-white/40 text-xs uppercase font-bold tracking-widest">Confirm Password</label>
                    <div className="relative">
                      <Lock className="absolute left-4 top-1/2 -translate-y-1/2 text-white/20" size={18} />
                      <input
                        id="reg-confirm"
                        type={showPassword ? "text" : "password"}
                        value={formData.confirmPassword}
                        onChange={(e) => setFormData({ ...formData, confirmPassword: e.target.value })}
                        placeholder="••••••••"
                        required
                        className="w-full bg-white/5 border border-white/10 rounded-xl py-4 pl-12 pr-4 text-white outline-none focus:border-accent transition-all placeholder:text-white/20"
                      />
                    </div>
                    {formData.confirmPassword && formData.password !== formData.confirmPassword && (
                      <p className="text-red-400 text-xs">Passwords do not match</p>
                    )}
                  </div>
                </div>

                {/* Summary */}
                <div className="bg-white/5 p-4 rounded-xl border border-white/5 space-y-2">
                  <div className="flex justify-between text-xs">
                    <span className="text-white/40">Role</span>
                    <span className="text-accent font-bold">
                      {ROLES.find((r) => r.id === formData.role)?.label}
                    </span>
                  </div>
                  <div className="flex justify-between text-xs">
                    <span className="text-white/40">Email</span>
                    <span className="text-white font-bold">{formData.email}</span>
                  </div>
                </div>

                <div className="flex gap-4">
                  <button
                    onClick={() => setStep(2)}
                    className="flex items-center gap-2 text-white/40 hover:text-white transition-colors px-4"
                  >
                    <ArrowLeft size={18} /> Back
                  </button>
                  <button
                    onClick={handleSubmit}
                    disabled={isLoading || !formData.password || formData.password !== formData.confirmPassword}
                    className="flex-1 bg-accent text-black font-bold py-4 rounded-xl flex items-center justify-center gap-3 hover:scale-[1.02] transition-all disabled:opacity-30 disabled:scale-100"
                  >
                    {isLoading ? (
                      <div className="w-5 h-5 border-2 border-black/30 border-t-black rounded-full animate-spin" />
                    ) : (
                      <>
                        Create Account <ArrowRight size={18} />
                      </>
                    )}
                  </button>
                </div>
              </motion.div>
            )}
          </AnimatePresence>

          <div className="mt-8 pt-8 border-t border-white/5 text-center">
            <p className="text-white/40 text-sm">
              Already have an account?{" "}
              <Link href="/auth/login" className="text-accent font-bold hover:underline">
                Sign In
              </Link>
            </p>
          </div>
        </div>

        <motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 1 }}
          className="mt-6 flex items-center justify-center gap-2 text-white/20 text-xs"
        >
          <Sparkles size={12} className="text-accent/50" />
          <span>Demo mode: All registrations auto-succeed</span>
        </motion.div>
      </motion.div>
    </main>
  );
}
