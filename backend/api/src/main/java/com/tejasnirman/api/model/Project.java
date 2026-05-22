package com.tejasnirman.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String name;
    private Integer plotSize;
    private BigDecimal budget;
    private String architecturalStyle;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
    private Double completionPercentage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) status = ProjectStatus.PLANNING;
        if (completionPercentage == null) completionPercentage = 0.0;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    @java.lang.SuppressWarnings("all")
    public static class ProjectBuilder {
        @java.lang.SuppressWarnings("all")
            private Long id;
        @java.lang.SuppressWarnings("all")
            private User user;
        @java.lang.SuppressWarnings("all")
            private String name;
        @java.lang.SuppressWarnings("all")
            private Integer plotSize;
        @java.lang.SuppressWarnings("all")
            private BigDecimal budget;
        @java.lang.SuppressWarnings("all")
            private String architecturalStyle;
        @java.lang.SuppressWarnings("all")
            private ProjectStatus status;
        @java.lang.SuppressWarnings("all")
            private Double completionPercentage;
        @java.lang.SuppressWarnings("all")
            private LocalDateTime createdAt;
        @java.lang.SuppressWarnings("all")
            private LocalDateTime updatedAt;

        @java.lang.SuppressWarnings("all")
            ProjectBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Project.ProjectBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Project.ProjectBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Project.ProjectBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Project.ProjectBuilder plotSize(final Integer plotSize) {
            this.plotSize = plotSize;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Project.ProjectBuilder budget(final BigDecimal budget) {
            this.budget = budget;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Project.ProjectBuilder architecturalStyle(final String architecturalStyle) {
            this.architecturalStyle = architecturalStyle;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Project.ProjectBuilder status(final ProjectStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Project.ProjectBuilder completionPercentage(final Double completionPercentage) {
            this.completionPercentage = completionPercentage;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Project.ProjectBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
            public Project.ProjectBuilder updatedAt(final LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
            public Project build() {
            return new Project(this.id, this.user, this.name, this.plotSize, this.budget, this.architecturalStyle, this.status, this.completionPercentage, this.createdAt, this.updatedAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "Project.ProjectBuilder(id=" + this.id + ", user=" + this.user + ", name=" + this.name + ", plotSize=" + this.plotSize + ", budget=" + this.budget + ", architecturalStyle=" + this.architecturalStyle + ", status=" + this.status + ", completionPercentage=" + this.completionPercentage + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static Project.ProjectBuilder builder() {
        return new Project.ProjectBuilder();
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
    public String getName() {
        return this.name;
    }

    @java.lang.SuppressWarnings("all")
    public Integer getPlotSize() {
        return this.plotSize;
    }

    @java.lang.SuppressWarnings("all")
    public BigDecimal getBudget() {
        return this.budget;
    }

    @java.lang.SuppressWarnings("all")
    public String getArchitecturalStyle() {
        return this.architecturalStyle;
    }

    @java.lang.SuppressWarnings("all")
    public ProjectStatus getStatus() {
        return this.status;
    }

    @java.lang.SuppressWarnings("all")
    public Double getCompletionPercentage() {
        return this.completionPercentage;
    }

    @java.lang.SuppressWarnings("all")
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @java.lang.SuppressWarnings("all")
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
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
    public void setName(final String name) {
        this.name = name;
    }

    @java.lang.SuppressWarnings("all")
    public void setPlotSize(final Integer plotSize) {
        this.plotSize = plotSize;
    }

    @java.lang.SuppressWarnings("all")
    public void setBudget(final BigDecimal budget) {
        this.budget = budget;
    }

    @java.lang.SuppressWarnings("all")
    public void setArchitecturalStyle(final String architecturalStyle) {
        this.architecturalStyle = architecturalStyle;
    }

    @java.lang.SuppressWarnings("all")
    public void setStatus(final ProjectStatus status) {
        this.status = status;
    }

    @java.lang.SuppressWarnings("all")
    public void setCompletionPercentage(final Double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    @java.lang.SuppressWarnings("all")
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.SuppressWarnings("all")
    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof Project)) return false;
        final Project other = (Project) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$plotSize = this.getPlotSize();
        final java.lang.Object other$plotSize = other.getPlotSize();
        if (this$plotSize == null ? other$plotSize != null : !this$plotSize.equals(other$plotSize)) return false;
        final java.lang.Object this$completionPercentage = this.getCompletionPercentage();
        final java.lang.Object other$completionPercentage = other.getCompletionPercentage();
        if (this$completionPercentage == null ? other$completionPercentage != null : !this$completionPercentage.equals(other$completionPercentage)) return false;
        final java.lang.Object this$user = this.getUser();
        final java.lang.Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final java.lang.Object this$name = this.getName();
        final java.lang.Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final java.lang.Object this$budget = this.getBudget();
        final java.lang.Object other$budget = other.getBudget();
        if (this$budget == null ? other$budget != null : !this$budget.equals(other$budget)) return false;
        final java.lang.Object this$architecturalStyle = this.getArchitecturalStyle();
        final java.lang.Object other$architecturalStyle = other.getArchitecturalStyle();
        if (this$architecturalStyle == null ? other$architecturalStyle != null : !this$architecturalStyle.equals(other$architecturalStyle)) return false;
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        final java.lang.Object this$updatedAt = this.getUpdatedAt();
        final java.lang.Object other$updatedAt = other.getUpdatedAt();
        if (this$updatedAt == null ? other$updatedAt != null : !this$updatedAt.equals(other$updatedAt)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof Project;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $plotSize = this.getPlotSize();
        result = result * PRIME + ($plotSize == null ? 43 : $plotSize.hashCode());
        final java.lang.Object $completionPercentage = this.getCompletionPercentage();
        result = result * PRIME + ($completionPercentage == null ? 43 : $completionPercentage.hashCode());
        final java.lang.Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final java.lang.Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final java.lang.Object $budget = this.getBudget();
        result = result * PRIME + ($budget == null ? 43 : $budget.hashCode());
        final java.lang.Object $architecturalStyle = this.getArchitecturalStyle();
        result = result * PRIME + ($architecturalStyle == null ? 43 : $architecturalStyle.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        final java.lang.Object $updatedAt = this.getUpdatedAt();
        result = result * PRIME + ($updatedAt == null ? 43 : $updatedAt.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "Project(id=" + this.getId() + ", user=" + this.getUser() + ", name=" + this.getName() + ", plotSize=" + this.getPlotSize() + ", budget=" + this.getBudget() + ", architecturalStyle=" + this.getArchitecturalStyle() + ", status=" + this.getStatus() + ", completionPercentage=" + this.getCompletionPercentage() + ", createdAt=" + this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + ")";
    }

    @java.lang.SuppressWarnings("all")
    public Project() {
    }

    @java.lang.SuppressWarnings("all")
    public Project(final Long id, final User user, final String name, final Integer plotSize, final BigDecimal budget, final String architecturalStyle, final ProjectStatus status, final Double completionPercentage, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.plotSize = plotSize;
        this.budget = budget;
        this.architecturalStyle = architecturalStyle;
        this.status = status;
        this.completionPercentage = completionPercentage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
