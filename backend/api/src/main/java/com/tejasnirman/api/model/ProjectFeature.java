package com.tejasnirman.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "project_features")
public class ProjectFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;
    private Integer quantity;
    private BigDecimal customPrice;


    @java.lang.SuppressWarnings("all")
    public static class ProjectFeatureBuilder {
        @java.lang.SuppressWarnings("all")
            private Long id;
        @java.lang.SuppressWarnings("all")
            private Project project;
        @java.lang.SuppressWarnings("all")
            private Feature feature;
        @java.lang.SuppressWarnings("all")
            private Integer quantity;
        @java.lang.SuppressWarnings("all")
            private BigDecimal customPrice;

        @java.lang.SuppressWarnings("all")
            ProjectFeatureBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public ProjectFeature.ProjectFeatureBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public ProjectFeature.ProjectFeatureBuilder project(final Project project) {
            this.project = project;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public ProjectFeature.ProjectFeatureBuilder feature(final Feature feature) {
            this.feature = feature;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public ProjectFeature.ProjectFeatureBuilder quantity(final Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public ProjectFeature.ProjectFeatureBuilder customPrice(final BigDecimal customPrice) {
            this.customPrice = customPrice;
            return this;
        }

        @java.lang.SuppressWarnings("all")
            public ProjectFeature build() {
            return new ProjectFeature(this.id, this.project, this.feature, this.quantity, this.customPrice);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "ProjectFeature.ProjectFeatureBuilder(id=" + this.id + ", project=" + this.project + ", feature=" + this.feature + ", quantity=" + this.quantity + ", customPrice=" + this.customPrice + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static ProjectFeature.ProjectFeatureBuilder builder() {
        return new ProjectFeature.ProjectFeatureBuilder();
    }

    @java.lang.SuppressWarnings("all")
    public Long getId() {
        return this.id;
    }

    @java.lang.SuppressWarnings("all")
    public Project getProject() {
        return this.project;
    }

    @java.lang.SuppressWarnings("all")
    public Feature getFeature() {
        return this.feature;
    }

    @java.lang.SuppressWarnings("all")
    public Integer getQuantity() {
        return this.quantity;
    }

    @java.lang.SuppressWarnings("all")
    public BigDecimal getCustomPrice() {
        return this.customPrice;
    }

    @java.lang.SuppressWarnings("all")
    public void setId(final Long id) {
        this.id = id;
    }

    @java.lang.SuppressWarnings("all")
    public void setProject(final Project project) {
        this.project = project;
    }

    @java.lang.SuppressWarnings("all")
    public void setFeature(final Feature feature) {
        this.feature = feature;
    }

    @java.lang.SuppressWarnings("all")
    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    @java.lang.SuppressWarnings("all")
    public void setCustomPrice(final BigDecimal customPrice) {
        this.customPrice = customPrice;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof ProjectFeature)) return false;
        final ProjectFeature other = (ProjectFeature) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$quantity = this.getQuantity();
        final java.lang.Object other$quantity = other.getQuantity();
        if (this$quantity == null ? other$quantity != null : !this$quantity.equals(other$quantity)) return false;
        final java.lang.Object this$project = this.getProject();
        final java.lang.Object other$project = other.getProject();
        if (this$project == null ? other$project != null : !this$project.equals(other$project)) return false;
        final java.lang.Object this$feature = this.getFeature();
        final java.lang.Object other$feature = other.getFeature();
        if (this$feature == null ? other$feature != null : !this$feature.equals(other$feature)) return false;
        final java.lang.Object this$customPrice = this.getCustomPrice();
        final java.lang.Object other$customPrice = other.getCustomPrice();
        if (this$customPrice == null ? other$customPrice != null : !this$customPrice.equals(other$customPrice)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof ProjectFeature;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $quantity = this.getQuantity();
        result = result * PRIME + ($quantity == null ? 43 : $quantity.hashCode());
        final java.lang.Object $project = this.getProject();
        result = result * PRIME + ($project == null ? 43 : $project.hashCode());
        final java.lang.Object $feature = this.getFeature();
        result = result * PRIME + ($feature == null ? 43 : $feature.hashCode());
        final java.lang.Object $customPrice = this.getCustomPrice();
        result = result * PRIME + ($customPrice == null ? 43 : $customPrice.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "ProjectFeature(id=" + this.getId() + ", project=" + this.getProject() + ", feature=" + this.getFeature() + ", quantity=" + this.getQuantity() + ", customPrice=" + this.getCustomPrice() + ")";
    }

    @java.lang.SuppressWarnings("all")
    public ProjectFeature() {
    }

    @java.lang.SuppressWarnings("all")
    public ProjectFeature(final Long id, final Project project, final Feature feature, final Integer quantity, final BigDecimal customPrice) {
        this.id = id;
        this.project = project;
        this.feature = feature;
        this.quantity = quantity;
        this.customPrice = customPrice;
    }
}
