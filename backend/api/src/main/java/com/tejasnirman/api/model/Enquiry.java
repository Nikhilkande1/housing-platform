package com.tejasnirman.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "enquiries")
public class Enquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
    @Column(columnDefinition = "TEXT")
    private String message;
    @Enumerated(EnumType.STRING)
    private EnquiryStatus status;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) status = EnquiryStatus.NEW;
    }


    @java.lang.SuppressWarnings("all")
    public static class EnquiryBuilder {
        @java.lang.SuppressWarnings("all")
            private Long id;
        @java.lang.SuppressWarnings("all")
            private User user;
        @java.lang.SuppressWarnings("all")
            private Property property;
        @java.lang.SuppressWarnings("all")
            private Vendor vendor;
        @java.lang.SuppressWarnings("all")
            private String message;
        @java.lang.SuppressWarnings("all")
            private EnquiryStatus status;
        @java.lang.SuppressWarnings("all")
            private LocalDateTime createdAt;

        @java.lang.SuppressWarnings("all")
            EnquiryBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Enquiry.EnquiryBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Enquiry.EnquiryBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Enquiry.EnquiryBuilder property(final Property property) {
            this.property = property;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Enquiry.EnquiryBuilder vendor(final Vendor vendor) {
            this.vendor = vendor;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Enquiry.EnquiryBuilder message(final String message) {
            this.message = message;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Enquiry.EnquiryBuilder status(final EnquiryStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Enquiry.EnquiryBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
            public Enquiry build() {
            return new Enquiry(this.id, this.user, this.property, this.vendor, this.message, this.status, this.createdAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "Enquiry.EnquiryBuilder(id=" + this.id + ", user=" + this.user + ", property=" + this.property + ", vendor=" + this.vendor + ", message=" + this.message + ", status=" + this.status + ", createdAt=" + this.createdAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static Enquiry.EnquiryBuilder builder() {
        return new Enquiry.EnquiryBuilder();
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
    public Vendor getVendor() {
        return this.vendor;
    }

    @java.lang.SuppressWarnings("all")
    public String getMessage() {
        return this.message;
    }

    @java.lang.SuppressWarnings("all")
    public EnquiryStatus getStatus() {
        return this.status;
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
    public void setVendor(final Vendor vendor) {
        this.vendor = vendor;
    }

    @java.lang.SuppressWarnings("all")
    public void setMessage(final String message) {
        this.message = message;
    }

    @java.lang.SuppressWarnings("all")
    public void setStatus(final EnquiryStatus status) {
        this.status = status;
    }

    @java.lang.SuppressWarnings("all")
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof Enquiry)) return false;
        final Enquiry other = (Enquiry) o;
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
        final java.lang.Object this$vendor = this.getVendor();
        final java.lang.Object other$vendor = other.getVendor();
        if (this$vendor == null ? other$vendor != null : !this$vendor.equals(other$vendor)) return false;
        final java.lang.Object this$message = this.getMessage();
        final java.lang.Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof Enquiry;
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
        final java.lang.Object $vendor = this.getVendor();
        result = result * PRIME + ($vendor == null ? 43 : $vendor.hashCode());
        final java.lang.Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "Enquiry(id=" + this.getId() + ", user=" + this.getUser() + ", property=" + this.getProperty() + ", vendor=" + this.getVendor() + ", message=" + this.getMessage() + ", status=" + this.getStatus() + ", createdAt=" + this.getCreatedAt() + ")";
    }

    @java.lang.SuppressWarnings("all")
    public Enquiry() {
    }

    @java.lang.SuppressWarnings("all")
    public Enquiry(final Long id, final User user, final Property property, final Vendor vendor, final String message, final EnquiryStatus status, final LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.property = property;
        this.vendor = vendor;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
    }
}
