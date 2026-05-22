"use client";

import React from "react";
import { AuthProvider } from "@/lib/auth";
import CustomCursor from "@/components/effects/CustomCursor";

export default function ClientProviders({ children }: { children: React.ReactNode }) {
  return (
    <AuthProvider>
      <CustomCursor />
      {children}
    </AuthProvider>
  );
}
