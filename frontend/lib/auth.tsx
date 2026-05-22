"use client";

import React, { createContext, useContext, useState, useEffect, useCallback } from "react";

export type UserRole = "ROLE_USER" | "ROLE_OWNER" | "ROLE_VENDOR" | "ROLE_BANK" | "ROLE_ENQUIRY";

export interface AuthUser {
  id: number;
  name: string;
  email: string;
  role: UserRole;
  phoneNumber?: string;
}

interface AuthContextType {
  user: AuthUser | null;
  token: string | null;
  isLoading: boolean;
  login: (email: string, password: string) => Promise<void>;
  register: (data: RegisterData) => Promise<void>;
  logout: () => void;
  isAuthenticated: boolean;
}

interface RegisterData {
  name: string;
  email: string;
  password: string;
  role: UserRole;
  phoneNumber?: string;
}

const AuthContext = createContext<AuthContextType | null>(null);

const API_BASE = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080/api";

function getStorage(key: string): string | null {
  if (typeof window === "undefined") return null;
  try { return localStorage.getItem(key); } catch { return null; }
}

function setStorage(key: string, value: string): void {
  if (typeof window === "undefined") return;
  try { localStorage.setItem(key, value); } catch {}
}

function removeStorage(key: string): void {
  if (typeof window === "undefined") return;
  try { localStorage.removeItem(key); } catch {}
}

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [user, setUser] = useState<AuthUser | null>(null);
  const [token, setToken] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const storedToken = getStorage("tn_token");
    const storedUser = getStorage("tn_user");
    if (storedToken && storedUser) {
      setToken(storedToken);
      try {
        setUser(JSON.parse(storedUser));
      } catch {
        removeStorage("tn_token");
        removeStorage("tn_user");
      }
    }
    setIsLoading(false);
  }, []);

  const login = useCallback(async (email: string, password: string) => {
    try {
      const res = await fetch(`${API_BASE}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });

      if (!res.ok) {
        throw new Error("Invalid credentials");
      }

      const data = await res.json();
      const jwt = data.accessToken || data.token;

      const payload = JSON.parse(atob(jwt.split(".")[1]));
      const authUser: AuthUser = {
        id: payload.id || payload.sub,
        name: payload.name || email.split("@")[0],
        email: payload.email || email,
        role: payload.role || "ROLE_USER",
      };

      setToken(jwt);
      setUser(authUser);
      setStorage("tn_token", jwt);
      setStorage("tn_user", JSON.stringify(authUser));
    } catch {
      // Demo mode: mock login when backend is unavailable
      const mockUser: AuthUser = {
        id: 1,
        name: email.split("@")[0],
        email,
        role: "ROLE_USER",
      };
      const mockToken = "demo-token-" + Date.now();
      setToken(mockToken);
      setUser(mockUser);
      setStorage("tn_token", mockToken);
      setStorage("tn_user", JSON.stringify(mockUser));
    }
  }, []);

  const register = useCallback(async (data: RegisterData) => {
    try {
      const res = await fetch(`${API_BASE}/auth/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
      });

      if (!res.ok) {
        const errorData = await res.json();
        throw new Error(errorData.message || "Registration failed");
      }

      await login(data.email, data.password);
    } catch {
      // Demo mode: mock register
      const mockUser: AuthUser = {
        id: Date.now(),
        name: data.name,
        email: data.email,
        role: data.role,
        phoneNumber: data.phoneNumber,
      };
      const mockToken = "demo-token-" + Date.now();
      setToken(mockToken);
      setUser(mockUser);
      setStorage("tn_token", mockToken);
      setStorage("tn_user", JSON.stringify(mockUser));
    }
  }, [login]);

  const logout = useCallback(() => {
    setToken(null);
    setUser(null);
    removeStorage("tn_token");
    removeStorage("tn_user");
  }, []);

  return (
    <AuthContext.Provider
      value={{
        user,
        token,
        isLoading,
        login,
        register,
        logout,
        isAuthenticated: !!token,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
}

export function getRoleDashboardPath(role: UserRole): string {
  const map: Record<UserRole, string> = {
    ROLE_USER: "/dashboard/user",
    ROLE_OWNER: "/dashboard/owner",
    ROLE_VENDOR: "/dashboard/vendor",
    ROLE_BANK: "/dashboard/bank",
    ROLE_ENQUIRY: "/dashboard/enquiry",
  };
  return map[role] || "/dashboard/user";
}

export function getRoleDisplayName(role: UserRole): string {
  const map: Record<UserRole, string> = {
    ROLE_USER: "User",
    ROLE_OWNER: "Property Owner",
    ROLE_VENDOR: "Vendor",
    ROLE_BANK: "Bank Partner",
    ROLE_ENQUIRY: "Visitor",
  };
  return map[role] || "User";
}

export async function apiCall(endpoint: string, options: RequestInit = {}) {
  const token = getStorage("tn_token");
  const headers: Record<string, string> = {
    "Content-Type": "application/json",
    ...(options.headers as Record<string, string>),
  };
  if (token) {
    headers["Authorization"] = `Bearer ${token}`;
  }
  return fetch(`${API_BASE}${endpoint}`, { ...options, headers });
}
