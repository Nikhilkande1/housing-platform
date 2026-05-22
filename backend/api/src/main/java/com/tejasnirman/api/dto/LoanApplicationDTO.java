package com.tejasnirman.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanApplicationDTO {
    private Long bankId;
    private Long propertyId;
    private BigDecimal amount;
    private int tenure; // in years
    
    private Long id;
    private String bankName;
    private String propertyTitle;
    private Double interestRate;
    private BigDecimal emi;
    private String status;
    private LocalDateTime appliedAt;
    
    public LoanApplicationDTO() {}

    public LoanApplicationDTO(Long bankId, Long propertyId, BigDecimal amount, int tenure, Long id, String bankName, String propertyTitle, Double interestRate, BigDecimal emi, String status, LocalDateTime appliedAt) {
        this.bankId = bankId;
        this.propertyId = propertyId;
        this.amount = amount;
        this.tenure = tenure;
        this.id = id;
        this.bankName = bankName;
        this.propertyTitle = propertyTitle;
        this.interestRate = interestRate;
        this.emi = emi;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
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

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getEmi() {
        return emi;
    }

    public void setEmi(BigDecimal emi) {
        this.emi = emi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}
