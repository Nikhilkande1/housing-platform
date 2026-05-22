package com.tejasnirman.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String bankName;
    private Double interestRate;
    private BigDecimal maxLoanAmount;
    private Double processingFee;
    @Column(columnDefinition = "TEXT")
    private String features;
    private String logoUrl;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }


    @java.lang.SuppressWarnings("all")
    public static class BankBuilder {
        @java.lang.SuppressWarnings("all")
            private Long id;
        @java.lang.SuppressWarnings("all")
            private User user;
        @java.lang.SuppressWarnings("all")
            private String bankName;
        @java.lang.SuppressWarnings("all")
            private Double interestRate;
        @java.lang.SuppressWarnings("all")
            private BigDecimal maxLoanAmount;
        @java.lang.SuppressWarnings("all")
            private Double processingFee;
        @java.lang.SuppressWarnings("all")
            private String features;
        @java.lang.SuppressWarnings("all")
            private String logoUrl;
        @java.lang.SuppressWarnings("all")
            private LocalDateTime createdAt;

        @java.lang.SuppressWarnings("all")
            BankBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Bank.BankBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Bank.BankBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Bank.BankBuilder bankName(final String bankName) {
            this.bankName = bankName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Bank.BankBuilder interestRate(final Double interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Bank.BankBuilder maxLoanAmount(final BigDecimal maxLoanAmount) {
            this.maxLoanAmount = maxLoanAmount;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Bank.BankBuilder processingFee(final Double processingFee) {
            this.processingFee = processingFee;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Bank.BankBuilder features(final String features) {
            this.features = features;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Bank.BankBuilder logoUrl(final String logoUrl) {
            this.logoUrl = logoUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Bank.BankBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
            public Bank build() {
            return new Bank(this.id, this.user, this.bankName, this.interestRate, this.maxLoanAmount, this.processingFee, this.features, this.logoUrl, this.createdAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "Bank.BankBuilder(id=" + this.id + ", user=" + this.user + ", bankName=" + this.bankName + ", interestRate=" + this.interestRate + ", maxLoanAmount=" + this.maxLoanAmount + ", processingFee=" + this.processingFee + ", features=" + this.features + ", logoUrl=" + this.logoUrl + ", createdAt=" + this.createdAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static Bank.BankBuilder builder() {
        return new Bank.BankBuilder();
    }

    @java.lang.SuppressWarnings("all")
    public Long getId() {
        return this.id;
    }

    @java.lang.SuppressWarnings("all")
    public User getUser() {
        return this.user;
    }

    @java.lang.SuppressWarnings("all")
    public String getBankName() {
        return this.bankName;
    }

    @java.lang.SuppressWarnings("all")
    public Double getInterestRate() {
        return this.interestRate;
    }

    @java.lang.SuppressWarnings("all")
    public BigDecimal getMaxLoanAmount() {
        return this.maxLoanAmount;
    }

    @java.lang.SuppressWarnings("all")
    public Double getProcessingFee() {
        return this.processingFee;
    }

    @java.lang.SuppressWarnings("all")
    public String getFeatures() {
        return this.features;
    }

    @java.lang.SuppressWarnings("all")
    public String getLogoUrl() {
        return this.logoUrl;
    }

    @java.lang.SuppressWarnings("all")
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @java.lang.SuppressWarnings("all")
    public void setId(final Long id) {
        this.id = id;
    }

    @java.lang.SuppressWarnings("all")
    public void setUser(final User user) {
        this.user = user;
    }

    @java.lang.SuppressWarnings("all")
    public void setBankName(final String bankName) {
        this.bankName = bankName;
    }

    @java.lang.SuppressWarnings("all")
    public void setInterestRate(final Double interestRate) {
        this.interestRate = interestRate;
    }

    @java.lang.SuppressWarnings("all")
    public void setMaxLoanAmount(final BigDecimal maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    @java.lang.SuppressWarnings("all")
    public void setProcessingFee(final Double processingFee) {
        this.processingFee = processingFee;
    }

    @java.lang.SuppressWarnings("all")
    public void setFeatures(final String features) {
        this.features = features;
    }

    @java.lang.SuppressWarnings("all")
    public void setLogoUrl(final String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @java.lang.SuppressWarnings("all")
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof Bank)) return false;
        final Bank other = (Bank) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$interestRate = this.getInterestRate();
        final java.lang.Object other$interestRate = other.getInterestRate();
        if (this$interestRate == null ? other$interestRate != null : !this$interestRate.equals(other$interestRate)) return false;
        final java.lang.Object this$processingFee = this.getProcessingFee();
        final java.lang.Object other$processingFee = other.getProcessingFee();
        if (this$processingFee == null ? other$processingFee != null : !this$processingFee.equals(other$processingFee)) return false;
        final java.lang.Object this$user = this.getUser();
        final java.lang.Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final java.lang.Object this$bankName = this.getBankName();
        final java.lang.Object other$bankName = other.getBankName();
        if (this$bankName == null ? other$bankName != null : !this$bankName.equals(other$bankName)) return false;
        final java.lang.Object this$maxLoanAmount = this.getMaxLoanAmount();
        final java.lang.Object other$maxLoanAmount = other.getMaxLoanAmount();
        if (this$maxLoanAmount == null ? other$maxLoanAmount != null : !this$maxLoanAmount.equals(other$maxLoanAmount)) return false;
        final java.lang.Object this$features = this.getFeatures();
        final java.lang.Object other$features = other.getFeatures();
        if (this$features == null ? other$features != null : !this$features.equals(other$features)) return false;
        final java.lang.Object this$logoUrl = this.getLogoUrl();
        final java.lang.Object other$logoUrl = other.getLogoUrl();
        if (this$logoUrl == null ? other$logoUrl != null : !this$logoUrl.equals(other$logoUrl)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof Bank;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $interestRate = this.getInterestRate();
        result = result * PRIME + ($interestRate == null ? 43 : $interestRate.hashCode());
        final java.lang.Object $processingFee = this.getProcessingFee();
        result = result * PRIME + ($processingFee == null ? 43 : $processingFee.hashCode());
        final java.lang.Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final java.lang.Object $bankName = this.getBankName();
        result = result * PRIME + ($bankName == null ? 43 : $bankName.hashCode());
        final java.lang.Object $maxLoanAmount = this.getMaxLoanAmount();
        result = result * PRIME + ($maxLoanAmount == null ? 43 : $maxLoanAmount.hashCode());
        final java.lang.Object $features = this.getFeatures();
        result = result * PRIME + ($features == null ? 43 : $features.hashCode());
        final java.lang.Object $logoUrl = this.getLogoUrl();
        result = result * PRIME + ($logoUrl == null ? 43 : $logoUrl.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "Bank(id=" + this.getId() + ", user=" + this.getUser() + ", bankName=" + this.getBankName() + ", interestRate=" + this.getInterestRate() + ", maxLoanAmount=" + this.getMaxLoanAmount() + ", processingFee=" + this.getProcessingFee() + ", features=" + this.getFeatures() + ", logoUrl=" + this.getLogoUrl() + ", createdAt=" + this.getCreatedAt() + ")";
    }

    @java.lang.SuppressWarnings("all")
    public Bank() {
    }

    @java.lang.SuppressWarnings("all")
    public Bank(final Long id, final User user, final String bankName, final Double interestRate, final BigDecimal maxLoanAmount, final Double processingFee, final String features, final String logoUrl, final LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.bankName = bankName;
        this.interestRate = interestRate;
        this.maxLoanAmount = maxLoanAmount;
        this.processingFee = processingFee;
        this.features = features;
        this.logoUrl = logoUrl;
        this.createdAt = createdAt;
    }
}
