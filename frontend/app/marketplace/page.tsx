"use client";

import React, { useState, useEffect } from "react";
import Navbar from "@/components/Navbar";
import { motion } from "framer-motion";
import { 
  Search, Filter, Star, Phone, 
  Package, Truck, HardHat, Info,
  ChevronRight, ArrowRight
} from "lucide-react";
import { cn } from "@/utils/cn";

const CATEGORIES = [
  { id: "all", label: "All Items", icon: Package },
  { id: "materials", label: "Raw Materials", icon: Truck },
  { id: "finishing", label: "Finishing & Tiles", icon: Info },
  { id: "appliances", label: "Appliances", icon: Info },
  { id: "services", label: "Contractors", icon: HardHat },
];

const VENDORS = [
  {
    id: 1,
    name: "Elite Teak & Timber",
    category: "Materials",
    rating: 4.8,
    reviews: 124,
    tags: ["Burma Teak", "Rosewood"],
    price: "Premium",
    img: "https://images.unsplash.com/photo-1533090161767-e6ffed986c88?q=80&w=2069&auto=format&fit=crop",
  },
  {
    id: 2,
    name: "Italian Marble Hub",
    category: "Finishing",
    rating: 4.9,
    reviews: 86,
    tags: ["Statuary", "Carrara"],
    price: "Luxury",
    img: "https://images.unsplash.com/photo-1600607687920-4e2a09cf159d?q=80&w=2070&auto=format&fit=crop",
  },
  {
    id: 3,
    name: "SmartBose Automations",
    category: "Appliances",
    rating: 4.7,
    reviews: 210,
    tags: ["Smart Lock", "Lighting"],
    price: "Advanced",
    img: "https://images.unsplash.com/photo-1558002038-1055907df827?q=80&w=2070&auto=format&fit=crop",
  },
  {
    id: 4,
    name: "Supreme Concrete Ltd",
    category: "Materials",
    rating: 4.5,
    reviews: 432,
    tags: ["Cement", "Steel Bars"],
    price: "Value",
    img: "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?q=80&w=2084&auto=format&fit=crop",
  },
];

const MOCK_MATERIALS = [
  {
    id: 101,
    name: "UltraTech Premium Cement",
    category: "Materials",
    price: "₹450 / Bag",
    vendorName: "Supreme Concrete Ltd",
    img: "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?q=80&w=2084&auto=format&fit=crop",
    tags: ["OPC Cement", "High Strength"]
  },
  {
    id: 102,
    name: "Italian Statuario Marble",
    category: "Finishing",
    price: "₹350 / sqft",
    vendorName: "Italian Marble Hub",
    img: "https://images.unsplash.com/photo-1600607687920-4e2a09cf159d?q=80&w=2070&auto=format&fit=crop",
    tags: ["Marble", "Luxury Flooring"]
  }
];

