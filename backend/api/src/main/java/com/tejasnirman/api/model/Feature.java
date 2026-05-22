package com.tejasnirman.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "features")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private FeatureCategory category;
    private BigDecimal basePrice;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String imageUrl;


    @java.lang.SuppressWarnings("all")
    public static class FeatureBuilder {
        @java.lang.SuppressWarnings("all")
            private Long id;
        @java.lang.SuppressWarnings("all")
            private String name;
        @java.lang.SuppressWarnings("all")
            private FeatureCategory category;
        @java.lang.SuppressWarnings("all")
            private BigDecimal basePrice;
        @java.lang.SuppressWarnings("all")
            private String description;
        @java.lang.SuppressWarnings("all")
            private String imageUrl;

        @java.lang.SuppressWarnings("all")
            FeatureBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Feature.FeatureBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Feature.FeatureBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Feature.FeatureBuilder category(final FeatureCategory category) {
            this.category = category;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Feature.FeatureBuilder basePrice(final BigDecimal basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Feature.FeatureBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Feature.FeatureBuilder imageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        @java.lang.SuppressWarnings("all")
            public Feature build() {
            return new Feature(this.id, this.name, this.category, this.basePrice, this.description, this.imageUrl);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "Feature.FeatureBuilder(id=" + this.id + ", name=" + this.name + ", category=" + this.category + ", basePrice=" + this.basePrice + ", description=" + this.description + ", imageUrl=" + this.imageUrl + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static Feature.FeatureBuilder builder() {
        return new Feature.FeatureBuilder();
    }

    @java.lang.SuppressWarnings("all")
    public Long getId() {
        return this.id;
    }

    @java.lang.SuppressWarnings("all")
    public String getName() {
        return this.name;
    }

    @java.lang.SuppressWarnings("all")
    public FeatureCategory getCategory() {
        return this.category;
    }

    @java.lang.SuppressWarnings("all")
    public BigDecimal getBasePrice() {
        return this.basePrice;
    }

    @java.lang.SuppressWarnings("all")
    public String getDescription() {
        return this.description;
    }

    @java.lang.SuppressWarnings("all")
    public String getImageUrl() {
        return this.imageUrl;
    }

    @java.lang.SuppressWarnings("all")
    public void setId(final Long id) {
        this.id = id;
    }

    @java.lang.SuppressWarnings("all")
    public void setName(final String name) {
        this.name = name;
    }

    @java.lang.SuppressWarnings("all")
    public void setCategory(final FeatureCategory category) {
        this.category = category;
    }

    @java.lang.SuppressWarnings("all")
    public void setBasePrice(final BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    @java.lang.SuppressWarnings("all")
    public void setDescription(final String description) {
        this.description = description;
    }

    @java.lang.SuppressWarnings("all")
    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof Feature)) return false;
        final Feature other = (Feature) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$name = this.getName();
        final java.lang.Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final java.lang.Object this$category = this.getCategory();
        final java.lang.Object other$category = other.getCategory();
        if (this$category == null ? other$category != null : !this$category.equals(other$category)) return false;
        final java.lang.Object this$basePrice = this.getBasePrice();
        final java.lang.Object other$basePrice = other.getBasePrice();
        if (this$basePrice == null ? other$basePrice != null : !this$basePrice.equals(other$basePrice)) return false;
        final java.lang.Object this$description = this.getDescription();
        final java.lang.Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) return false;
        final java.lang.Object this$imageUrl = this.getImageUrl();
        final java.lang.Object other$imageUrl = other.getImageUrl();
        if (this$imageUrl == null ? other$imageUrl != null : !this$imageUrl.equals(other$imageUrl)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof Feature;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final java.lang.Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        final java.lang.Object $basePrice = this.getBasePrice();
        result = result * PRIME + ($basePrice == null ? 43 : $basePrice.hashCode());
        final java.lang.Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final java.lang.Object $imageUrl = this.getImageUrl();
        result = result * PRIME + ($imageUrl == null ? 43 : $imageUrl.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "Feature(id=" + this.getId() + ", name=" + this.getName() + ", category=" + this.getCategory() + ", basePrice=" + this.getBasePrice() + ", description=" + this.getDescription() + ", imageUrl=" + this.getImageUrl() + ")";
    }

    @java.lang.SuppressWarnings("all")
    public Feature() {
    }

    @java.lang.SuppressWarnings("all")
    public Feature(final Long id, final String name, final FeatureCategory category, final BigDecimal basePrice, final String description, final String imageUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}
