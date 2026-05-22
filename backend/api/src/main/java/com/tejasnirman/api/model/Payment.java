package com.tejasnirman.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String transactionId;
    private String gateway; // RAZORPAY, STRIPE
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (paymentStatus == null) paymentStatus = PaymentStatus.PENDING;
    }


    @java.lang.SuppressWarnings("all")
    public static class PaymentBuilder {
        @java.lang.SuppressWarnings("all")
            private Long id;
        @java.lang.SuppressWarnings("all")
            private User user;
        @java.lang.SuppressWarnings("all")
            private Property property;
        @java.lang.SuppressWarnings("all")
            private BigDecimal amount;
        @java.lang.SuppressWarnings("all")
            private PaymentStatus paymentStatus;
        @java.lang.SuppressWarnings("all")
            private String transactionId;
        @java.lang.SuppressWarnings("all")
            private String gateway;
        @java.lang.SuppressWarnings("all")
            private LocalDateTime createdAt;

        @java.lang.SuppressWarnings("all")
            PaymentBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Payment.PaymentBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Payment.PaymentBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Payment.PaymentBuilder property(final Property property) {
            this.property = property;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Payment.PaymentBuilder amount(final BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Payment.PaymentBuilder paymentStatus(final PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Payment.PaymentBuilder transactionId(final String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Payment.PaymentBuilder gateway(final String gateway) {
            this.gateway = gateway;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Payment.PaymentBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
            public Payment build() {
            return new Payment(this.id, this.user, this.property, this.amount, this.paymentStatus, this.transactionId, this.gateway, this.createdAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "Payment.PaymentBuilder(id=" + this.id + ", user=" + this.user + ", property=" + this.property + ", amount=" + this.amount + ", paymentStatus=" + this.paymentStatus + ", transactionId=" + this.transactionId + ", gateway=" + this.gateway + ", createdAt=" + this.createdAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static Payment.PaymentBuilder builder() {
        return new Payment.PaymentBuilder();
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
    public Property getProperty() {
        return this.property;
    }

    @java.lang.SuppressWarnings("all")
    public BigDecimal getAmount() {
        return this.amount;
    }

    @java.lang.SuppressWarnings("all")
    public PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }

    @java.lang.SuppressWarnings("all")
    public String getTransactionId() {
        return this.transactionId;
    }

    @java.lang.SuppressWarnings("all")
    public String getGateway() {
        return this.gateway;
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
    public void setProperty(final Property property) {
        this.property = property;
    }

    @java.lang.SuppressWarnings("all")
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    @java.lang.SuppressWarnings("all")
    public void setPaymentStatus(final PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @java.lang.SuppressWarnings("all")
    public void setTransactionId(final String transactionId) {
        this.transactionId = transactionId;
    }

    @java.lang.SuppressWarnings("all")
    public void setGateway(final String gateway) {
        this.gateway = gateway;
    }

    @java.lang.SuppressWarnings("all")
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof Payment)) return false;
        final Payment other = (Payment) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$user = this.getUser();
        final java.lang.Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final java.lang.Object this$property = this.getProperty();
        final java.lang.Object other$property = other.getProperty();
        if (this$property == null ? other$property != null : !this$property.equals(other$property)) return false;
        final java.lang.Object this$amount = this.getAmount();
        final java.lang.Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final java.lang.Object this$paymentStatus = this.getPaymentStatus();
        final java.lang.Object other$paymentStatus = other.getPaymentStatus();
        if (this$paymentStatus == null ? other$paymentStatus != null : !this$paymentStatus.equals(other$paymentStatus)) return false;
        final java.lang.Object this$transactionId = this.getTransactionId();
        final java.lang.Object other$transactionId = other.getTransactionId();
        if (this$transactionId == null ? other$transactionId != null : !this$transactionId.equals(other$transactionId)) return false;
        final java.lang.Object this$gateway = this.getGateway();
        final java.lang.Object other$gateway = other.getGateway();
        if (this$gateway == null ? other$gateway != null : !this$gateway.equals(other$gateway)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof Payment;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final java.lang.Object $property = this.getProperty();
        result = result * PRIME + ($property == null ? 43 : $property.hashCode());
        final java.lang.Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        final java.lang.Object $paymentStatus = this.getPaymentStatus();
        result = result * PRIME + ($paymentStatus == null ? 43 : $paymentStatus.hashCode());
        final java.lang.Object $transactionId = this.getTransactionId();
        result = result * PRIME + ($transactionId == null ? 43 : $transactionId.hashCode());
        final java.lang.Object $gateway = this.getGateway();
        result = result * PRIME + ($gateway == null ? 43 : $gateway.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "Payment(id=" + this.getId() + ", user=" + this.getUser() + ", property=" + this.getProperty() + ", amount=" + this.getAmount() + ", paymentStatus=" + this.getPaymentStatus() + ", transactionId=" + this.getTransactionId() + ", gateway=" + this.getGateway() + ", createdAt=" + this.getCreatedAt() + ")";
    }

    @java.lang.SuppressWarnings("all")
    public Payment() {
    }

    @java.lang.SuppressWarnings("all")
    public Payment(final Long id, final User user, final Property property, final BigDecimal amount, final PaymentStatus paymentStatus, final String transactionId, final String gateway, final LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.property = property;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
        this.gateway = gateway;
        this.createdAt = createdAt;
    }
}
