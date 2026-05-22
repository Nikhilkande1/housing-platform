import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import ClientProviders from "@/components/ClientProviders";

const inter = Inter({
  variable: "--font-inter",
  subsets: ["latin"],
  display: "swap",
});

export const metadata: Metadata = {
  title: "Tejas Nirman | Architectural Excellence & Property Ecosystem",
  description:
    "The world's premier property ecosystem. Build luxury homes, lease for ROI, connect with global vendors, and get financing — all in one premium platform.",
  keywords: [
    "luxury homes",
    "property platform",
    "build home",
    "lease property",
    "vendor marketplace",
    "home loan",
    "smart home",
    "architecture",
  ],
  openGraph: {
    title: "Tejas Nirman | Build. Lease. Earn.",
    description: "All-in-one property ecosystem for luxury homes",
    type: "website",
  },
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en" className={`${inter.variable} h-full antialiased`}>
      <body className="min-h-full flex flex-col font-sans">
        <ClientProviders>{children}</ClientProviders>
      </body>
    </html>
  );
}
