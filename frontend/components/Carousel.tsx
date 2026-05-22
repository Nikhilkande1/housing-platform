"use client";

import React, { useState, useCallback, useEffect } from "react";
import { motion, AnimatePresence } from "framer-motion";
import { ChevronLeft, ChevronRight } from "lucide-react";
import { cn } from "@/utils/cn";

interface CarouselProps {
  items: {
    image: string;
    title: string;
    subtitle?: string;
    tag?: string;
  }[];
  autoPlay?: boolean;
  interval?: number;
  className?: string;
}

export default function Carousel({
  items,
  autoPlay = true,
  interval = 5000,
  className = "",
}: CarouselProps) {
  const [current, setCurrent] = useState(0);
  const [direction, setDirection] = useState(0);

  const next = useCallback(() => {
    setDirection(1);
    setCurrent((prev) => (prev + 1) % items.length);
  }, [items.length]);

  const prev = useCallback(() => {
    setDirection(-1);
    setCurrent((prev) => (prev - 1 + items.length) % items.length);
  }, [items.length]);

  useEffect(() => {
    if (!autoPlay) return;
    const timer = setInterval(next, interval);
    return () => clearInterval(timer);
  }, [autoPlay, interval, next]);

  const variants = {
    enter: (dir: number) => ({
      x: dir > 0 ? "100%" : "-100%",
      opacity: 0,
      scale: 0.95,
    }),
    center: {
      x: 0,
      opacity: 1,
      scale: 1,
    },
    exit: (dir: number) => ({
      x: dir > 0 ? "-100%" : "100%",
      opacity: 0,
      scale: 0.95,
    }),
  };

  return (
    <div className={cn("relative w-full overflow-hidden rounded-[2.5rem]", className)}>
      <div className="aspect-[16/9] relative">
        <AnimatePresence initial={false} custom={direction} mode="popLayout">
          <motion.div
            key={current}
            custom={direction}
            variants={variants}
            initial="enter"
            animate="center"
            exit="exit"
            transition={{ duration: 0.5, ease: [0.25, 0.4, 0.25, 1] }}
            className="absolute inset-0"
          >
            <img
              src={items[current].image}
              alt={items[current].title}
              className="w-full h-full object-cover"
            />
            <div className="absolute inset-0 bg-gradient-to-t from-black via-black/30 to-transparent" />

            {/* Content Overlay */}
            <div className="absolute bottom-10 left-10 right-10">
              {items[current].tag && (
                <span className="bg-accent/20 backdrop-blur-md text-accent text-[10px] font-bold px-3 py-1 rounded-full border border-accent/30 uppercase tracking-widest mb-4 inline-block">
                  {items[current].tag}
                </span>
              )}
              <h3 className="text-white text-3xl md:text-4xl font-bold mb-2 tracking-tight">
                {items[current].title}
              </h3>
              {items[current].subtitle && (
                <p className="text-white/60 text-sm md:text-base max-w-lg">
                  {items[current].subtitle}
                </p>
              )}
            </div>
          </motion.div>
        </AnimatePresence>
      </div>

      {/* Navigation Buttons */}
      <button
        onClick={prev}
        aria-label="Previous slide"
        className="absolute left-6 top-1/2 -translate-y-1/2 w-12 h-12 bg-white/10 backdrop-blur-md rounded-full border border-white/20 flex items-center justify-center text-white hover:bg-white/20 transition-all z-20"
      >
        <ChevronLeft size={20} />
      </button>
      <button
        onClick={next}
        aria-label="Next slide"
        className="absolute right-6 top-1/2 -translate-y-1/2 w-12 h-12 bg-white/10 backdrop-blur-md rounded-full border border-white/20 flex items-center justify-center text-white hover:bg-white/20 transition-all z-20"
      >
        <ChevronRight size={20} />
      </button>

      {/* Dots */}
      <div className="absolute bottom-4 left-1/2 -translate-x-1/2 flex gap-2 z-20">
        {items.map((_, idx) => (
          <button
            key={idx}
            onClick={() => {
              setDirection(idx > current ? 1 : -1);
              setCurrent(idx);
            }}
            aria-label={`Go to slide ${idx + 1}`}
            className={cn(
              "h-1.5 rounded-full transition-all duration-300",
              idx === current
                ? "w-8 bg-accent"
                : "w-1.5 bg-white/30 hover:bg-white/50"
            )}
          />
        ))}
      </div>
    </div>
  );
}
