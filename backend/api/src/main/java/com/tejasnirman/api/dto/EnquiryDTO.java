package com.tejasnirman.api.dto;

import java.time.LocalDateTime;

public class EnquiryDTO {
    private Long propertyId;
    private Long vendorId;
    private String message;
    
    private Long id;
    private String userName;
    private String propertyTitle;
    private String vendorName;
    private String status;
    private LocalDateTime createdAt;
    
    public EnquiryDTO() {}

    public EnquiryDTO(Long propertyId, Long vendorId, String message, Long id, String userName, String propertyTitle, String vendorName, String status, LocalDateTime createdAt) {
        this.propertyId = propertyId;
        this.vendorId = vendorId;
        this.message = message;
        this.id = id;
        this.userName = userName;
        this.propertyTitle = propertyTitle;
        this.vendorName = vendorName;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
