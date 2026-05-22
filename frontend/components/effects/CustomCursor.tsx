"use client";

import React, { useEffect, useRef, useState } from "react";
import { motion, useMotionValue, useSpring } from "framer-motion";

export default function CustomCursor() {
  const cursorX = useMotionValue(-100);
  const cursorY = useMotionValue(-100);
  const [isHovering, setIsHovering] = useState(false);
  const [isClicking, setIsClicking] = useState(false);
  const [isVisible, setIsVisible] = useState(false);

  const springConfig = { damping: 25, stiffness: 300, mass: 0.5 };
  const cursorXSpring = useSpring(cursorX, springConfig);
  const cursorYSpring = useSpring(cursorY, springConfig);

  useEffect(() => {
    // Only show custom cursor on desktop
    const isTouchDevice = "ontouchstart" in window || navigator.maxTouchPoints > 0;
    if (isTouchDevice) return;

    setIsVisible(true);

    const moveCursor = (e: MouseEvent) => {
      cursorX.set(e.clientX);
      cursorY.set(e.clientY);
    };

    const handleMouseDown = () => setIsClicking(true);
    const handleMouseUp = () => setIsClicking(false);

    const handleMouseOver = (e: MouseEvent) => {
      const target = e.target as HTMLElement;
      if (
        target.tagName === "BUTTON" ||
        target.tagName === "A" ||
        target.closest("button") ||
        target.closest("a") ||
        target.closest("[data-cursor-hover]") ||
        target.classList.contains("cursor-pointer")
      ) {
        setIsHovering(true);
      }
    };

    const handleMouseOut = (e: MouseEvent) => {
      const target = e.target as HTMLElement;
      if (
        target.tagName === "BUTTON" ||
        target.tagName === "A" ||
        target.closest("button") ||
        target.closest("a") ||
        target.closest("[data-cursor-hover]") ||
        target.classList.contains("cursor-pointer")
      ) {
        setIsHovering(false);
      }
    };

    window.addEventListener("mousemove", moveCursor);
    window.addEventListener("mousedown", handleMouseDown);
    window.addEventListener("mouseup", handleMouseUp);
    document.addEventListener("mouseover", handleMouseOver);
    document.addEventListener("mouseout", handleMouseOut);

    return () => {
      window.removeEventListener("mousemove", moveCursor);
      window.removeEventListener("mousedown", handleMouseDown);
      window.removeEventListener("mouseup", handleMouseUp);
      document.removeEventListener("mouseover", handleMouseOver);
      document.removeEventListener("mouseout", handleMouseOut);
    };
  }, [cursorX, cursorY]);

  if (!isVisible) return null;

  return (
    <>
      {/* Main cursor dot */}
      <motion.div
        className="custom-cursor-dot"
        style={{
          left: cursorXSpring,
          top: cursorYSpring,
          position: "fixed",
          zIndex: 99999,
          pointerEvents: "none",
          width: isHovering ? 48 : isClicking ? 8 : 12,
          height: isHovering ? 48 : isClicking ? 8 : 12,
          borderRadius: "50%",
          background: isHovering
            ? "rgba(197, 168, 128, 0.15)"
            : "rgba(197, 168, 128, 0.9)",
          border: isHovering ? "2px solid rgba(197, 168, 128, 0.6)" : "none",
          transform: "translate(-50%, -50%)",
          transition: "width 0.3s ease, height 0.3s ease, background 0.3s ease, border 0.3s ease",
          mixBlendMode: isHovering ? "normal" : "normal",
        }}
      />
      {/* Outer ring */}
      <motion.div
        className="custom-cursor-ring"
        style={{
          left: cursorXSpring,
          top: cursorYSpring,
          position: "fixed",
          zIndex: 99998,
          pointerEvents: "none",
          width: isHovering ? 64 : 36,
          height: isHovering ? 64 : 36,
          borderRadius: "50%",
          border: `1px solid rgba(197, 168, 128, ${isHovering ? 0.4 : 0.2})`,
          transform: "translate(-50%, -50%)",
          transition: "width 0.4s ease, height 0.4s ease, border 0.3s ease",
        }}
      />
    </>
  );
}
