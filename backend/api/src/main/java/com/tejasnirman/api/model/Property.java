package com.tejasnirman.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    private PropertyType type;
    private String location;
    private BigDecimal price; // Purchase or Valuation
    private BigDecimal monthlyRent;
    private Double estimatedRoi;
    private Double occupancyRate;
    private Boolean isVerified;
    private String imageUrl;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (isVerified == null) isVerified = false;
    }


    @java.lang.SuppressWarnings("all")
    public static class PropertyBuilder {
        @java.lang.SuppressWarnings("all")
            private Long id;
        @java.lang.SuppressWarnings("all")
            private User owner;
        @java.lang.SuppressWarnings("all")
            private String title;
        @java.lang.SuppressWarnings("all")
            private String description;
        @java.lang.SuppressWarnings("all")
            private PropertyType type;
        @java.lang.SuppressWarnings("all")
            private String location;
        @java.lang.SuppressWarnings("all")
            private BigDecimal price;
        @java.lang.SuppressWarnings("all")
            private BigDecimal monthlyRent;
        @java.lang.SuppressWarnings("all")
            private Double estimatedRoi;
        @java.lang.SuppressWarnings("all")
            private Double occupancyRate;
        @java.lang.SuppressWarnings("all")
            private Boolean isVerified;
        @java.lang.SuppressWarnings("all")
            private String imageUrl;
        @java.lang.SuppressWarnings("all")
            private LocalDateTime createdAt;

        @java.lang.SuppressWarnings("all")
            PropertyBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Property.PropertyBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Property.PropertyBuilder owner(final User owner) {
            this.owner = owner;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Property.PropertyBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Property.PropertyBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Property.PropertyBuilder type(final PropertyType type) {
            this.type = type;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Property.PropertyBuilder location(final String location) {
            this.location = location;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Property.PropertyBuilder price(final BigDecimal price) {
            this.price = price;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Property.PropertyBuilder monthlyRent(final BigDecimal monthlyRent) {
            this.monthlyRent = monthlyRent;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Property.PropertyBuilder estimatedRoi(final Double estimatedRoi) {
            this.estimatedRoi = estimatedRoi;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Property.PropertyBuilder occupancyRate(final Double occupancyRate) {
            this.occupancyRate = occupancyRate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Property.PropertyBuilder isVerified(final Boolean isVerified) {
            this.isVerified = isVerified;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Property.PropertyBuilder imageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Property.PropertyBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
            public Property build() {
            return new Property(this.id, this.owner, this.title, this.description, this.type, this.location, this.price, this.monthlyRent, this.estimatedRoi, this.occupancyRate, this.isVerified, this.imageUrl, this.createdAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "Property.PropertyBuilder(id=" + this.id + ", owner=" + this.owner + ", title=" + this.title + ", description=" + this.description + ", type=" + this.type + ", location=" + this.location + ", price=" + this.price + ", monthlyRent=" + this.monthlyRent + ", estimatedRoi=" + this.estimatedRoi + ", occupancyRate=" + this.occupancyRate + ", isVerified=" + this.isVerified + ", imageUrl=" + this.imageUrl + ", createdAt=" + this.createdAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static Property.PropertyBuilder builder() {
        return new Property.PropertyBuilder();
    }

    @java.lang.SuppressWarnings("all")
    public Long getId() {
        return this.id;
    }

    @java.lang.SuppressWarnings("all")
    public User getOwner() {
        return this.owner;
    }

    @java.lang.SuppressWarnings("all")
    public String getTitle() {
        return this.title;
    }

    @java.lang.SuppressWarnings("all")
    public String getDescription() {
        return this.description;
    }

    @java.lang.SuppressWarnings("all")
    public PropertyType getType() {
        return this.type;
    }

    @java.lang.SuppressWarnings("all")
    public String getLocation() {
        return this.location;
    }

    @java.lang.SuppressWarnings("all")
    public BigDecimal getPrice() {
        return this.price;
    }

    @java.lang.SuppressWarnings("all")
    public BigDecimal getMonthlyRent() {
        return this.monthlyRent;
    }

    @java.lang.SuppressWarnings("all")
    public Double getEstimatedRoi() {
        return this.estimatedRoi;
    }

    @java.lang.SuppressWarnings("all")
    public Double getOccupancyRate() {
        return this.occupancyRate;
    }

    @java.lang.SuppressWarnings("all")
    public Boolean getIsVerified() {
        return this.isVerified;
    }

    @java.lang.SuppressWarnings("all")
    public String getImageUrl() {
        return this.imageUrl;
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
    public void setOwner(final User owner) {
        this.owner = owner;
    }

    @java.lang.SuppressWarnings("all")
    public void setTitle(final String title) {
        this.title = title;
    }

    @java.lang.SuppressWarnings("all")
    public void setDescription(final String description) {
        this.description = description;
    }

    @java.lang.SuppressWarnings("all")
    public void setType(final PropertyType type) {
        this.type = type;
    }

    @java.lang.SuppressWarnings("all")
    public void setLocation(final String location) {
        this.location = location;
    }

    @java.lang.SuppressWarnings("all")
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    @java.lang.SuppressWarnings("all")
    public void setMonthlyRent(final BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    @java.lang.SuppressWarnings("all")
    public void setEstimatedRoi(final Double estimatedRoi) {
        this.estimatedRoi = estimatedRoi;
    }

    @java.lang.SuppressWarnings("all")
    public void setOccupancyRate(final Double occupancyRate) {
        this.occupancyRate = occupancyRate;
    }

    @java.lang.SuppressWarnings("all")
    public void setIsVerified(final Boolean isVerified) {
        this.isVerified = isVerified;
    }

    @java.lang.SuppressWarnings("all")
    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @java.lang.SuppressWarnings("all")
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof Property)) return false;
        final Property other = (Property) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$estimatedRoi = this.getEstimatedRoi();
        final java.lang.Object other$estimatedRoi = other.getEstimatedRoi();
        if (this$estimatedRoi == null ? other$estimatedRoi != null : !this$estimatedRoi.equals(other$estimatedRoi)) return false;
        final java.lang.Object this$occupancyRate = this.getOccupancyRate();
        final java.lang.Object other$occupancyRate = other.getOccupancyRate();
        if (this$occupancyRate == null ? other$occupancyRate != null : !this$occupancyRate.equals(other$occupancyRate)) return false;
        final java.lang.Object this$isVerified = this.getIsVerified();
        final java.lang.Object other$isVerified = other.getIsVerified();
        if (this$isVerified == null ? other$isVerified != null : !this$isVerified.equals(other$isVerified)) return false;
        final java.lang.Object this$owner = this.getOwner();
        final java.lang.Object other$owner = other.getOwner();
        if (this$owner == null ? other$owner != null : !this$owner.equals(other$owner)) return false;
        final java.lang.Object this$title = this.getTitle();
        final java.lang.Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final java.lang.Object this$description = this.getDescription();
        final java.lang.Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) return false;
        final java.lang.Object this$type = this.getType();
        final java.lang.Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final java.lang.Object this$location = this.getLocation();
        final java.lang.Object other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location)) return false;
        final java.lang.Object this$price = this.getPrice();
        final java.lang.Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final java.lang.Object this$monthlyRent = this.getMonthlyRent();
        final java.lang.Object other$monthlyRent = other.getMonthlyRent();
        if (this$monthlyRent == null ? other$monthlyRent != null : !this$monthlyRent.equals(other$monthlyRent)) return false;
        final java.lang.Object this$imageUrl = this.getImageUrl();
        final java.lang.Object other$imageUrl = other.getImageUrl();
        if (this$imageUrl == null ? other$imageUrl != null : !this$imageUrl.equals(other$imageUrl)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof Property;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $estimatedRoi = this.getEstimatedRoi();
        result = result * PRIME + ($estimatedRoi == null ? 43 : $estimatedRoi.hashCode());
        final java.lang.Object $occupancyRate = this.getOccupancyRate();
        result = result * PRIME + ($occupancyRate == null ? 43 : $occupancyRate.hashCode());
        final java.lang.Object $isVerified = this.getIsVerified();
        result = result * PRIME + ($isVerified == null ? 43 : $isVerified.hashCode());
        final java.lang.Object $owner = this.getOwner();
        result = result * PRIME + ($owner == null ? 43 : $owner.hashCode());
        final java.lang.Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final java.lang.Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final java.lang.Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final java.lang.Object $location = this.getLocation();
        result = result * PRIME + ($location == null ? 43 : $location.hashCode());
        final java.lang.Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final java.lang.Object $monthlyRent = this.getMonthlyRent();
        result = result * PRIME + ($monthlyRent == null ? 43 : $monthlyRent.hashCode());
        final java.lang.Object $imageUrl = this.getImageUrl();
        result = result * PRIME + ($imageUrl == null ? 43 : $imageUrl.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "Property(id=" + this.getId() + ", owner=" + this.getOwner() + ", title=" + this.getTitle() + ", description=" + this.getDescription() + ", type=" + this.getType() + ", location=" + this.getLocation() + ", price=" + this.getPrice() + ", monthlyRent=" + this.getMonthlyRent() + ", estimatedRoi=" + this.getEstimatedRoi() + ", occupancyRate=" + this.getOccupancyRate() + ", isVerified=" + this.getIsVerified() + ", imageUrl=" + this.getImageUrl() + ", createdAt=" + this.getCreatedAt() + ")";
    }

    @java.lang.SuppressWarnings("all")
    public Property() {
    }

    @java.lang.SuppressWarnings("all")
    public Property(final Long id, final User owner, final String title, final String description, final PropertyType type, final String location, final BigDecimal price, final BigDecimal monthlyRent, final Double estimatedRoi, final Double occupancyRate, final Boolean isVerified, final String imageUrl, final LocalDateTime createdAt) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.description = description;
        this.type = type;
        this.location = location;
        this.price = price;
        this.monthlyRent = monthlyRent;
        this.estimatedRoi = estimatedRoi;
        this.occupancyRate = occupancyRate;
        this.isVerified = isVerified;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }
}
