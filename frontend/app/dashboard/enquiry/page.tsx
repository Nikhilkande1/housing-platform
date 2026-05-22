"use client";

import React, { useEffect, useState } from "react";
import DashboardLayout from "@/components/DashboardLayout";
import { motion, AnimatePresence } from "framer-motion";
import {
  Search, MessageSquare, Clock, MapPin, Star,
  ArrowUpRight, Send, RefreshCw, Check, Mail, Phone
} from "lucide-react";
import { cn } from "@/utils/cn";
import { apiCall } from "@/lib/auth";

interface PropertyData {
  id: number;
  title: string;
  location: string;
  price: number;
  estimatedRoi: number;
  imageUrl: string;
  type: string;
}

interface EnquiryData {
  id: number;
  propertyTitle: string;
  vendorName: string;
  message: string;
  status: string;
  createdAt: string;
}

function formatPrice(n: number): string {
  if (n >= 10000000) return `₹${(n / 10000000).toFixed(1)} Cr`;
  if (n >= 100000) return `₹${(n / 100000).toFixed(1)}L`;
  return `₹${n.toLocaleString("en-IN")}`;
}

export default function EnquiryDashboard() {
  const [properties, setProperties] = useState<PropertyData[]>([]);
  const [enquiries, setEnquiries] = useState<EnquiryData[]>([]);
  const [loading, setLoading] = useState(true);
  const [refreshing, setRefreshing] = useState(false);
  const [enquiryText, setEnquiryText] = useState("");
  const [selectedProperty, setSelectedProperty] = useState<PropertyData | null>(null);
  const [sending, setSending] = useState(false);
  const [sendSuccess, setSendSuccess] = useState(false);
  const [searchQuery, setSearchQuery] = useState("");

  const fetchData = async () => {
    try {
      const propRes = await apiCall("/properties");
      if (propRes.ok) {
        const propData = await propRes.json();
        setProperties(propData);
      }

      const enqRes = await apiCall("/enquiries/me");
      if (enqRes.ok) {
        const enqData = await enqRes.json();
        setEnquiries(enqData);
      }
    } catch (err) {
      console.error("Error fetching enquiries dashboard data", err);
    } finally {
      setLoading(false);
      setRefreshing(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleRefresh = () => {
    setRefreshing(true);
    fetchData();
  };

  const handleSendEnquiry = async () => {
    if (!selectedProperty || !enquiryText.trim()) return;
    setSending(true);
    try {
      const res = await apiCall("/enquiries", {
        method: "POST",
        body: JSON.stringify({
          propertyId: selectedProperty.id,
          message: enquiryText,
        }),
      });

      if (res.ok) {
        setSendSuccess(true);
        setEnquiryText("");
        setSelectedProperty(null);
        setTimeout(() => setSendSuccess(false), 3000);
        // Refresh enquiries list
        const enqRes = await apiCall("/enquiries/me");
        if (enqRes.ok) {
          const enqData = await enqRes.json();
          setEnquiries(enqData);
        }
      }
    } catch (err) {
      console.error("Error sending enquiry", err);
    } finally {
      setSending(false);
    }
  };

  const filteredProperties = properties.filter(
    (p) =>
      p.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
      p.location.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <DashboardLayout>
      <div className="space-y-10 max-w-7xl">
        <div className="flex flex-col md:flex-row justify-between items-start md:items-end gap-4">
          <div className="space-y-2">
            <h1 className="text-white text-4xl font-bold tracking-tight">
              Explore <span className="text-accent italic">Properties</span>.
            </h1>
            <p className="text-white/40">Browse properties and send enquiries to owners.</p>
          </div>
          <button
            onClick={handleRefresh}
            disabled={refreshing}
            className="glass px-4 py-2 rounded-xl border border-white/10 hover:border-accent/30 text-white/70 hover:text-white text-xs font-bold uppercase tracking-widest flex items-center gap-2 transition-all"
          >
            <RefreshCw size={14} className={refreshing ? "animate-spin" : ""} />
            {refreshing ? "Refreshing..." : "Refresh"}
          </button>
        </div>

        {loading ? (
          <div className="flex flex-col items-center justify-center py-20 space-y-4">
            <RefreshCw size={40} className="text-accent animate-spin" />
            <p className="text-white/40 text-sm">Syncing secure listings...</p>
          </div>
        ) : (
          <>
            {/* Search */}
            <div className="relative max-w-xl">
              <Search className="absolute left-4 top-1/2 -translate-y-1/2 text-white/20" size={20} />
              <input
                type="text"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                placeholder="Search properties, locations..."
                className="w-full bg-white/5 border border-white/10 rounded-2xl py-4 pl-12 pr-4 text-white outline-none focus:border-accent transition-all placeholder:text-white/20 text-sm"
              />
            </div>

            {/* Direct Contact Enquiries Banner */}
            <div className="glass p-6 rounded-3xl border border-white/10 flex flex-col md:flex-row md:items-center justify-between gap-6 bg-gradient-to-r from-accent/5 via-white/5 to-transparent">
              <div className="space-y-1">
                <h3 className="text-white font-bold text-sm">Direct Enquiries Support</h3>
                <p className="text-white/40 text-xs">For immediate assistance or custom broker assistance, contact our tejas nirman desk.</p>
              </div>
              <div className="flex flex-wrap gap-3 text-xs font-bold uppercase tracking-widest">
                <a href="mailto:Tejasnirman1@gmail.com" className="flex items-center gap-2 bg-white/5 hover:bg-accent hover:text-black border border-white/10 px-4 py-3 rounded-xl transition-all group">
                  <Mail size={14} className="text-accent group-hover:text-inherit" />
                  Tejasnirman1@gmail.com
                </a>
                <a href="tel:9676467909" className="flex items-center gap-2 bg-white/5 hover:bg-accent hover:text-black border border-white/10 px-4 py-3 rounded-xl transition-all group">
                  <Phone size={14} className="text-accent group-hover:text-inherit" />
                  9676467909
                </a>
              </div>
            </div>

            {/* Featured Properties */}
            <div className="space-y-4">
              <h2 className="text-white text-xl font-bold">Featured Properties</h2>
              {filteredProperties.length === 0 ? (
                <p className="text-white/40 text-sm">No properties found matching your search.</p>
              ) : (
                <div className="grid sm:grid-cols-2 lg:grid-cols-4 gap-4">
                  {filteredProperties.map((prop, idx) => (
                    <motion.div
                      key={prop.id}
                      initial={{ opacity: 0, y: 20 }}
                      animate={{ opacity: 1, y: 0 }}
                      transition={{ delay: idx * 0.1 }}
                      whileHover={{ y: -5 }}
                      onClick={() => setSelectedProperty(selectedProperty?.id === prop.id ? null : prop)}
                      className={cn(
                        "glass rounded-2xl border overflow-hidden cursor-pointer group transition-all",
                        selectedProperty?.id === prop.id ? "border-accent" : "border-white/10"
                      )}
                    >
                      <div className="aspect-[4/3] relative overflow-hidden bg-white/5">
                        <img
                          src={prop.imageUrl || "https://images.unsplash.com/photo-1613490493576-7fde63acd811?q=80&w=2071&auto=format&fit=crop"}
                          alt={prop.title}
                          className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-700"
                        />
                        <div className="absolute top-3 right-3 bg-black/60 backdrop-blur-md px-2 py-1 rounded-lg flex items-center gap-1 text-xs">
                          <Star size={10} className="text-amber-400" fill="currentColor" />
                          <span className="text-white font-bold">4.9</span>
                        </div>
                        <div className="absolute inset-0 bg-gradient-to-t from-black/60 to-transparent" />
                      </div>
                      <div className="p-4 space-y-2">
                        <span className="text-accent text-[10px] font-bold uppercase tracking-widest">
                          {prop.type || "VILLA"}
                        </span>
                        <h3 className="text-white font-bold text-sm line-clamp-1">{prop.title}</h3>
                        <p className="text-white/40 text-xs flex items-center gap-1">
                          <MapPin size={12} /> {prop.location}
                        </p>
                        <div className="flex justify-between items-center pt-2 border-t border-white/5">
                          <span className="text-accent font-bold text-sm">{formatPrice(prop.price)}</span>
                          <button className="text-white/40 hover:text-accent transition-colors">
                            <ArrowUpRight size={16} />
                          </button>
                        </div>
                      </div>
                    </motion.div>
                  ))}
                </div>
              )}
            </div>

            {/* Send Enquiry */}
            <AnimatePresence>
              {selectedProperty && (
                <motion.div
                  initial={{ opacity: 0, height: 0 }}
                  animate={{ opacity: 1, height: "auto" }}
                  exit={{ opacity: 0, height: 0 }}
                  className="glass p-6 rounded-2xl border border-accent/20 space-y-4 overflow-hidden"
                >
                  <h3 className="text-white font-bold">
                    Send Enquiry — <span className="text-accent">{selectedProperty.title}</span>
                  </h3>
                  <div className="flex gap-4">
                    <input
                      type="text"
                      value={enquiryText}
                      onChange={(e) => setEnquiryText(e.target.value)}
                      placeholder="Type your question about visits, pricing, or details..."
                      className="flex-1 bg-white/5 border border-white/10 rounded-xl py-3 px-4 text-white outline-none focus:border-accent transition-all text-sm"
                    />
                    <button
                      onClick={handleSendEnquiry}
                      disabled={sending || !enquiryText.trim()}
                      className="bg-accent text-black font-bold px-6 rounded-xl flex items-center gap-2 hover:scale-105 transition-transform text-sm disabled:opacity-50"
                    >
                      {sending ? <RefreshCw size={16} className="animate-spin" /> : <Send size={16} />}
                      {sending ? "Sending..." : "Send"}
                    </button>
                  </div>
                </motion.div>
              )}
            </AnimatePresence>

            {/* Success notification */}
            {sendSuccess && (
              <motion.div
                initial={{ opacity: 0, y: 10 }}
                animate={{ opacity: 1, y: 0 }}
                className="bg-emerald-500/10 border border-emerald-500/20 text-emerald-500 p-4 rounded-xl flex items-center gap-2"
              >
                <Check size={18} />
                <span>Enquiry sent successfully! The developer will respond shortly.</span>
              </motion.div>
            )}

            {/* My Enquiries */}
            <div className="space-y-4">
              <h2 className="text-white text-xl font-bold">My Enquiries</h2>
              {enquiries.length === 0 ? (
                <div className="glass rounded-3xl border border-white/10 p-12 text-center space-y-4">
                  <div className="w-16 h-16 bg-white/5 rounded-2xl flex items-center justify-center text-white/30 mx-auto">
                    <MessageSquare size={30} />
                  </div>
                  <div className="space-y-1">
                    <h3 className="text-white font-bold text-lg">No enquiries sent yet</h3>
                    <p className="text-white/40 text-sm max-w-md mx-auto">Browse featured properties above and drop an enquiry to connect directly with the developers.</p>
                  </div>
                </div>
              ) : (
                <div className="space-y-3">
                  {enquiries.map((enq) => (
                    <div
                      key={enq.id}
                      className="glass p-5 rounded-2xl border border-white/10 flex flex-col md:flex-row md:items-center justify-between gap-4 hover:border-accent/20 transition-all"
                    >
                      <div className="flex-1">
                        <div className="text-white font-bold text-sm">
                          {enq.propertyTitle || enq.vendorName || "General Enquiry"}
                        </div>
                        <p className="text-white/40 text-xs mt-1">&ldquo;{enq.message}&rdquo;</p>
                      </div>
                      <div className="flex items-center gap-4">
                        <span className="text-white/20 text-xs flex items-center gap-1">
                          <Clock size={12} /> {new Date(enq.createdAt).toLocaleDateString()}
                        </span>
                        <span
                          className={`text-[10px] font-bold px-2 py-0.5 rounded-full border uppercase tracking-widest ${
                            enq.status === "REPLIED" || enq.status === "COMPLETED"
                              ? "bg-emerald-500/10 text-emerald-500 border-emerald-500/20"
                              : "bg-amber-500/10 text-amber-500 border-amber-500/20"
                          }`}
                        >
                          {enq.status}
                        </span>
                      </div>
                    </div>
                  ))}
                </div>
              )}
            </div>
          </>
        )}
      </div>
    </DashboardLayout>
  );
}
