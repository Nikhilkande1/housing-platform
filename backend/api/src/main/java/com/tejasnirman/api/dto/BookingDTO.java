package com.tejasnirman.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingDTO {
    // Request fields
    private Long propertyId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    
    // Response fields
    private Long id;
    private String userName;
    private String propertyTitle;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
    
    public BookingDTO() {}

    public BookingDTO(Long propertyId, LocalDate checkIn, LocalDate checkOut, Long id, String userName, String propertyTitle, BigDecimal totalAmount, String status, LocalDateTime createdAt) {
        this.propertyId = propertyId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.id = id;
        this.userName = userName;
        this.propertyTitle = propertyTitle;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