export default function MarketplacePage() {
  const [activeCategory, setActiveCategory] = useState("all");
  const [searchQuery, setSearchQuery] = useState("");
  const [vendors, setVendors] = useState<any[]>([]);
  const [materials, setMaterials] = useState<any[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function loadData() {
      try {
        const [vendorRes, materialRes] = await Promise.all([
          fetch("http://localhost:8080/api/vendors").then(r => r.ok ? r.json() : []),
          fetch("http://localhost:8080/api/materials").then(r => r.ok ? r.json() : [])
        ]);
        setVendors(vendorRes);
        setMaterials(materialRes);
      } catch (e) {
        console.error("Failed to load marketplace data", e);
      } finally {
        setLoading(false);
      }
    }
    loadData();
  }, []);

  const displayVendors = (vendors.length > 0 ? vendors : VENDORS).map(v => ({
    id: v.id,
    name: v.companyName || v.name,
    category: v.category || "Materials",
    rating: v.rating || 4.8,
    reviews: v.reviewCount || 100,
    tags: v.tags || ["Premium", "Verified"],
    price: v.priceRange || "Premium",
    img: v.imageUrl || "https://images.unsplash.com/photo-1533090161767-e6ffed986c88?q=80&w=2069&auto=format&fit=crop",
    vendorName: undefined as string | undefined,
    isMaterial: false
  }));

  const displayMaterials = (materials.length > 0 ? materials : MOCK_MATERIALS).map(m => ({
    id: `material-${m.id}`,
    name: m.name,
    category: m.category || "Materials",
    rating: 4.7,
    reviews: 50,
    tags: [m.category || "Material", m.unit || "Unit"],
    price: m.pricePerUnit ? `₹${m.pricePerUnit} / ${m.unit}` : (m as any).price || "Contact for Price",
    img: m.imageUrl || "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?q=80&w=2084&auto=format&fit=crop",
    vendorName: m.vendorName || "Apex Building Materials",
    isMaterial: true
  }));

  const allItems = [...displayVendors, ...displayMaterials];

  const filteredItems = allItems.filter(item => {
    const matchesSearch = item.name.toLowerCase().includes(searchQuery.toLowerCase()) || 
      item.category.toLowerCase().includes(searchQuery.toLowerCase());
    
    if (!matchesSearch) return false;

    if (activeCategory === "all") return true;
    if (activeCategory === "materials") return item.isMaterial && (item.category.toLowerCase().includes("material") || item.category.toLowerCase().includes("cement") || item.category.toLowerCase().includes("steel"));
    if (activeCategory === "finishing") return item.isMaterial && (item.category.toLowerCase().includes("finish") || item.category.toLowerCase().includes("tile") || item.category.toLowerCase().includes("wood") || item.category.toLowerCase().includes("plumbing"));
    if (activeCategory === "appliances") return item.isMaterial && (item.category.toLowerCase().includes("appliance") || item.category.toLowerCase().includes("automation") || item.category.toLowerCase().includes("smart"));
    if (activeCategory === "services") return !item.isMaterial;

    return item.category.toLowerCase() === activeCategory.toLowerCase();
  });

  return (
    <main className="min-h-screen bg-black pt-24 px-6 md:px-12 pb-20">
      <Navbar />

      <div className="max-w-7xl mx-auto space-y-12">
        {/* Header Section */}
        <div className="flex flex-col md:flex-row justify-between items-center gap-8">
           <div className="space-y-4 text-center md:text-left">
              <h1 className="text-white text-5xl font-bold tracking-tight">Vendor <span className="text-accent italic">Marketplace</span>.</h1>
              <p className="text-white/40 text-lg">Source the world's finest materials and construction services directly.</p>
           </div>
           
           <div className="relative w-full max-w-md">
              <Search className="absolute left-4 top-1/2 -translate-y-1/2 text-white/20" size={20} />
              <input 
                type="text" 
                placeholder="Search materials, brands or vendors..." 
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                className="w-full bg-white/5 border border-white/10 rounded-2xl py-4 pl-12 pr-4 text-white outline-none focus:border-accent transition-all"
              />
           </div>
        </div>

        {/* Categories Bar */}
        <div className="flex gap-4 overflow-x-auto pb-4 scrollbar-hide">
           {CATEGORIES.map(cat => {
              const Icon = cat.icon;
              return (
                <button
                  key={cat.id}
                  onClick={() => setActiveCategory(cat.id)}
                  className={cn(
                    "flex items-center gap-3 px-6 py-3 rounded-xl border border-white/5 transition-all whitespace-nowrap",
                    activeCategory === cat.id 
                      ? "bg-accent text-black font-bold shadow-[0_0_20px_rgba(197,168,128,0.3)]" 
                      : "bg-white/5 text-white/40 hover:bg-white/10"
                  )}
                >
                  <Icon size={18} />
                  {cat.label}
                </button>
              );
           })}
        </div>

        {/* Grid Section */}
        {loading ? (
          <div className="flex justify-center items-center py-20 text-accent font-bold">
            Loading Premium Assets...
          </div>
        ) : filteredItems.length === 0 ? (
          <div className="flex justify-center items-center py-20 text-white/40 font-bold">
            No premium products or partners found matching your selection.
          </div>
        ) : (
          <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-8">
             {filteredItems.map((item, idx) => (
                <motion.div
                  key={item.id}
                  initial={{ opacity: 0, scale: 0.9 }}
                  animate={{ opacity: 1, scale: 1 }}
                  transition={{ delay: idx * 0.05 }}
                  whileHover={{ y: -8 }}
                  className="glass rounded-3xl border border-white/10 overflow-hidden group cursor-pointer"
                >
                   <div className="aspect-video relative overflow-hidden">
                      <img 
                        src={item.img} 
                        alt={item.name} 
                        className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-700" 
                      />
                      <div className="absolute top-4 right-4 bg-black/60 backdrop-blur-md px-3 py-1 rounded-full border border-white/10 flex items-center gap-1 text-white text-[10px] font-bold">
                         <Star size={10} className="text-accent" fill="currentColor" />
                         {item.rating}
                      </div>
                   </div>

                   <div className="p-6 space-y-4">
                      <div className="flex justify-between items-start">
                         <div>
                            <div className="text-accent text-[10px] font-bold uppercase tracking-widest mb-1">
                              {item.isMaterial ? "Raw Material" : item.category}
                            </div>
                            <h3 className="text-white font-bold text-lg leading-snug line-clamp-1">{item.name}</h3>
                            {item.isMaterial && (
                              <div className="text-white/40 text-xs mt-1">Vendor: {item.vendorName}</div>
                            )}
                         </div>
                      </div>

                      <div className="flex flex-wrap gap-2">
                         {item.tags.map((tag: any) => (
                            <span key={tag} className="bg-white/5 text-white/40 text-[10px] px-2 py-0.5 rounded border border-white/5">{tag}</span>
                         ))}
                      </div>

                      <div className="pt-4 flex items-center justify-between border-t border-white/5">
                         <div className="text-white/20 text-xs font-bold uppercase tracking-widest">
                           {item.isMaterial ? "Price" : `${item.price} Range`}
                         </div>
                         <div className="text-accent flex items-center gap-2 text-xs font-bold uppercase tracking-[0.2em] group-hover:gap-3 transition-all">
                            {item.isMaterial ? item.price : "Contact"} <ArrowRight size={14} />
                         </div>
                      </div>
                   </div>
                </motion.div>
             ))}
          </div>
        )}

        {/* Vendor Registration CTA */}
        <div className="mt-20 glass p-12 rounded-[3.5rem] border border-white/10 flex flex-col md:flex-row items-center justify-between gap-8 relative overflow-hidden">
           <div className="space-y-4 relative z-10 text-center md:text-left">
              <h2 className="text-white text-3xl font-bold italic">Are you a manufacturer or service provider?</h2>
              <p className="text-white/40 max-w-md">Join India's most premium property ecosystem and scale your business to ultra-luxury projects.</p>
           </div>
           <button className="bg-white text-black font-bold px-10 py-5 rounded-2xl hover:bg-accent transition-colors relative z-10 whitespace-nowrap">
              Become a Partner Vendor
           </button>
           <div className="absolute -right-20 -bottom-20 w-80 h-80 bg-accent/10 blur-[100px] rounded-full" />
        </div>
      </div>
    </main>
  );
}
