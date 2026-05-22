package com.tejasnirman.api.dto;

import java.math.BigDecimal;

public class DashboardStatsDTO {
    private int activeProjects;
    private int totalBookings;
    private BigDecimal totalPayments;
    private int pendingEnquiries;
    private int pendingLoans;
    private int properties;
    private Double estimatedRoi;

    public DashboardStatsDTO() {}

    public DashboardStatsDTO(int activeProjects, int totalBookings, BigDecimal totalPayments, int pendingEnquiries, int pendingLoans, int properties, Double estimatedRoi) {
        this.activeProjects = activeProjects;
        this.totalBookings = totalBookings;
        this.totalPayments = totalPayments;
        this.pendingEnquiries = pendingEnquiries;
        this.pendingLoans = pendingLoans;
        this.properties = properties;
        this.estimatedRoi = estimatedRoi;
    }

    public int getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(int activeProjects) {
        this.activeProjects = activeProjects;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public BigDecimal getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(BigDecimal totalPayments) {
        this.totalPayments = totalPayments;
    }

    public int getPendingEnquiries() {
        return pendingEnquiries;
    }

    public void setPendingEnquiries(int pendingEnquiries) {
        this.pendingEnquiries = pendingEnquiries;
    }

    public int getPendingLoans() {
        return pendingLoans;
    }

    public void setPendingLoans(int pendingLoans) {
        this.pendingLoans = pendingLoans;
    }

    public int getProperties() {
        return properties;
    }

    public void setProperties(int properties) {
        this.properties = properties;
    }

    public Double getEstimatedRoi() {
        return estimatedRoi;
    }

    public void setEstimatedRoi(Double estimatedRoi) {
        this.estimatedRoi = estimatedRoi;
    }
}
