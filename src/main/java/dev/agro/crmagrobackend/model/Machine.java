package dev.agro.crmagrobackend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import dev.agro.crmagrobackend.model.enums.MachineStatus;

public class Machine {
    
    private Long id;
    private String name;
    private String type;
    private String plate;
    private String vin;
    private String model;
    private String manufacturer;
    private BigDecimal normFuel;
    private BigDecimal hourlyCost;
    private String telematicsId;
    private MachineStatus status;
    private LocalDate lastMaintenanceDate;
    private LocalDate nextMaintenanceDate;
    private Integer totalHours;
    private BigDecimal totalFuel;
    private String description;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public Machine() {}
    
    public Machine(String name, String type, String model, String manufacturer) {
        this.name = name;
        this.type = type;
        this.model = model;
        this.manufacturer = manufacturer;
        this.status = MachineStatus.ACTIVE;
        this.totalHours = 0;
        this.totalFuel = BigDecimal.ZERO;
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
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getPlate() {
        return plate;
    }
    
    public void setPlate(String plate) {
        this.plate = plate;
    }
    
    public String getVin() {
        return vin;
    }
    
    public void setVin(String vin) {
        this.vin = vin;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    public BigDecimal getNormFuel() {
        return normFuel;
    }
    
    public void setNormFuel(BigDecimal normFuel) {
        this.normFuel = normFuel;
    }
    
    public BigDecimal getHourlyCost() {
        return hourlyCost;
    }
    
    public void setHourlyCost(BigDecimal hourlyCost) {
        this.hourlyCost = hourlyCost;
    }
    
    public String getTelematicsId() {
        return telematicsId;
    }
    
    public void setTelematicsId(String telematicsId) {
        this.telematicsId = telematicsId;
    }
    
    public MachineStatus getStatus() {
        return status;
    }
    
    public void setStatus(MachineStatus status) {
        this.status = status;
    }
    
    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }
    
    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }
    
    public LocalDate getNextMaintenanceDate() {
        return nextMaintenanceDate;
    }
    
    public void setNextMaintenanceDate(LocalDate nextMaintenanceDate) {
        this.nextMaintenanceDate = nextMaintenanceDate;
    }
    
    public Integer getTotalHours() {
        return totalHours;
    }
    
    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }
    
    public BigDecimal getTotalFuel() {
        return totalFuel;
    }
    
    public void setTotalFuel(BigDecimal totalFuel) {
        this.totalFuel = totalFuel;
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
