package com.tejasnirman.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "lease_properties")
public class LeaseProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;
    @Enumerated(EnumType.STRING)
    private LeaseType leaseType;
    private BigDecimal monthlyIncome;
    private Double occupancyRate;
    private Boolean isActive;

    @PrePersist
    protected void onCreate() {
        if (isActive == null) isActive = true;
        if (occupancyRate == null) occupancyRate = 0.0;
    }


    @java.lang.SuppressWarnings("all")
    public static class LeasePropertyBuilder {
        @java.lang.SuppressWarnings("all")
            private Long id;
        @java.lang.SuppressWarnings("all")
            private Property property;
        @java.lang.SuppressWarnings("all")
            private LeaseType leaseType;
        @java.lang.SuppressWarnings("all")
            private BigDecimal monthlyIncome;
        @java.lang.SuppressWarnings("all")
            private Double occupancyRate;
        @java.lang.SuppressWarnings("all")
            private Boolean isActive;

        @java.lang.SuppressWarnings("all")
            LeasePropertyBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public LeaseProperty.LeasePropertyBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public LeaseProperty.LeasePropertyBuilder property(final Property property) {
            this.property = property;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public LeaseProperty.LeasePropertyBuilder leaseType(final LeaseType leaseType) {
            this.leaseType = leaseType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public LeaseProperty.LeasePropertyBuilder monthlyIncome(final BigDecimal monthlyIncome) {
            this.monthlyIncome = monthlyIncome;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public LeaseProperty.LeasePropertyBuilder occupancyRate(final Double occupancyRate) {
            this.occupancyRate = occupancyRate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public LeaseProperty.LeasePropertyBuilder isActive(final Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        @java.lang.SuppressWarnings("all")
            public LeaseProperty build() {
            return new LeaseProperty(this.id, this.property, this.leaseType, this.monthlyIncome, this.occupancyRate, this.isActive);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "LeaseProperty.LeasePropertyBuilder(id=" + this.id + ", property=" + this.property + ", leaseType=" + this.leaseType + ", monthlyIncome=" + this.monthlyIncome + ", occupancyRate=" + this.occupancyRate + ", isActive=" + this.isActive + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static LeaseProperty.LeasePropertyBuilder builder() {
        return new LeaseProperty.LeasePropertyBuilder();
    }

    @java.lang.SuppressWarnings("all")
    public Long getId() {
        return this.id;
    }

    @java.lang.SuppressWarnings("all")
    public Property getProperty() {
        return this.property;
    }

    @java.lang.SuppressWarnings("all")
    public LeaseType getLeaseType() {
        return this.leaseType;
    }

    @java.lang.SuppressWarnings("all")
    public BigDecimal getMonthlyIncome() {
        return this.monthlyIncome;
    }

    @java.lang.SuppressWarnings("all")
    public Double getOccupancyRate() {
        return this.occupancyRate;
    }

    @java.lang.SuppressWarnings("all")
    public Boolean getIsActive() {
        return this.isActive;
    }

    @java.lang.SuppressWarnings("all")
    public void setId(final Long id) {
        this.id = id;
    }

    @java.lang.SuppressWarnings("all")
    public void setProperty(final Property property) {
        this.property = property;
    }

    @java.lang.SuppressWarnings("all")
    public void setLeaseType(final LeaseType leaseType) {
        this.leaseType = leaseType;
    }

    @java.lang.SuppressWarnings("all")
    public void setMonthlyIncome(final BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    @java.lang.SuppressWarnings("all")
    public void setOccupancyRate(final Double occupancyRate) {
        this.occupancyRate = occupancyRate;
    }

    @java.lang.SuppressWarnings("all")
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof LeaseProperty)) return false;
        final LeaseProperty other = (LeaseProperty) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$occupancyRate = this.getOccupancyRate();
        final java.lang.Object other$occupancyRate = other.getOccupancyRate();
        if (this$occupancyRate == null ? other$occupancyRate != null : !this$occupancyRate.equals(other$occupancyRate)) return false;
        final java.lang.Object this$isActive = this.getIsActive();
        final java.lang.Object other$isActive = other.getIsActive();
        if (this$isActive == null ? other$isActive != null : !this$isActive.equals(other$isActive)) return false;
        final java.lang.Object this$property = this.getProperty();
        final java.lang.Object other$property = other.getProperty();
        if (this$property == null ? other$property != null : !this$property.equals(other$property)) return false;
        final java.lang.Object this$leaseType = this.getLeaseType();
        final java.lang.Object other$leaseType = other.getLeaseType();
        if (this$leaseType == null ? other$leaseType != null : !this$leaseType.equals(other$leaseType)) return false;
        final java.lang.Object this$monthlyIncome = this.getMonthlyIncome();
        final java.lang.Object other$monthlyIncome = other.getMonthlyIncome();
        if (this$monthlyIncome == null ? other$monthlyIncome != null : !this$monthlyIncome.equals(other$monthlyIncome)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof LeaseProperty;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $occupancyRate = this.getOccupancyRate();
        result = result * PRIME + ($occupancyRate == null ? 43 : $occupancyRate.hashCode());
        final java.lang.Object $isActive = this.getIsActive();
        result = result * PRIME + ($isActive == null ? 43 : $isActive.hashCode());
        final java.lang.Object $property = this.getProperty();
        result = result * PRIME + ($property == null ? 43 : $property.hashCode());
        final java.lang.Object $leaseType = this.getLeaseType();
        result = result * PRIME + ($leaseType == null ? 43 : $leaseType.hashCode());
        final java.lang.Object $monthlyIncome = this.getMonthlyIncome();
        result = result * PRIME + ($monthlyIncome == null ? 43 : $monthlyIncome.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "LeaseProperty(id=" + this.getId() + ", property=" + this.getProperty() + ", leaseType=" + this.getLeaseType() + ", monthlyIncome=" + this.getMonthlyIncome() + ", occupancyRate=" + this.getOccupancyRate() + ", isActive=" + this.getIsActive() + ")";
    }

    @java.lang.SuppressWarnings("all")
    public LeaseProperty() {
    }

    @java.lang.SuppressWarnings("all")
    public LeaseProperty(final Long id, final Property property, final LeaseType leaseType, final BigDecimal monthlyIncome, final Double occupancyRate, final Boolean isActive) {
        this.id = id;
        this.property = property;
        this.leaseType = leaseType;
        this.monthlyIncome = monthlyIncome;
        this.occupancyRate = occupancyRate;
        this.isActive = isActive;
    }
}
