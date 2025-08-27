package dev.agro.crmagrobackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Field {
    
    private Long id;
    private String name;
    private BigDecimal area; // в гектарах
    private String crop; // культура
    private String season; // сезон (год)
    private String soil; // тип почвы
    private String geojson; // геометрия поля в формате GeoJSON
    private Long ownerId;
    private String description;
    private boolean isActive = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public Field() {}
    
    public Field(String name, BigDecimal area) {
        this.name = name;
        this.area = area;
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
    
    public BigDecimal getArea() {
        return area;
    }
    
    public void setArea(BigDecimal area) {
        this.area = area;
    }
    
    public String getCrop() {
        return crop;
    }
    
    public void setCrop(String crop) {
        this.crop = crop;
    }
    
    public String getSeason() {
        return season;
    }
    
    public void setSeason(String season) {
        this.season = season;
    }
    
    public String getSoil() {
        return soil;
    }
    
    public void setSoil(String soil) {
        this.soil = soil;
    }
    
    public String getGeojson() {
        return geojson;
    }
    
    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }
    
    public Long getOwnerId() {
        return ownerId;
    }
    
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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
