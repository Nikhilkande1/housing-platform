package com.tejasnirman.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) status = BookingStatus.PENDING;
    }


    @java.lang.SuppressWarnings("all")
    public static class BookingBuilder {
        @java.lang.SuppressWarnings("all")
            private Long id;
        @java.lang.SuppressWarnings("all")
            private User user;
        @java.lang.SuppressWarnings("all")
            private Property property;
        @java.lang.SuppressWarnings("all")
            private LocalDate checkIn;
        @java.lang.SuppressWarnings("all")
            private LocalDate checkOut;
        @java.lang.SuppressWarnings("all")
            private BigDecimal totalAmount;
        @java.lang.SuppressWarnings("all")
            private BookingStatus status;
        @java.lang.SuppressWarnings("all")
            private Payment payment;
        @java.lang.SuppressWarnings("all")
            private LocalDateTime createdAt;

        @java.lang.SuppressWarnings("all")
            BookingBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Booking.BookingBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Booking.BookingBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Booking.BookingBuilder property(final Property property) {
            this.property = property;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Booking.BookingBuilder checkIn(final LocalDate checkIn) {
            this.checkIn = checkIn;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Booking.BookingBuilder checkOut(final LocalDate checkOut) {
            this.checkOut = checkOut;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Booking.BookingBuilder totalAmount(final BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Booking.BookingBuilder status(final BookingStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Booking.BookingBuilder payment(final Payment payment) {
            this.payment = payment;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Booking.BookingBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
            public Booking build() {
            return new Booking(this.id, this.user, this.property, this.checkIn, this.checkOut, this.totalAmount, this.status, this.payment, this.createdAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "Booking.BookingBuilder(id=" + this.id + ", user=" + this.user + ", property=" + this.property + ", checkIn=" + this.checkIn + ", checkOut=" + this.checkOut + ", totalAmount=" + this.totalAmount + ", status=" + this.status + ", payment=" + this.payment + ", createdAt=" + this.createdAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static Booking.BookingBuilder builder() {
        return new Booking.BookingBuilder();
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
    public LocalDate getCheckIn() {
        return this.checkIn;
    }

    @java.lang.SuppressWarnings("all")
    public LocalDate getCheckOut() {
        return this.checkOut;
    }

    @java.lang.SuppressWarnings("all")
    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    @java.lang.SuppressWarnings("all")
    public BookingStatus getStatus() {
        return this.status;
    }

    @java.lang.SuppressWarnings("all")
    public Payment getPayment() {
        return this.payment;
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
    public void setCheckIn(final LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    @java.lang.SuppressWarnings("all")
    public void setCheckOut(final LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    @java.lang.SuppressWarnings("all")
    public void setTotalAmount(final BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @java.lang.SuppressWarnings("all")
    public void setStatus(final BookingStatus status) {
        this.status = status;
    }

    @java.lang.SuppressWarnings("all")
    public void setPayment(final Payment payment) {
        this.payment = payment;
    }

    @java.lang.SuppressWarnings("all")
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof Booking)) return false;
        final Booking other = (Booking) o;
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
        final java.lang.Object this$checkIn = this.getCheckIn();
        final java.lang.Object other$checkIn = other.getCheckIn();
        if (this$checkIn == null ? other$checkIn != null : !this$checkIn.equals(other$checkIn)) return false;
        final java.lang.Object this$checkOut = this.getCheckOut();
        final java.lang.Object other$checkOut = other.getCheckOut();
        if (this$checkOut == null ? other$checkOut != null : !this$checkOut.equals(other$checkOut)) return false;
        final java.lang.Object this$totalAmount = this.getTotalAmount();
        final java.lang.Object other$totalAmount = other.getTotalAmount();
        if (this$totalAmount == null ? other$totalAmount != null : !this$totalAmount.equals(other$totalAmount)) return false;
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final java.lang.Object this$payment = this.getPayment();
        final java.lang.Object other$payment = other.getPayment();
        if (this$payment == null ? other$payment != null : !this$payment.equals(other$payment)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof Booking;
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
        final java.lang.Object $checkIn = this.getCheckIn();
        result = result * PRIME + ($checkIn == null ? 43 : $checkIn.hashCode());
        final java.lang.Object $checkOut = this.getCheckOut();
        result = result * PRIME + ($checkOut == null ? 43 : $checkOut.hashCode());
        final java.lang.Object $totalAmount = this.getTotalAmount();
        result = result * PRIME + ($totalAmount == null ? 43 : $totalAmount.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final java.lang.Object $payment = this.getPayment();
        result = result * PRIME + ($payment == null ? 43 : $payment.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "Booking(id=" + this.getId() + ", user=" + this.getUser() + ", property=" + this.getProperty() + ", checkIn=" + this.getCheckIn() + ", checkOut=" + this.getCheckOut() + ", totalAmount=" + this.getTotalAmount() + ", status=" + this.getStatus() + ", payment=" + this.getPayment() + ", createdAt=" + this.getCreatedAt() + ")";
    }

    @java.lang.SuppressWarnings("all")
    public Booking() {
    }

    @java.lang.SuppressWarnings("all")
    public Booking(final Long id, final User user, final Property property, final LocalDate checkIn, final LocalDate checkOut, final BigDecimal totalAmount, final BookingStatus status, final Payment payment, final LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.property = property;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalAmount = totalAmount;
        this.status = status;
        this.payment = payment;
        this.createdAt = createdAt;
    }
}
