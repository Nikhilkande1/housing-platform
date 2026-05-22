package com.tejasnirman.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;
    private String name;
    private String category;
    private BigDecimal pricePerUnit;
    private String unit;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String imageUrl;


    @java.lang.SuppressWarnings("all")
    public static class MaterialBuilder {
        @java.lang.SuppressWarnings("all")
            private Long id;
        @java.lang.SuppressWarnings("all")
            private Vendor vendor;
        @java.lang.SuppressWarnings("all")
            private String name;
        @java.lang.SuppressWarnings("all")
            private String category;
        @java.lang.SuppressWarnings("all")
            private BigDecimal pricePerUnit;
        @java.lang.SuppressWarnings("all")
            private String unit;
        @java.lang.SuppressWarnings("all")
            private String description;
        @java.lang.SuppressWarnings("all")
            private String imageUrl;

        @java.lang.SuppressWarnings("all")
            MaterialBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Material.MaterialBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Material.MaterialBuilder vendor(final Vendor vendor) {
            this.vendor = vendor;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Material.MaterialBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Material.MaterialBuilder category(final String category) {
            this.category = category;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Material.MaterialBuilder pricePerUnit(final BigDecimal pricePerUnit) {
            this.pricePerUnit = pricePerUnit;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Material.MaterialBuilder unit(final String unit) {
            this.unit = unit;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Material.MaterialBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Material.MaterialBuilder imageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        @java.lang.SuppressWarnings("all")
            public Material build() {
            return new Material(this.id, this.vendor, this.name, this.category, this.pricePerUnit, this.unit, this.description, this.imageUrl);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "Material.MaterialBuilder(id=" + this.id + ", vendor=" + this.vendor + ", name=" + this.name + ", category=" + this.category + ", pricePerUnit=" + this.pricePerUnit + ", unit=" + this.unit + ", description=" + this.description + ", imageUrl=" + this.imageUrl + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static Material.MaterialBuilder builder() {
        return new Material.MaterialBuilder();
    }

    @java.lang.SuppressWarnings("all")
    public Long getId() {
        return this.id;
    }

    @java.lang.SuppressWarnings("all")
    public Vendor getVendor() {
        return this.vendor;
    }

    @java.lang.SuppressWarnings("all")
    public String getName() {
        return this.name;
    }

    @java.lang.SuppressWarnings("all")
    public String getCategory() {
        return this.category;
    }

    @java.lang.SuppressWarnings("all")
    public BigDecimal getPricePerUnit() {
        return this.pricePerUnit;
    }

    @java.lang.SuppressWarnings("all")
    public String getUnit() {
        return this.unit;
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
    public void setVendor(final Vendor vendor) {
        this.vendor = vendor;
    }

    @java.lang.SuppressWarnings("all")
    public void setName(final String name) {
        this.name = name;
    }

    @java.lang.SuppressWarnings("all")
    public void setCategory(final String category) {
        this.category = category;
    }

    @java.lang.SuppressWarnings("all")
    public void setPricePerUnit(final BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @java.lang.SuppressWarnings("all")
    public void setUnit(final String unit) {
        this.unit = unit;
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
        if (!(o instanceof Material)) return false;
        final Material other = (Material) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$vendor = this.getVendor();
        final java.lang.Object other$vendor = other.getVendor();
        if (this$vendor == null ? other$vendor != null : !this$vendor.equals(other$vendor)) return false;
        final java.lang.Object this$name = this.getName();
        final java.lang.Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final java.lang.Object this$category = this.getCategory();
        final java.lang.Object other$category = other.getCategory();
        if (this$category == null ? other$category != null : !this$category.equals(other$category)) return false;
        final java.lang.Object this$pricePerUnit = this.getPricePerUnit();
        final java.lang.Object other$pricePerUnit = other.getPricePerUnit();
        if (this$pricePerUnit == null ? other$pricePerUnit != null : !this$pricePerUnit.equals(other$pricePerUnit)) return false;
        final java.lang.Object this$unit = this.getUnit();
        final java.lang.Object other$unit = other.getUnit();
        if (this$unit == null ? other$unit != null : !this$unit.equals(other$unit)) return false;
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
        return other instanceof Material;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $vendor = this.getVendor();
        result = result * PRIME + ($vendor == null ? 43 : $vendor.hashCode());
        final java.lang.Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final java.lang.Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        final java.lang.Object $pricePerUnit = this.getPricePerUnit();
        result = result * PRIME + ($pricePerUnit == null ? 43 : $pricePerUnit.hashCode());
        final java.lang.Object $unit = this.getUnit();
        result = result * PRIME + ($unit == null ? 43 : $unit.hashCode());
        final java.lang.Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final java.lang.Object $imageUrl = this.getImageUrl();
        result = result * PRIME + ($imageUrl == null ? 43 : $imageUrl.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "Material(id=" + this.getId() + ", vendor=" + this.getVendor() + ", name=" + this.getName() + ", category=" + this.getCategory() + ", pricePerUnit=" + this.getPricePerUnit() + ", unit=" + this.getUnit() + ", description=" + this.getDescription() + ", imageUrl=" + this.getImageUrl() + ")";
    }

    @java.lang.SuppressWarnings("all")
    public Material() {
    }

    @java.lang.SuppressWarnings("all")
    public Material(final Long id, final Vendor vendor, final String name, final String category, final BigDecimal pricePerUnit, final String unit, final String description, final String imageUrl) {
        this.id = id;
        this.vendor = vendor;
        this.name = name;
        this.category = category;
        this.pricePerUnit = pricePerUnit;
        this.unit = unit;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}
