package com.tejasnirman.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String companyName;
    private String category;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double rating;
    private Integer reviewCount;
    private String location;
    private String imageUrl;
    private Boolean isVerified;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (isVerified == null) isVerified = false;
        if (rating == null) rating = 0.0;
        if (reviewCount == null) reviewCount = 0;
    }


    @java.lang.SuppressWarnings("all")
    public static class VendorBuilder {
        @java.lang.SuppressWarnings("all")
            private Long id;
        @java.lang.SuppressWarnings("all")
            private User user;
        @java.lang.SuppressWarnings("all")
            private String companyName;
        @java.lang.SuppressWarnings("all")
            private String category;
        @java.lang.SuppressWarnings("all")
            private String description;
        @java.lang.SuppressWarnings("all")
            private Double rating;
        @java.lang.SuppressWarnings("all")
            private Integer reviewCount;
        @java.lang.SuppressWarnings("all")
            private String location;
        @java.lang.SuppressWarnings("all")
            private String imageUrl;
        @java.lang.SuppressWarnings("all")
            private Boolean isVerified;
        @java.lang.SuppressWarnings("all")
            private LocalDateTime createdAt;

        @java.lang.SuppressWarnings("all")
            VendorBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Vendor.VendorBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Vendor.VendorBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Vendor.VendorBuilder companyName(final String companyName) {
            this.companyName = companyName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Vendor.VendorBuilder category(final String category) {
            this.category = category;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Vendor.VendorBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Vendor.VendorBuilder rating(final Double rating) {
            this.rating = rating;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Vendor.VendorBuilder reviewCount(final Integer reviewCount) {
            this.reviewCount = reviewCount;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Vendor.VendorBuilder location(final String location) {
            this.location = location;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Vendor.VendorBuilder imageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Vendor.VendorBuilder isVerified(final Boolean isVerified) {
            this.isVerified = isVerified;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Vendor.VendorBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
            public Vendor build() {
            return new Vendor(this.id, this.user, this.companyName, this.category, this.description, this.rating, this.reviewCount, this.location, this.imageUrl, this.isVerified, this.createdAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "Vendor.VendorBuilder(id=" + this.id + ", user=" + this.user + ", companyName=" + this.companyName + ", category=" + this.category + ", description=" + this.description + ", rating=" + this.rating + ", reviewCount=" + this.reviewCount + ", location=" + this.location + ", imageUrl=" + this.imageUrl + ", isVerified=" + this.isVerified + ", createdAt=" + this.createdAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static Vendor.VendorBuilder builder() {
        return new Vendor.VendorBuilder();
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
    public String getCompanyName() {
        return this.companyName;
    }

    @java.lang.SuppressWarnings("all")
    public String getCategory() {
        return this.category;
    }

    @java.lang.SuppressWarnings("all")
    public String getDescription() {
        return this.description;
    }

    @java.lang.SuppressWarnings("all")
    public Double getRating() {
        return this.rating;
    }

    @java.lang.SuppressWarnings("all")
    public Integer getReviewCount() {
        return this.reviewCount;
    }

    @java.lang.SuppressWarnings("all")
    public String getLocation() {
        return this.location;
    }

    @java.lang.SuppressWarnings("all")
    public String getImageUrl() {
        return this.imageUrl;
    }

    @java.lang.SuppressWarnings("all")
    public Boolean getIsVerified() {
        return this.isVerified;
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
    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    @java.lang.SuppressWarnings("all")
    public void setCategory(final String category) {
        this.category = category;
    }

    @java.lang.SuppressWarnings("all")
    public void setDescription(final String description) {
        this.description = description;
    }

    @java.lang.SuppressWarnings("all")
    public void setRating(final Double rating) {
        this.rating = rating;
    }

    @java.lang.SuppressWarnings("all")
    public void setReviewCount(final Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    @java.lang.SuppressWarnings("all")
    public void setLocation(final String location) {
        this.location = location;
    }

    @java.lang.SuppressWarnings("all")
    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @java.lang.SuppressWarnings("all")
    public void setIsVerified(final Boolean isVerified) {
        this.isVerified = isVerified;
    }

    @java.lang.SuppressWarnings("all")
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof Vendor)) return false;
        final Vendor other = (Vendor) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$rating = this.getRating();
        final java.lang.Object other$rating = other.getRating();
        if (this$rating == null ? other$rating != null : !this$rating.equals(other$rating)) return false;
        final java.lang.Object this$reviewCount = this.getReviewCount();
        final java.lang.Object other$reviewCount = other.getReviewCount();
        if (this$reviewCount == null ? other$reviewCount != null : !this$reviewCount.equals(other$reviewCount)) return false;
        final java.lang.Object this$isVerified = this.getIsVerified();
        final java.lang.Object other$isVerified = other.getIsVerified();
        if (this$isVerified == null ? other$isVerified != null : !this$isVerified.equals(other$isVerified)) return false;
        final java.lang.Object this$user = this.getUser();
        final java.lang.Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final java.lang.Object this$companyName = this.getCompanyName();
        final java.lang.Object other$companyName = other.getCompanyName();
        if (this$companyName == null ? other$companyName != null : !this$companyName.equals(other$companyName)) return false;
        final java.lang.Object this$category = this.getCategory();
        final java.lang.Object other$category = other.getCategory();
        if (this$category == null ? other$category != null : !this$category.equals(other$category)) return false;
        final java.lang.Object this$description = this.getDescription();
        final java.lang.Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) return false;
        final java.lang.Object this$location = this.getLocation();
        final java.lang.Object other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location)) return false;
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
        return other instanceof Vendor;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $rating = this.getRating();
        result = result * PRIME + ($rating == null ? 43 : $rating.hashCode());
        final java.lang.Object $reviewCount = this.getReviewCount();
        result = result * PRIME + ($reviewCount == null ? 43 : $reviewCount.hashCode());
        final java.lang.Object $isVerified = this.getIsVerified();
        result = result * PRIME + ($isVerified == null ? 43 : $isVerified.hashCode());
        final java.lang.Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final java.lang.Object $companyName = this.getCompanyName();
        result = result * PRIME + ($companyName == null ? 43 : $companyName.hashCode());
        final java.lang.Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        final java.lang.Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final java.lang.Object $location = this.getLocation();
        result = result * PRIME + ($location == null ? 43 : $location.hashCode());
        final java.lang.Object $imageUrl = this.getImageUrl();
        result = result * PRIME + ($imageUrl == null ? 43 : $imageUrl.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "Vendor(id=" + this.getId() + ", user=" + this.getUser() + ", companyName=" + this.getCompanyName() + ", category=" + this.getCategory() + ", description=" + this.getDescription() + ", rating=" + this.getRating() + ", reviewCount=" + this.getReviewCount() + ", location=" + this.getLocation() + ", imageUrl=" + this.getImageUrl() + ", isVerified=" + this.getIsVerified() + ", createdAt=" + this.getCreatedAt() + ")";
    }

    @java.lang.SuppressWarnings("all")
    public Vendor() {
    }

    @java.lang.SuppressWarnings("all")
    public Vendor(final Long id, final User user, final String companyName, final String category, final String description, final Double rating, final Integer reviewCount, final String location, final String imageUrl, final Boolean isVerified, final LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.companyName = companyName;
        this.category = category;
        this.description = description;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.location = location;
        this.imageUrl = imageUrl;
        this.isVerified = isVerified;
        this.createdAt = createdAt;
    }
}
