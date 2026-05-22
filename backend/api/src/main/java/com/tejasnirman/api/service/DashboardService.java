package com.tejasnirman.api.service;

import com.tejasnirman.api.dto.DashboardStatsDTO;
import com.tejasnirman.api.model.*;
import com.tejasnirman.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {

    private final ProjectRepository projectRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final EnquiryRepository enquiryRepository;
    private final LoanRepository loanRepository;
    private final PropertyRepository propertyRepository;
    private final VendorRepository vendorRepository;
    private final BankRepository bankRepository;

    @Autowired
    public DashboardService(ProjectRepository projectRepository,
                            BookingRepository bookingRepository,
                            PaymentRepository paymentRepository,
                            EnquiryRepository enquiryRepository,
                            LoanRepository loanRepository,
                            PropertyRepository propertyRepository,
                            VendorRepository vendorRepository,
                            BankRepository bankRepository) {
        this.projectRepository = projectRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.enquiryRepository = enquiryRepository;
        this.loanRepository = loanRepository;
        this.propertyRepository = propertyRepository;
        this.vendorRepository = vendorRepository;
        this.bankRepository = bankRepository;
    }

    public DashboardStatsDTO getStats(Long userId, String roleName) {
        DashboardStatsDTO stats = new DashboardStatsDTO();
        String role = roleName.toUpperCase().replace("ROLE_", "");

        if ("USER".equals(role)) {
            // Stats for regular user / buyer
            List<Project> myProjects = projectRepository.findByUserId(userId);
            stats.setActiveProjects((int) myProjects.stream().filter(p -> p.getStatus() != ProjectStatus.COMPLETED).count());
            
            List<Booking> myBookings = bookingRepository.findByUserId(userId);
            stats.setTotalBookings(myBookings.size());

            List<Payment> myPayments = paymentRepository.findByUserId(userId);
            BigDecimal totalPaid = myPayments.stream()
                    .map(Payment::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            stats.setTotalPayments(totalPaid);

            List<Enquiry> myEnquiries = enquiryRepository.findByUserId(userId);
            stats.setPendingEnquiries((int) myEnquiries.stream().filter(e -> e.getStatus() == EnquiryStatus.NEW).count());

            List<Loan> myLoans = loanRepository.findByUserId(userId);
            stats.setPendingLoans((int) myLoans.stream().filter(l -> l.getStatus() == LoanStatus.PENDING).count());

            stats.setProperties(propertyRepository.findByIsVerifiedTrue().size());
            stats.setEstimatedRoi(12.5); // Default display ROI
        } 
        else if ("OWNER".equals(role)) {
            // Stats for developer / owner
            List<Property> myProperties = propertyRepository.findByOwnerId(userId);
            stats.setProperties(myProperties.size());

            int bookingsCount = 0;
            BigDecimal paymentsSum = BigDecimal.ZERO;
            int pendingEnqCount = 0;

            for (Property p : myProperties) {
                List<Booking> bookings = bookingRepository.findByPropertyId(p.getId());
                bookingsCount += bookings.size();

                List<Enquiry> enquiries = enquiryRepository.findByPropertyId(p.getId());
                pendingEnqCount += enquiries.stream().filter(e -> e.getStatus() == EnquiryStatus.NEW).count();
            }

            stats.setTotalBookings(bookingsCount);
            stats.setPendingEnquiries(pendingEnqCount);

            List<Payment> allPayments = paymentRepository.findAll();
            for (Payment p : allPayments) {
                if (p.getProperty() != null && p.getProperty().getOwner() != null && p.getProperty().getOwner().getId().equals(userId)) {
                    paymentsSum = paymentsSum.add(p.getAmount());
                }
            }
            stats.setTotalPayments(paymentsSum);

            double avgRoi = myProperties.stream()
                    .mapToDouble(p -> p.getEstimatedRoi() != null ? p.getEstimatedRoi() : 0.0)
                    .average()
                    .orElse(10.0);
            stats.setEstimatedRoi(avgRoi);
            stats.setActiveProjects(projectRepository.findAll().size());
            stats.setPendingLoans(0);
        }
        else if ("VENDOR".equals(role)) {
            // Stats for vendor
            stats.setActiveProjects(projectRepository.findAll().size());
            
            Vendor vendor = vendorRepository.findByUserId(userId).orElse(null);
            if (vendor != null) {
                List<Enquiry> enquiries = enquiryRepository.findByVendorId(vendor.getId());
                stats.setPendingEnquiries((int) enquiries.stream().filter(e -> e.getStatus() == EnquiryStatus.NEW).count());
                stats.setTotalBookings(enquiries.size());
            } else {
                stats.setPendingEnquiries(0);
                stats.setTotalBookings(0);
            }
            stats.setTotalPayments(BigDecimal.ZERO);
            stats.setPendingLoans(0);
            stats.setProperties(0);
            stats.setEstimatedRoi(0.0);
        }
        else if ("BANK".equals(role)) {
            // Stats for banking partner
            Bank bank = bankRepository.findByUserId(userId).orElse(null);
            if (bank != null) {
                List<Loan> bankLoans = loanRepository.findByBankId(bank.getId());
                stats.setPendingLoans((int) bankLoans.stream().filter(l -> l.getStatus() == LoanStatus.PENDING).count());
                stats.setTotalBookings(bankLoans.size());
                
                BigDecimal totalSum = bankLoans.stream()
                        .filter(l -> l.getStatus() == LoanStatus.APPROVED || l.getStatus() == LoanStatus.DISBURSED)
                        .map(Loan::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                stats.setTotalPayments(totalSum);
            } else {
                stats.setPendingLoans(0);
                stats.setTotalBookings(0);
                stats.setTotalPayments(BigDecimal.ZERO);
            }
            stats.setActiveProjects(0);
            stats.setPendingEnquiries(0);
            stats.setProperties(0);
            stats.setEstimatedRoi(0.0);
        }
        else {
            // fallback generic/admin stats
            stats.setActiveProjects(projectRepository.findAll().size());
            stats.setTotalBookings(bookingRepository.findAll().size());
            
            BigDecimal sum = paymentRepository.findAll().stream()
                    .map(Payment::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            stats.setTotalPayments(sum);
            
            stats.setPendingEnquiries(enquiryRepository.findAll().size());
            stats.setPendingLoans(loanRepository.findAll().size());
            stats.setProperties(propertyRepository.findAll().size());
            stats.setEstimatedRoi(12.0);
        }

        return stats;
    }
}
