package com.tejasnirman.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDTO {
    private Long propertyId;
    private BigDecimal amount;
    private String gateway;
    
    private Long id;
    private String propertyTitle;
    private String paymentStatus;
    private String transactionId;
    private LocalDateTime createdAt;
    
    public PaymentDTO() {}

    public PaymentDTO(Long propertyId, BigDecimal amount, String gateway, Long id, String propertyTitle, String paymentStatus, String transactionId, LocalDateTime createdAt) {
        this.propertyId = propertyId;
        this.amount = amount;
        this.gateway = gateway;
        this.id = id;
        this.propertyTitle = propertyTitle;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
        this.createdAt = createdAt;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
