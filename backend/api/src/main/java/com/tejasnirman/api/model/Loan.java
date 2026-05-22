package com.tejasnirman.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;
    private BigDecimal amount;
    private Integer tenure; // in years
    private Double interestRate;
    private BigDecimal emi;
    @Enumerated(EnumType.STRING)
    private LoanStatus status;
    private LocalDateTime appliedAt;

    @PrePersist
    protected void onCreate() {
        appliedAt = LocalDateTime.now();
        if (status == null) status = LoanStatus.PENDING;
    }


    @java.lang.SuppressWarnings("all")
    public static class LoanBuilder {
        @java.lang.SuppressWarnings("all")
            private Long id;
        @java.lang.SuppressWarnings("all")
            private User user;
        @java.lang.SuppressWarnings("all")
            private Bank bank;
        @java.lang.SuppressWarnings("all")
            private Property property;
        @java.lang.SuppressWarnings("all")
            private BigDecimal amount;
        @java.lang.SuppressWarnings("all")
            private Integer tenure;
        @java.lang.SuppressWarnings("all")
            private Double interestRate;
        @java.lang.SuppressWarnings("all")
            private BigDecimal emi;
        @java.lang.SuppressWarnings("all")
            private LoanStatus status;
        @java.lang.SuppressWarnings("all")
            private LocalDateTime appliedAt;

        @java.lang.SuppressWarnings("all")
            LoanBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Loan.LoanBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Loan.LoanBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Loan.LoanBuilder bank(final Bank bank) {
            this.bank = bank;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Loan.LoanBuilder property(final Property property) {
            this.property = property;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Loan.LoanBuilder amount(final BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Loan.LoanBuilder tenure(final Integer tenure) {
            this.tenure = tenure;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Loan.LoanBuilder interestRate(final Double interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Loan.LoanBuilder emi(final BigDecimal emi) {
            this.emi = emi;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Loan.LoanBuilder status(final LoanStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Loan.LoanBuilder appliedAt(final LocalDateTime appliedAt) {
            this.appliedAt = appliedAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
            public Loan build() {
            return new Loan(this.id, this.user, this.bank, this.property, this.amount, this.tenure, this.interestRate, this.emi, this.status, this.appliedAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "Loan.LoanBuilder(id=" + this.id + ", user=" + this.user + ", bank=" + this.bank + ", property=" + this.property + ", amount=" + this.amount + ", tenure=" + this.tenure + ", interestRate=" + this.interestRate + ", emi=" + this.emi + ", status=" + this.status + ", appliedAt=" + this.appliedAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static Loan.LoanBuilder builder() {
        return new Loan.LoanBuilder();
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
    public Bank getBank() {
        return this.bank;
    }

    @java.lang.SuppressWarnings("all")
    public Property getProperty() {
        return this.property;
    }

    @java.lang.SuppressWarnings("all")
    public BigDecimal getAmount() {
        return this.amount;
    }

    @java.lang.SuppressWarnings("all")
    public Integer getTenure() {
        return this.tenure;
    }

    @java.lang.SuppressWarnings("all")
    public Double getInterestRate() {
        return this.interestRate;
    }

    @java.lang.SuppressWarnings("all")
    public BigDecimal getEmi() {
        return this.emi;
    }

    @java.lang.SuppressWarnings("all")
    public LoanStatus getStatus() {
        return this.status;
    }

    @java.lang.SuppressWarnings("all")
    public LocalDateTime getAppliedAt() {
        return this.appliedAt;
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
    public void setBank(final Bank bank) {
        this.bank = bank;
    }

    @java.lang.SuppressWarnings("all")
    public void setProperty(final Property property) {
        this.property = property;
    }

    @java.lang.SuppressWarnings("all")
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    @java.lang.SuppressWarnings("all")
    public void setTenure(final Integer tenure) {
        this.tenure = tenure;
    }

    @java.lang.SuppressWarnings("all")
    public void setInterestRate(final Double interestRate) {
        this.interestRate = interestRate;
    }

    @java.lang.SuppressWarnings("all")
    public void setEmi(final BigDecimal emi) {
        this.emi = emi;
    }

    @java.lang.SuppressWarnings("all")
    public void setStatus(final LoanStatus status) {
        this.status = status;
    }

    @java.lang.SuppressWarnings("all")
    public void setAppliedAt(final LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof Loan)) return false;
        final Loan other = (Loan) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$tenure = this.getTenure();
        final java.lang.Object other$tenure = other.getTenure();
        if (this$tenure == null ? other$tenure != null : !this$tenure.equals(other$tenure)) return false;
        final java.lang.Object this$interestRate = this.getInterestRate();
        final java.lang.Object other$interestRate = other.getInterestRate();
        if (this$interestRate == null ? other$interestRate != null : !this$interestRate.equals(other$interestRate)) return false;
        final java.lang.Object this$user = this.getUser();
        final java.lang.Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final java.lang.Object this$bank = this.getBank();
        final java.lang.Object other$bank = other.getBank();
        if (this$bank == null ? other$bank != null : !this$bank.equals(other$bank)) return false;
        final java.lang.Object this$property = this.getProperty();
        final java.lang.Object other$property = other.getProperty();
        if (this$property == null ? other$property != null : !this$property.equals(other$property)) return false;
        final java.lang.Object this$amount = this.getAmount();
        final java.lang.Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final java.lang.Object this$emi = this.getEmi();
        final java.lang.Object other$emi = other.getEmi();
        if (this$emi == null ? other$emi != null : !this$emi.equals(other$emi)) return false;
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final java.lang.Object this$appliedAt = this.getAppliedAt();
        final java.lang.Object other$appliedAt = other.getAppliedAt();
        if (this$appliedAt == null ? other$appliedAt != null : !this$appliedAt.equals(other$appliedAt)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof Loan;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $tenure = this.getTenure();
        result = result * PRIME + ($tenure == null ? 43 : $tenure.hashCode());
        final java.lang.Object $interestRate = this.getInterestRate();
        result = result * PRIME + ($interestRate == null ? 43 : $interestRate.hashCode());
        final java.lang.Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final java.lang.Object $bank = this.getBank();
        result = result * PRIME + ($bank == null ? 43 : $bank.hashCode());
        final java.lang.Object $property = this.getProperty();
        result = result * PRIME + ($property == null ? 43 : $property.hashCode());
        final java.lang.Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        final java.lang.Object $emi = this.getEmi();
        result = result * PRIME + ($emi == null ? 43 : $emi.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final java.lang.Object $appliedAt = this.getAppliedAt();
        result = result * PRIME + ($appliedAt == null ? 43 : $appliedAt.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "Loan(id=" + this.getId() + ", user=" + this.getUser() + ", bank=" + this.getBank() + ", property=" + this.getProperty() + ", amount=" + this.getAmount() + ", tenure=" + this.getTenure() + ", interestRate=" + this.getInterestRate() + ", emi=" + this.getEmi() + ", status=" + this.getStatus() + ", appliedAt=" + this.getAppliedAt() + ")";
    }

    @java.lang.SuppressWarnings("all")
    public Loan() {
    }

    @java.lang.SuppressWarnings("all")
    public Loan(final Long id, final User user, final Bank bank, final Property property, final BigDecimal amount, final Integer tenure, final Double interestRate, final BigDecimal emi, final LoanStatus status, final LocalDateTime appliedAt) {
        this.id = id;
        this.user = user;
        this.bank = bank;
        this.property = property;
        this.amount = amount;
        this.tenure = tenure;
        this.interestRate = interestRate;
        this.emi = emi;
        this.status = status;
        this.appliedAt = appliedAt;
    }
}
