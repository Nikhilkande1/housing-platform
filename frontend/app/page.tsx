import Navbar from "@/components/Navbar";
import Hero from "@/components/Hero";
import ConfiguratorPreview from "@/components/ConfiguratorPreview";
import { StyleGrid, SectionHeader } from "@/components/StyleGrid";
import { LeaseShowcase } from "@/components/LeaseShowcase";
import SmartInfraSection from "@/components/SmartInfraSection";
import TestimonialsSection from "@/components/TestimonialsSection";

export default function Home() {
  return (
    <main className="relative bg-black min-h-screen">
      <Navbar />
      <Hero />

      {/* Configurator Section */}
      <section className="bg-black py-32 px-6">
        <div className="max-w-7xl mx-auto">
          <ConfiguratorPreview />
        </div>
      </section>

      {/* Styles Showcase */}
      <section className="bg-black py-32 px-6 border-t border-white/5">
        <div className="max-w-7xl mx-auto">
          <SectionHeader
            tag="Curated Styles"
            title="Fusion Architectures for the Modern Era."
            subtitle="Our design selection blends global luxury with traditional Indian elements,
              bringing timeless aesthetics to your personalized property."
          />
          <StyleGrid />
        </div>
      </section>

      {/* Smart Infrastructure */}
      <section className="bg-black py-32 px-6 border-t border-white/5">
        <div className="max-w-7xl mx-auto">
          <SmartInfraSection />
        </div>
      </section>

      {/* Lease Showcase */}
      <section className="bg-black py-32 px-6 border-t border-white/5">
        <div className="max-w-7xl mx-auto">
          <SectionHeader
            align="center"
            tag="Lease & Earn"
            title="Monetize Your Masterpiece."
            subtitle="Build with the intent to earn. We offer specialized leasing models
              from Airbnb hosting to high-yield commercial office spaces."
          />
          <LeaseShowcase />
        </div>
      </section>

      {/* Testimonials */}
      <section className="bg-black py-32 px-6 border-t border-white/5">
        <div className="max-w-7xl mx-auto">
          <TestimonialsSection />
        </div>
      </section>

      {/* Visual CTA Section */}
      <section className="bg-black py-40 px-6 overflow-hidden relative">
        <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-full h-[500px] bg-accent/5 blur-[150px] rounded-full" />

        <div className="max-w-4xl mx-auto text-center relative z-10">
          <h2 className="text-white text-5xl md:text-7xl font-bold mb-10 tracking-tighter italic">
            &ldquo;Your legacy, built with <span className="text-accent underline decoration-accent/30 underline-offset-8">Tejas Nirman</span>.&rdquo;
          </h2>
          <div className="flex flex-col sm:flex-row gap-6 justify-center">
            <a href="/auth/register" className="bg-accent text-black font-bold px-12 py-5 rounded-2xl hover:scale-105 transition-transform text-lg shadow-[0_0_50px_rgba(197,168,128,0.3)] text-center">
              Get Started Now
            </a>
            <a href="/build" className="bg-white/5 backdrop-blur-xl border border-white/10 text-white font-bold px-12 py-5 rounded-2xl hover:bg-white/10 transition-colors text-lg text-center">
              Explore Configurator
            </a>
          </div>
        </div>
      </section>

      <footer className="bg-black border-t border-white/10 py-20 px-6">
        <div className="max-w-7xl mx-auto">
          <div className="grid md:grid-cols-4 gap-12 mb-20">
            <div className="col-span-2">
              <div className="flex items-center gap-2 mb-8">
                <div className="w-10 h-10 bg-accent rounded-lg flex items-center justify-center">
                  <span className="text-black font-bold text-xl">TN</span>
                </div>
                <span className="text-white font-display text-2xl font-semibold tracking-tight">
                  Tejas <span className="text-accent">Nirman</span>
                </span>
              </div>
              <p className="text-white/40 max-w-sm mb-8 leading-relaxed">
                The world&apos;s premier property ecosystem.
                Integrating architecture, finance, and passive income in a single premium experience.
              </p>
              <div className="flex gap-4">
                {[1, 2, 3, 4].map((i) => (
                  <div key={i} className="w-10 h-10 bg-white/5 rounded-full border border-white/10 hover:border-accent transition-colors cursor-pointer" />
                ))}
              </div>
            </div>
            <div>
              <h4 className="text-white font-bold mb-6 uppercase text-xs tracking-widest text-accent">Navigation</h4>
              <ul className="space-y-4 text-white/40 text-sm">
                <li><a href="/build" className="hover:text-accent transition-colors">Build Configurator</a></li>
                <li><a href="/lease" className="hover:text-accent transition-colors">Lease Marketplace</a></li>
                <li><a href="/marketplace" className="hover:text-accent transition-colors">Vendor Directory</a></li>
                <li><a href="/finance" className="hover:text-accent transition-colors">Loan Eligibility</a></li>
                <li><a href="/payment" className="hover:text-accent transition-colors">Payments</a></li>
              </ul>
            </div>
            <div>
              <h4 className="text-white font-bold mb-6 uppercase text-xs tracking-widest text-accent">Company</h4>
              <ul className="space-y-4 text-white/40 text-sm">
                <li><a href="#" className="hover:text-accent transition-colors">About Legacy</a></li>
                <li><a href="#" className="hover:text-accent transition-colors">Sustainability</a></li>
                <li><a href="#" className="hover:text-accent transition-colors">Global Partners</a></li>
                <li><a href="#" className="hover:text-accent transition-colors">Contact</a></li>
              </ul>
            </div>
          </div>
          <div className="flex flex-col md:flex-row justify-between items-center gap-8 pt-10 border-t border-white/5">
            <div className="text-white/20 text-[10px] font-bold uppercase tracking-[0.3em]">
              © 2024 Tejas Nirman. All rights reserved. Architecting the future.
            </div>
            <div className="flex gap-8 text-white/20 text-[10px] font-bold uppercase tracking-widest">
              <a href="#" className="hover:text-white transition-colors">Privacy</a>
              <a href="#" className="hover:text-white transition-colors">Terms</a>
              <a href="#" className="hover:text-white transition-colors">Luxury Policy</a>
            </div>
          </div>
        </div>
      </footer>
    </main>
  );
}
