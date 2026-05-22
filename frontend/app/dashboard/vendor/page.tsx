"use client";

import React, { useEffect, useState } from "react";
import DashboardLayout from "@/components/DashboardLayout";
import { motion, AnimatePresence } from "framer-motion";
import {
  Package, Star, MessageSquare, TrendingUp,
  Plus, ArrowUpRight, Eye, RefreshCw, Check
} from "lucide-react";
import { apiCall } from "@/lib/auth";

interface ProductData {
  id: number;
  name: string;
  category: string;
  pricePerUnit: number;
  unit: string;
  description: string;
  imageUrl: string;
}

interface EnquiryData {
  id: number;
  userName: string;
  message: string;
  status: string;
  createdAt: string;
}

function formatPrice(n: number): string {
  if (n >= 100000) return `₹${(n / 100000).toFixed(1)}L`;
  return `₹${n.toLocaleString("en-IN")}`;
}

export default function VendorDashboard() {
  const [stats, setStats] = useState<any>(null);
  const [vendorProfile, setVendorProfile] = useState<any>(null);
  const [products, setProducts] = useState<ProductData[]>([]);
  const [enquiries, setEnquiries] = useState<EnquiryData[]>([]);
  const [loading, setLoading] = useState(true);
  const [refreshing, setRefreshing] = useState(false);
  
  // Add product form state
  const [showAddForm, setShowAddForm] = useState(false);
  const [newProduct, setNewProduct] = useState({
    name: "",
    category: "Cement",
    pricePerUnit: 0,
    unit: "Bag (50kg)",
    description: "",
    imageUrl: "/images/mat-cement.jpg"
  });
  const [addingProduct, setAddingProduct] = useState(false);

  const fetchData = async () => {
    try {
      // Fetch stats
      const statsRes = await apiCall("/dashboard/stats");
      if (statsRes.ok) {
        const statsData = await statsRes.json();
        setStats(statsData);
      }

      // Fetch vendor profile
      const vendorRes = await apiCall("/vendors/me");
      if (vendorRes.ok) {
        const profile = await vendorRes.json();
        setVendorProfile(profile);

        // Fetch products for this vendor
        const productsRes = await apiCall(`/materials/vendor/${profile.id}`);
        if (productsRes.ok) {
          const productsData = await productsRes.json();
          setProducts(productsData);
        }

        // Fetch enquiries for this vendor
        const enquiriesRes = await apiCall(`/enquiries/vendor/${profile.id}`);
        if (enquiriesRes.ok) {
          const enquiriesData = await enquiriesRes.json();
          setEnquiries(enquiriesData);
        }
      }
    } catch (err) {
      console.error("Error fetching vendor dashboard data", err);
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

  const handleAddProduct = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!newProduct.name || newProduct.pricePerUnit <= 0) return;
    setAddingProduct(true);
    try {
      const res = await apiCall("/materials", {
        method: "POST",
        body: JSON.stringify(newProduct)
      });
      if (res.ok) {
        setShowAddForm(false);
        setNewProduct({
          name: "",
          category: "Cement",
          pricePerUnit: 0,
          unit: "Bag (50kg)",
          description: "",
          imageUrl: "/images/mat-cement.jpg"
        });
        fetchData();
      }
    } catch (err) {
      console.error("Error adding product", err);
    } finally {
      setAddingProduct(false);
    }
  };

  const handleReplyEnquiry = async (id: number) => {
    try {
      const res = await apiCall(`/enquiries/${id}/status?status=REPLIED`, {
        method: "PUT"
      });
      if (res.ok) {
        fetchData();
      }
    } catch (err) {
      console.error("Error updating enquiry", err);
    }
  };

  const statItems = [
    {
      label: "Total Products",
      value: products ? String(products.length) : "0",
      icon: Package,
      color: "text-blue-500 bg-blue-500/10"
    },
    {
      label: "Avg. Rating",
      value: vendorProfile && vendorProfile.rating ? String(vendorProfile.rating) : "4.8",
      icon: Star,
      color: "text-amber-500 bg-amber-500/10"
    },
    {
      label: "Open Enquiries",
      value: stats ? String(stats.pendingEnquiries) : "0",
      icon: MessageSquare,
      color: "text-emerald-500 bg-emerald-500/10"
    },
    {
      label: "Review Count",
      value: vendorProfile && vendorProfile.reviewCount ? String(vendorProfile.reviewCount) : "88",
      icon: TrendingUp,
      color: "text-accent bg-accent/10"
    },
  ];

  return (
    <DashboardLayout>
      <div className="space-y-10 max-w-7xl">
        <div className="flex flex-col md:flex-row justify-between items-start md:items-end gap-4">
          <div className="space-y-2">
            <h1 className="text-white text-4xl font-bold tracking-tight">
              Vendor <span className="text-accent italic">Hub</span>.
            </h1>
            <p className="text-white/40">
              {vendorProfile ? vendorProfile.companyName : "Manage your catalog and respond to enquiries."}
            </p>
          </div>
          <div className="flex gap-3">
            <button
              onClick={handleRefresh}
              disabled={refreshing}
              className="glass px-4 py-2 rounded-xl border border-white/10 hover:border-accent/30 text-white/70 hover:text-white text-xs font-bold uppercase tracking-widest flex items-center gap-2 transition-all"
            >
              <RefreshCw size={14} className={refreshing ? "animate-spin" : ""} />
              {refreshing ? "Refreshing..." : "Refresh"}
            </button>
            <button
              onClick={() => setShowAddForm(!showAddForm)}
              className="bg-accent text-black font-bold px-6 py-3 rounded-xl flex items-center gap-2 hover:scale-105 transition-all text-sm"
            >
              <Plus size={18} /> Add Product
            </button>
          </div>
        </div>

        {loading ? (
          <div className="flex flex-col items-center justify-center py-20 space-y-4">
            <RefreshCw size={40} className="text-accent animate-spin" />
            <p className="text-white/40 text-sm">Syncing catalog with platform index...</p>
          </div>
        ) : (
          <>
            {/* Stats */}
            <div className="grid grid-cols-2 lg:grid-cols-4 gap-4">
              {statItems.map((stat, idx) => {
                const Icon = stat.icon;
                return (
                  <motion.div
                    key={stat.label}
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ delay: idx * 0.1 }}
                    className="glass p-6 rounded-2xl border border-white/10"
                  >
                    <div className={`w-10 h-10 rounded-xl flex items-center justify-center mb-4 ${stat.color}`}>
                      <Icon size={20} />
                    </div>
                    <div className="text-white/40 text-xs font-bold uppercase tracking-widest mb-1">{stat.label}</div>
                    <div className="text-white text-2xl font-bold tracking-tighter">{stat.value}</div>
                  </motion.div>
                );
              })}
            </div>

            {/* Add Product Form Modal */}
            <AnimatePresence>
              {showAddForm && (
                <motion.div
                  initial={{ opacity: 0, y: -20 }}
                  animate={{ opacity: 1, y: 0 }}
                  exit={{ opacity: 0, y: -20 }}
                  className="glass p-8 rounded-3xl border border-accent/20 space-y-6"
                >
                  <h2 className="text-white text-xl font-bold">Add New Product to Catalog</h2>
                  <form onSubmit={handleAddProduct} className="grid md:grid-cols-2 gap-6">
                    <div className="space-y-4">
                      <div className="space-y-1">
                        <label className="text-white/40 text-xs uppercase font-bold tracking-widest">Product Name</label>
                        <input
                          type="text"
                          required
                          value={newProduct.name}
                          onChange={(e) => setNewProduct({ ...newProduct, name: e.target.value })}
                          className="w-full bg-white/5 border border-white/10 rounded-xl py-3 px-4 text-white outline-none focus:border-accent transition-all text-sm"
                          placeholder="e.g. Premium Burma Teak Planks"
                        />
                      </div>
                      <div className="space-y-1">
                        <label className="text-white/40 text-xs uppercase font-bold tracking-widest">Category</label>
                        <select
                          value={newProduct.category}
                          onChange={(e) => setNewProduct({ ...newProduct, category: e.target.value })}
                          className="w-full bg-white/5 border border-white/10 rounded-xl py-3 px-4 text-white outline-none focus:border-accent transition-all text-sm"
                        >
                          <option value="Cement">Cement</option>
                          <option value="Steel">Steel</option>
                          <option value="Wood">Wood</option>
                          <option value="Plumbing">Plumbing</option>
                          <option value="Electrical">Electrical</option>
                        </select>
                      </div>
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-1">
                          <label className="text-white/40 text-xs uppercase font-bold tracking-widest">Price (₹)</label>
                          <input
                            type="number"
                            required
                            value={newProduct.pricePerUnit || ""}
                            onChange={(e) => setNewProduct({ ...newProduct, pricePerUnit: Number(e.target.value) })}
                            className="w-full bg-white/5 border border-white/10 rounded-xl py-3 px-4 text-white outline-none focus:border-accent transition-all text-sm"
                            placeholder="e.g. 850"
                          />
                        </div>
                        <div className="space-y-1">
                          <label className="text-white/40 text-xs uppercase font-bold tracking-widest">Unit</label>
                          <input
                            type="text"
                            required
                            value={newProduct.unit}
                            onChange={(e) => setNewProduct({ ...newProduct, unit: e.target.value })}
                            className="w-full bg-white/5 border border-white/10 rounded-xl py-3 px-4 text-white outline-none focus:border-accent transition-all text-sm"
                            placeholder="e.g. Sheet, Sq.Ft., Bag"
                          />
                        </div>
                      </div>
                    </div>
                    <div className="space-y-4 flex flex-col justify-between">
                      <div className="space-y-1">
                        <label className="text-white/40 text-xs uppercase font-bold tracking-widest">Description</label>
                        <textarea
                          required
                          value={newProduct.description}
                          onChange={(e) => setNewProduct({ ...newProduct, description: e.target.value })}
                          className="w-full bg-white/5 border border-white/10 rounded-xl py-3 px-4 text-white outline-none focus:border-accent transition-all text-sm h-32 resize-none"
                          placeholder="Provide details about quality, dimensions, shipping..."
                        />
                      </div>
                      <div className="flex gap-4">
                        <button
                          type="button"
                          onClick={() => setShowAddForm(false)}
                          className="flex-1 border border-white/10 text-white py-3 rounded-xl text-sm font-bold hover:bg-white/5 transition-all"
                        >
                          Cancel
                        </button>
                        <button
                          type="submit"
                          disabled={addingProduct}
                          className="flex-1 bg-accent text-black py-3 rounded-xl text-sm font-bold hover:scale-105 transition-all disabled:opacity-50"
                        >
                          {addingProduct ? "Adding..." : "Add to Catalog"}
                        </button>
                      </div>
                    </div>
                  </form>
                </motion.div>
              )}
            </AnimatePresence>

            <div className="grid lg:grid-cols-12 gap-8">
              {/* Products */}
              <div className="lg:col-span-7 space-y-4">
                <h2 className="text-white text-xl font-bold">Product Catalog</h2>
                {products.length === 0 ? (
                  <div className="glass rounded-3xl border border-white/10 p-12 text-center space-y-4">
                    <div className="w-16 h-16 bg-white/5 rounded-2xl flex items-center justify-center text-white/30 mx-auto">
                      <Package size={30} />
                    </div>
                    <div className="space-y-1">
                      <h3 className="text-white font-bold text-lg">No products in catalog</h3>
                      <p className="text-white/40 text-sm max-w-md mx-auto">List your building materials or luxury fixtures to start accepting project enquiries.</p>
                    </div>
                  </div>
                ) : (
                  <div className="grid sm:grid-cols-2 gap-4">
                    {products.map((prod) => (
                      <div
                        key={prod.id}
                        className="glass rounded-2xl border border-white/10 overflow-hidden group hover:border-accent/20 transition-all"
                      >
                        <div className="aspect-[4/3] relative overflow-hidden bg-white/5">
                          <img
                            src={prod.imageUrl || "https://images.unsplash.com/photo-1533090161767-e6ffed986c88?q=80&w=2069&auto=format&fit=crop"}
                            alt={prod.name}
                            className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-700"
                          />
                          <div className="absolute top-3 right-3 bg-black/60 backdrop-blur-md px-2 py-1 rounded-lg text-accent text-xs font-bold">
                            {formatPrice(prod.pricePerUnit)}/{prod.unit}
                          </div>
                        </div>
                        <div className="p-4 space-y-2">
                          <div className="text-accent text-[10px] font-bold uppercase tracking-widest">{prod.category}</div>
                          <h3 className="text-white font-bold text-sm line-clamp-1">{prod.name}</h3>
                          <p className="text-white/40 text-xs line-clamp-2">{prod.description}</p>
                        </div>
                      </div>
                    ))}
                  </div>
                )}
              </div>

              {/* Enquiries */}
              <div className="lg:col-span-5 space-y-4">
                <h2 className="text-white text-xl font-bold">Recent Enquiries</h2>
                {enquiries.length === 0 ? (
                  <div className="glass rounded-3xl border border-white/10 p-12 text-center space-y-4">
                    <div className="w-16 h-16 bg-white/5 rounded-2xl flex items-center justify-center text-white/30 mx-auto">
                      <MessageSquare size={30} />
                    </div>
                    <div className="space-y-1">
                      <h3 className="text-white font-bold text-lg">No enquiries received</h3>
                      <p className="text-white/40 text-sm max-w-md mx-auto">Enquiries from users building homes will populate here automatically.</p>
                    </div>
                  </div>
                ) : (
                  <div className="space-y-3">
                    {enquiries.map((enq) => (
                      <div
                        key={enq.id}
                        className="glass p-5 rounded-2xl border border-white/10 space-y-3 hover:border-accent/20 transition-all"
                      >
                        <div className="flex justify-between items-start">
                          <div>
                            <div className="text-white font-bold text-sm">{enq.userName}</div>
                            <div className="text-white/40 text-xs">Re: Material Order</div>
                          </div>
                          <span
                            className={`text-[10px] font-bold px-2 py-0.5 rounded-full border uppercase tracking-widest ${
                              enq.status === "NEW" || enq.status === "PENDING"
                                ? "bg-blue-500/10 text-blue-500 border-blue-500/20"
                                : "bg-emerald-500/10 text-emerald-500 border-emerald-500/20"
                            }`}
                          >
                            {enq.status}
                          </span>
                        </div>
                        <p className="text-white/50 text-xs leading-relaxed">&ldquo;{enq.message}&rdquo;</p>
                        <div className="flex justify-between items-center pt-2 border-t border-white/5">
                          <span className="text-white/20 text-[10px]">
                            {new Date(enq.createdAt).toLocaleDateString()}
                          </span>
                          {enq.status === "NEW" && (
                            <button
                              onClick={() => handleReplyEnquiry(enq.id)}
                              className="text-accent text-xs font-bold flex items-center gap-1 hover:gap-2 transition-all"
                            >
                              Mark Solved <ArrowUpRight size={12} />
                            </button>
                          )}
                        </div>
                      </div>
                    ))}
                  </div>
                )}
              </div>
            </div>
          </>
        )}
      </div>
    </DashboardLayout>
  );
}
