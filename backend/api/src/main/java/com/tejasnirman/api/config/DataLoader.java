package com.tejasnirman.api.config;

import com.tejasnirman.api.model.*;
import com.tejasnirman.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final FeatureRepository featureRepository;
    private final VendorRepository vendorRepository;
    private final BankRepository bankRepository;
    private final PropertyRepository propertyRepository;
    private final MaterialRepository materialRepository;
    private final LeasePropertyRepository leasePropertyRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository,
                      FeatureRepository featureRepository,
                      VendorRepository vendorRepository,
                      BankRepository bankRepository,
                      PropertyRepository propertyRepository,
                      MaterialRepository materialRepository,
                      LeasePropertyRepository leasePropertyRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.featureRepository = featureRepository;
        this.vendorRepository = vendorRepository;
        this.bankRepository = bankRepository;
        this.propertyRepository = propertyRepository;
        this.materialRepository = materialRepository;
        this.leasePropertyRepository = leasePropertyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            System.out.println("Database already seeded. Skipping seeder.");
            return;
        }

        System.out.println("Seeding database with Tejas Nirman platform data...");

        // 1. Seed Users
        String encodedPassword = passwordEncoder.encode("password");

        User userBuyer = new User("Rahul Sharma", "user@example.com", encodedPassword, Role.ROLE_USER);
        userBuyer.setPhoneNumber("+919876543210");
        userRepository.save(userBuyer);

        User userOwner = new User("Tejas Developers", "owner@example.com", encodedPassword, Role.ROLE_OWNER);
        userOwner.setPhoneNumber("+919999888877");
        userRepository.save(userOwner);

        User userVendor = new User("Apex Materials", "vendor@example.com", encodedPassword, Role.ROLE_VENDOR);
        userVendor.setPhoneNumber("+918888777766");
        userRepository.save(userVendor);

        User userBank = new User("HDFC Bank Agent", "bank@example.com", encodedPassword, Role.ROLE_BANK);
        userBank.setPhoneNumber("+917777666655");
        userRepository.save(userBank);

        System.out.println("Users seeded successfully.");

        // 2. Seed Configurator Features
        List<Feature> features = new ArrayList<>();

        // Flooring
        features.add(new Feature(null, "Italian Marble", FeatureCategory.FLOORING, new BigDecimal("450000"), "Premium quality statuario Italian marble flooring", "/images/flooring-marble.jpg"));
        features.add(new Feature(null, "Vitrified Tiles", FeatureCategory.FLOORING, new BigDecimal("150000"), "Double charged heavy-duty vitrified tile flooring", "/images/flooring-tiles.jpg"));
        features.add(new Feature(null, "Wooden Laminate", FeatureCategory.FLOORING, new BigDecimal("250000"), "High density AC4 grade anti-scratch wooden laminate flooring", "/images/flooring-wood.jpg"));

        // Kitchen
        features.add(new Feature(null, "Gourmet Modular Kitchen", FeatureCategory.KITCHEN, new BigDecimal("350000"), "L-shaped modular kitchen with soft-close drawers and acrylic finish", "/images/kitchen-gourmet.jpg"));
        features.add(new Feature(null, "Minimalist Kitchen", FeatureCategory.KITCHEN, new BigDecimal("200000"), "Simple laminate finish modular kitchen with SS accessories", "/images/kitchen-minimalist.jpg"));

        // Bathroom
        features.add(new Feature(null, "Luxury Bath Suite", FeatureCategory.BATHROOM, new BigDecimal("200000"), "Kohler/Jaquar sanitaryware with wall-hung WC and glass shower cubicle", "/images/bathroom-luxury.jpg"));
        features.add(new Feature(null, "Standard Bath Suite", FeatureCategory.BATHROOM, new BigDecimal("80000"), "Jaquar Essco fittings with standard sanitaryware", "/images/bathroom-standard.jpg"));

        // Luxury (including Swimming Pool!)
        features.add(new Feature(null, "Swimming Pool", FeatureCategory.LUXURY, new BigDecimal("800000"), "Luxury custom-built inground swimming pool with filtration and lighting systems", "/images/luxury-pool.jpg"));
        features.add(new Feature(null, "Home Theatre", FeatureCategory.LUXURY, new BigDecimal("500000"), "Acoustically treated room with 7.1 Dolby Atmos sound and projector setup", "/images/luxury-theatre.jpg"));
        features.add(new Feature(null, "Private Gym", FeatureCategory.LUXURY, new BigDecimal("300000"), "Equipped home gym with rubberized flooring and climate control", "/images/luxury-gym.jpg"));

        // Smart Home
        features.add(new Feature(null, "Smart Automation Hub", FeatureCategory.SMART, new BigDecimal("150000"), "Integrated smart lighting, automated curtains, and voice control via Alexa/Google", "/images/smart-hub.jpg"));
        features.add(new Feature(null, "Biometric Security Lock", FeatureCategory.SMART, new BigDecimal("40000"), "Fingerprint, passcode, and RFID card smart entry door lock", "/images/smart-lock.jpg"));

        // Doors
        features.add(new Feature(null, "Teak Wood Main Door", FeatureCategory.DOOR, new BigDecimal("75000"), "Solid Burma teak wood front door with premium brass hardware", "/images/door-teak.jpg"));
        features.add(new Feature(null, "Flush Bedroom Doors", FeatureCategory.DOOR, new BigDecimal("30000"), "Waterproof flush doors with veneer finish for bedrooms", "/images/door-flush.jpg"));

        // Utility (including Borewell System!)
        features.add(new Feature(null, "Borewell System", FeatureCategory.UTILITY, new BigDecimal("150000"), "Deep borewell system with high-yield submersible pump and automatic controller", "/images/utility-borewell.jpg"));
        features.add(new Feature(null, "Solar Power Plant 5kW", FeatureCategory.UTILITY, new BigDecimal("250000"), "Grid-tied solar rooftop power system with monocrystalline panels", "/images/utility-solar.jpg"));
        features.add(new Feature(null, "Rainwater Harvesting", FeatureCategory.UTILITY, new BigDecimal("60000"), "Eco-friendly filtration pit for groundwater recharging", "/images/utility-rainwater.jpg"));

        // Facade
        features.add(new Feature(null, "Glass Facade", FeatureCategory.FACADE, new BigDecimal("400000"), "Stunning structural glass glazing with spider fittings for modern aesthetic", "/images/facade-glass.jpg"));
        features.add(new Feature(null, "Cladding Panels", FeatureCategory.FACADE, new BigDecimal("250000"), "Weatherproof HPL exterior wall cladding panels", "/images/facade-cladding.jpg"));

        // Infrastructure
        features.add(new Feature(null, "Earthquake Resistant Frame", FeatureCategory.INFRASTRUCTURE, new BigDecimal("600000"), "Heavy duty Fe550 steel reinforcement with concrete shear walls", "/images/infra-earthquake.jpg"));
        features.add(new Feature(null, "Thermal Insulation", FeatureCategory.INFRASTRUCTURE, new BigDecimal("180000"), "Roof and wall insulation to reduce cooling load", "/images/infra-insulation.jpg"));

        featureRepository.saveAll(features);
        System.out.println("Features seeded successfully.");

        // 3. Seed Vendor Profiles
        Vendor vendor = new Vendor();
        vendor.setUser(userVendor);
        vendor.setCompanyName("Apex Building Materials");
        vendor.setCategory("Contractor & Supply");
        vendor.setDescription("Top-tier concrete, cement, steel rods, and structural fabrication suppliers.");
        vendor.setLocation("Mumbai, MH");
        vendor.setImageUrl("https://images.unsplash.com/photo-1581094288338-2314dddb7ecc?w=800&auto=format&fit=crop&q=60");
        vendor.setIsVerified(true);
        vendor.setRating(4.8);
        vendor.setReviewCount(142);
        Vendor savedVendor1 = vendorRepository.save(vendor);

        User userVendor2 = new User("Elite Interiors", "vendor2@example.com", encodedPassword, Role.ROLE_VENDOR);
        userRepository.save(userVendor2);
        Vendor vendor2 = new Vendor();
        vendor2.setUser(userVendor2);
        vendor2.setCompanyName("Elite Interiors Ltd");
        vendor2.setCategory("Interior Designer");
        vendor2.setDescription("Bespoke modular kitchens, Italian marble fitments, luxury wood paneling, and interior landscaping.");
        vendor2.setLocation("Bangalore, KA");
        vendor2.setImageUrl("https://images.unsplash.com/photo-1618221195710-dd6b41faaea6?w=800&auto=format&fit=crop&q=60");
        vendor2.setIsVerified(true);
        vendor2.setRating(4.9);
        vendor2.setReviewCount(88);
        Vendor savedVendor2 = vendorRepository.save(vendor2);

        System.out.println("Vendors seeded successfully.");

        // 4. Seed Materials
        List<Material> materials = new ArrayList<>();
        materials.add(new Material(null, savedVendor1, "UltraTech Premium Cement", "Cement", new BigDecimal("450"), "Bag (50kg)", "High-strength OPC cement for foundation and column casting.", "/images/mat-cement.jpg"));
        materials.add(new Material(null, savedVendor1, "TMT Steel Rebars Fe550", "Steel", new BigDecimal("65000"), "Ton", "Corrosion resistant thermos-mechanically treated reinforcement bars.", "/images/mat-steel.jpg"));
        materials.add(new Material(null, savedVendor2, "Premium Teak Laminates", "Wood", new BigDecimal("2200"), "Sheet", "Waterproof designer veneer laminates for wardrobe and wall paneling.", "/images/mat-wood.jpg"));
        materials.add(new Material(null, savedVendor2, "Jaquar Chrome Mixer", "Plumbing", new BigDecimal("4800"), "Unit", "Quarter-turn high flow basin mixer with chrome finish.", "/images/mat-plumbing.jpg"));
        materialRepository.saveAll(materials);

        System.out.println("Materials seeded successfully.");

        // 5. Seed Banks
        Bank bank1 = new Bank();
        bank1.setUser(userBank);
        bank1.setBankName("HDFC Home Loans");
        bank1.setInterestRate(8.35);
        bank1.setMaxLoanAmount(new BigDecimal("15000000"));
        bank1.setProcessingFee(0.25);
        bank1.setFeatures("Instant digital sanction, Nil prepayment charges, flexible tenure up to 30 years.");
        bank1.setLogoUrl("https://logo.clearbit.com/hdfcbank.com");
        bankRepository.save(bank1);

        User userBank2 = new User("SBI Loan Officer", "sbi@example.com", encodedPassword, Role.ROLE_BANK);
        userRepository.save(userBank2);
        Bank bank2 = new Bank();
        bank2.setUser(userBank2);
        bank2.setBankName("SBI Housing Finance");
        bank2.setInterestRate(8.50);
        bank2.setMaxLoanAmount(new BigDecimal("20000000"));
        bank2.setProcessingFee(0.15);
        bank2.setFeatures("Lowest interest rates, special concession for women borrowers, zero hidden charges.");
        bank2.setLogoUrl("https://logo.clearbit.com/sbi.co.in");
        bankRepository.save(bank2);

        System.out.println("Banks seeded successfully.");

        // 6. Seed Properties & Lease Properties
        Property prop1 = new Property();
        prop1.setOwner(userOwner);
        prop1.setTitle("Grand Tejas Villa");
        prop1.setDescription("Stunning 4 BHK luxury villa equipped with a gorgeous Swimming Pool, premium Borewell System for uninterrupted water supply, and smart automation.");
        prop1.setLocation("Lonavala, Pune");
        prop1.setType(PropertyType.VILLA);
        prop1.setPrice(new BigDecimal("25000000"));
        prop1.setMonthlyRent(new BigDecimal("120000"));
        prop1.setEstimatedRoi(12.5);
        prop1.setOccupancyRate(90.0);
        prop1.setIsVerified(true);
        prop1.setImageUrl("https://images.unsplash.com/photo-1613490493576-7fde63acd811?w=800&auto=format&fit=crop&q=60");
        Property savedProp1 = propertyRepository.save(prop1);

        Property prop2 = new Property();
        prop2.setOwner(userOwner);
        prop2.setTitle("Tejas Commercial Plaza");
        prop2.setDescription("State-of-the-art office spaces in a high-growth IT hub. Features glass facade, premium insulation, and 24/7 security.");
        prop2.setLocation("Hitec City, Hyderabad");
        prop2.setType(PropertyType.OFFICE);
        prop2.setPrice(new BigDecimal("45000000"));
        prop2.setMonthlyRent(new BigDecimal("350000"));
        prop2.setEstimatedRoi(14.2);
        prop2.setOccupancyRate(95.0);
        prop2.setIsVerified(true);
        prop2.setImageUrl("https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?w=800&auto=format&fit=crop&q=60");
        Property savedProp2 = propertyRepository.save(prop2);

        System.out.println("Properties seeded successfully.");

        // Seed Lease Listings
        LeaseProperty lease1 = new LeaseProperty();
        lease1.setProperty(savedProp1);
        lease1.setLeaseType(LeaseType.RENTAL);
        lease1.setMonthlyIncome(new BigDecimal("120000"));
        lease1.setOccupancyRate(90.0);
        lease1.setIsActive(true);
        leasePropertyRepository.save(lease1);

        LeaseProperty lease2 = new LeaseProperty();
        lease2.setProperty(savedProp2);
        lease2.setLeaseType(LeaseType.OFFICE);
        lease2.setMonthlyIncome(new BigDecimal("350000"));
        lease2.setOccupancyRate(95.0);
        lease2.setIsActive(true);
        leasePropertyRepository.save(lease2);

        System.out.println("Lease listings seeded successfully.");
        System.out.println("Database seeding completed successfully!");
    }
}
