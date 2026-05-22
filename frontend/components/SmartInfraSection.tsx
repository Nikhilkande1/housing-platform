"use client";

import React from "react";
import { motion } from "framer-motion";
import {
  Wifi, Shield, Zap, Sun, Droplets, BatteryCharging,
  Lock, Eye, Monitor, CloudRain
} from "lucide-react";
import ScrollReveal from "@/components/effects/ScrollReveal";
import { SectionHeader } from "@/components/StyleGrid";

const INFRA_ITEMS = [
  { icon: Wifi, label: "Broadband Wiring", desc: "Cat6A structured networking" },
  { icon: Eye, label: "CCTV Security", desc: "AI-powered surveillance system" },
  { icon: Zap, label: "Electrical Fencing", desc: "Perimeter security fencing" },
  { icon: Lock, label: "Smart Locks", desc: "Biometric + app-controlled" },
  { icon: Sun, label: "Solar Panels", desc: "10kW rooftop installation" },
  { icon: BatteryCharging, label: "Power Backup", desc: "Lithium-ion full home UPS" },
  { icon: Droplets, label: "Borewell System", desc: "Deep bore + RO filtration" },
  { icon: CloudRain, label: "Rainwater Harvest", desc: "Collection + recharge pit" },
  { icon: Monitor, label: "Home Automation", desc: "Central smart hub control" },
  { icon: Shield, label: "Fire Safety", desc: "Sprinklers + smoke detection" },
];

export default function SmartInfraSection() {
  return (
    <div className="w-full">
      <SectionHeader
        tag="Smart Infrastructure"
        title="Built for the Future."
        subtitle="Every Tejas Nirman project includes future-proof smart infrastructure, security, and sustainable living systems."
      />

      <div className="grid grid-cols-2 md:grid-cols-5 gap-4">
        {INFRA_ITEMS.map((item, idx) => {
          const Icon = item.icon;
          return (
            <motion.div
              key={item.label}
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              viewport={{ once: true }}
              transition={{ delay: idx * 0.05, duration: 0.4 }}
              whileHover={{ y: -5, scale: 1.02 }}
              className="glass p-6 rounded-2xl border border-white/10 text-center group hover:border-accent/30 transition-all cursor-default"
            >
              <div className="w-12 h-12 bg-accent/10 rounded-xl flex items-center justify-center mx-auto mb-4 text-accent group-hover:bg-accent/20 transition-colors">
                <Icon size={22} />
              </div>
              <h4 className="text-white font-bold text-sm mb-1">{item.label}</h4>
              <p className="text-white/30 text-[10px] uppercase tracking-widest font-bold">{item.desc}</p>
            </motion.div>
          );
        })}
      </div>
    </div>
  );
}
