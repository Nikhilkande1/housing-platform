package com.tejasnirman.api.dto;

import java.math.BigDecimal;

public class MaterialDTO {
    private Long id;
    private String name;
    private String category;
    private String description;
    private BigDecimal pricePerUnit;
    private String unit;
    private Long vendorId;
    private String vendorName;
    private String imageUrl;

    public MaterialDTO() {}

    public MaterialDTO(Long id, String name, String category, String description, BigDecimal pricePerUnit, String unit, Long vendorId, String vendorName, String imageUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.pricePerUnit = pricePerUnit;
        this.unit = unit;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
