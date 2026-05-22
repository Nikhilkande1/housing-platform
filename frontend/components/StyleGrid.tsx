"use client";

import React from "react";
import { motion } from "framer-motion";
import { ChevronRight, ArrowUpRight } from "lucide-react";
import { cn } from "@/utils/cn";

export function SectionHeader({ 
  title, 
  subtitle, 
  tag, 
  align = "left" 
}: { 
  title: string; 
  subtitle: string; 
  tag: string; 
  align?: "left" | "center" 
}) {
  return (
    <div className={cn("max-w-3xl mb-16", align === "center" && "mx-auto text-center")}>
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        whileInView={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.6 }}
        viewport={{ once: true }}
      >
        <span className="text-accent text-xs font-bold uppercase tracking-[0.3em] mb-4 block">
          {tag}
        </span>
        <h2 className="text-white text-4xl md:text-5xl font-bold mb-6 tracking-tight leading-tight">
          {title}
        </h2>
        <p className="text-white/50 text-lg leading-relaxed">
          {subtitle}
        </p>
      </motion.div>
    </div>
  );
}

const STYLES = [
  {
    id: 1,
    name: "Indian Traditional",
    description: "Manduva, Chettinad, and Kaavi fusion architectures.",
    image: "https://images.unsplash.com/photo-1590059132213-f91590b106b3?q=80&w=1974&auto=format&fit=crop",
    className: "md:col-span-1 md:row-span-2",
  },
  {
    id: 2,
    name: "Modern Minimalist",
    description: "Sleek lines and open layouts.",
    image: "https://images.unsplash.com/photo-1480074568708-e7b720bb3f09?q=80&w=2074&auto=format&fit=crop",
    className: "md:col-span-1 md:row-span-1",
  },
  {
    id: 3,
    name: "European Luxury",
    description: "Grand facades and classical details.",
    image: "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?q=80&w=2070&auto=format&fit=crop",
    className: "md:col-span-2 md:row-span-1",
  },
  {
    id: 4,
    name: "Bali Resort",
    description: "Tropical serenity and wooden finishes.",
    image: "https://images.unsplash.com/photo-1540541338287-41700207dee6?q=80&w=2070&auto=format&fit=crop",
    className: "md:col-span-1 md:row-span-1",
  },
];

export function StyleGrid() {
  return (
    <div className="grid grid-cols-1 md:grid-cols-3 md:grid-rows-2 gap-4">
      {STYLES.map((style) => (
        <motion.div
          key={style.id}
          initial={{ opacity: 0, scale: 0.95 }}
          whileInView={{ opacity: 1, scale: 1 }}
          transition={{ duration: 0.5 }}
          viewport={{ once: true }}
          className={cn(
            "group relative overflow-hidden rounded-[2rem] border border-white/10 aspect-square md:aspect-auto",
            style.className
          )}
        >
          <img
            src={style.image}
            alt={style.name}
            className="h-full w-full object-cover transition-transform duration-700 group-hover:scale-110"
          />
          <div className="absolute inset-0 bg-gradient-to-t from-black via-black/40 to-transparent" />
          
          <div className="absolute bottom-6 left-6 right-6 flex justify-between items-end">
            <div>
              <h3 className="text-white text-xl font-bold mb-1">{style.name}</h3>
              <p className="text-white/60 text-sm">{style.description}</p>
            </div>
            <button aria-label={`View ${style.name} details`} className="bg-white/10 hover:bg-white backdrop-blur-md text-white hover:text-black p-3 rounded-full border border-white/20 transition-all">
              <ArrowUpRight size={20} />
            </button>
          </div>
        </motion.div>
      ))}
    </div>
  );
}
