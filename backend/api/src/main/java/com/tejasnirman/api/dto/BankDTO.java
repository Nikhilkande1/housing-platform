package com.tejasnirman.api.dto;

import java.math.BigDecimal;

public class BankDTO {
    private Long id;
    private String name;
    private Double interestRate;
    private BigDecimal maxLoanAmount;
    private Double processingFee;
    private String features;
    private String logoUrl;
    private String contactEmail;

    public BankDTO() {}

    public BankDTO(Long id, String name, Double interestRate, BigDecimal maxLoanAmount, Double processingFee, String features, String logoUrl, String contactEmail) {
        this.id = id;
        this.name = name;
        this.interestRate = interestRate;
        this.maxLoanAmount = maxLoanAmount;
        this.processingFee = processingFee;
        this.features = features;
        this.logoUrl = logoUrl;
        this.contactEmail = contactEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMaxLoanAmount() {
        return maxLoanAmount;
    }

    public void setMaxLoanAmount(BigDecimal maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    public Double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(Double processingFee) {
        this.processingFee = processingFee;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
