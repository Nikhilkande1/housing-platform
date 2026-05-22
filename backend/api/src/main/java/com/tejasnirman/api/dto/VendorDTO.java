package com.tejasnirman.api.dto;

public class VendorDTO {
    private Long id;
    private String companyName;
    private String category;
    private String description;
    private Double rating;
    private Integer reviewCount;
    private String location;
    private String imageUrl;
    private Boolean isVerified;
    private String contactEmail;
    private String contactPhone;

    public VendorDTO() {}

    public VendorDTO(Long id, String companyName, String category, String description, Double rating, Integer reviewCount, String location, String imageUrl, Boolean isVerified, String contactEmail, String contactPhone) {
        this.id = id;
        this.companyName = companyName;
        this.category = category;
        this.description = description;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.location = location;
        this.imageUrl = imageUrl;
        this.isVerified = isVerified;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
