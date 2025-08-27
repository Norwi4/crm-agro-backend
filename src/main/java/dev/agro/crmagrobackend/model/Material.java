package dev.agro.crmagrobackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Material {
    
    private Long id;
    private String name;
    private String unit;
    private String batch;
    private String certificate;
    private BigDecimal stock;
    private BigDecimal minStock;
    private String category;
    private String description;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public Material() {}
    
    public Material(String name, String unit, String category) {
        this.name = name;
        this.unit = unit;
        this.category = category;
        this.stock = BigDecimal.ZERO;
        this.minStock = BigDecimal.ZERO;
        this.isActive = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
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
    
    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public String getBatch() {
        return batch;
    }
    
    public void setBatch(String batch) {
        this.batch = batch;
    }
    
    public String getCertificate() {
        return certificate;
    }
    
    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
    
    public BigDecimal getStock() {
        return stock;
    }
    
    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }
    
    public BigDecimal getMinStock() {
        return minStock;
    }
    
    public void setMinStock(BigDecimal minStock) {
        this.minStock = minStock;
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
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
