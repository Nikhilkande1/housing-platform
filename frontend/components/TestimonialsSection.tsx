"use client";

import React from "react";
import { motion } from "framer-motion";
import { Star, Quote } from "lucide-react";
import { SectionHeader } from "@/components/StyleGrid";

const TESTIMONIALS = [
  {
    name: "Rajesh Mehta",
    role: "Property Owner",
    text: "Tejas Nirman transformed how I think about property investment. The ROI calculator alone saved me months of research. My villa generates 15% annual returns.",
    rating: 5,
    avatar: "https://i.pravatar.cc/100?u=rajesh",
  },
  {
    name: "Priya Sharma",
    role: "Home Builder",
    text: "The configurator is incredible. I designed my dream Chettinad-fusion home in minutes. The vendor marketplace had everything I needed, all premium quality.",
    rating: 5,
    avatar: "https://i.pravatar.cc/100?u=priya",
  },
  {
    name: "Amit Gupta",
    role: "Airbnb Host",
    text: "Built a resort-style villa through Tejas Nirman and listed it on Airbnb. 94% occupancy rate from day one. The smart home features are a game changer.",
    rating: 5,
    avatar: "https://i.pravatar.cc/100?u=amit",
  },
];

export default function TestimonialsSection() {
  return (
    <div className="w-full">
      <SectionHeader
        align="center"
        tag="Testimonials"
        title="Trusted by Visionaries."
        subtitle="Hear from property owners, builders, and investors who've transformed their dreams into reality."
      />

      <div className="grid md:grid-cols-3 gap-6">
        {TESTIMONIALS.map((t, idx) => (
          <motion.div
            key={t.name}
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            transition={{ delay: idx * 0.15, duration: 0.5 }}
            className="glass p-8 rounded-3xl border border-white/10 hover:border-accent/20 transition-all relative group"
          >
            <Quote size={40} className="text-accent/10 absolute top-6 right-6" />

            <div className="flex items-center gap-1 mb-6">
              {Array.from({ length: t.rating }).map((_, i) => (
                <Star key={i} size={14} className="text-accent" fill="currentColor" />
              ))}
            </div>

            <p className="text-white/70 text-sm leading-relaxed mb-8 italic">
              &ldquo;{t.text}&rdquo;
            </p>

            <div className="flex items-center gap-4">
              <img src={t.avatar} alt={t.name} className="w-12 h-12 rounded-full border-2 border-accent/20" />
              <div>
                <div className="text-white font-bold text-sm">{t.name}</div>
                <div className="text-accent text-xs font-bold">{t.role}</div>
              </div>
            </div>
          </motion.div>
        ))}
      </div>
    </div>
  );
}
