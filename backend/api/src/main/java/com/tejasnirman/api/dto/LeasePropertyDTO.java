package com.tejasnirman.api.dto;

import java.math.BigDecimal;

public class LeasePropertyDTO {
    private Long propertyId;
    private String leaseType;
    private BigDecimal monthlyIncome;
    
    private Long id;
    private String propertyTitle;
    private String location;
    private Double estimatedRoi;
    private Double occupancyRate;
    private String imageUrl;
    private String ownerName;
    private Boolean isActive;

    public LeasePropertyDTO() {}

    public LeasePropertyDTO(Long propertyId, String leaseType, BigDecimal monthlyIncome, Long id, String propertyTitle, String location, Double estimatedRoi, Double occupancyRate, String imageUrl, String ownerName, Boolean isActive) {
        this.propertyId = propertyId;
        this.leaseType = leaseType;
        this.monthlyIncome = monthlyIncome;
        this.id = id;
        this.propertyTitle = propertyTitle;
        this.location = location;
        this.estimatedRoi = estimatedRoi;
        this.occupancyRate = occupancyRate;
        this.imageUrl = imageUrl;
        this.ownerName = ownerName;
        this.isActive = isActive;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType;
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getEstimatedRoi() {
        return estimatedRoi;
    }

    public void setEstimatedRoi(Double estimatedRoi) {
        this.estimatedRoi = estimatedRoi;
    }

    public Double getOccupancyRate() {
        return occupancyRate;
    }

    public void setOccupancyRate(Double occupancyRate) {
        this.occupancyRate = occupancyRate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
