package dev.agro.crmagrobackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import dev.agro.crmagrobackend.model.enums.FuelTransactionStatus;

public class FuelTransaction {
    
    private Long id;
    private Long fuelCardId;
    private String transactionNumber;
    private LocalDateTime transactionTime;
    private String stationName;
    private String stationAddress;
    private String fuelType;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal totalAmount;
    private BigDecimal odometer;
    private String waybillId; // Reference to waybill if matched
    private String workEntryId; // Reference to work entry if matched
    private FuelTransactionStatus status;
    private String anomalyType; // OVERCONSUMPTION, NIGHT_REFUELING, UNAUTHORIZED_STATION, etc.
    private String notes;
    private boolean isExportedTo1C;
    private String exportStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public FuelTransaction() {}
    
    public FuelTransaction(Long fuelCardId, String transactionNumber, LocalDateTime transactionTime, 
                          String stationName, String fuelType, BigDecimal quantity, BigDecimal price) {
        this.fuelCardId = fuelCardId;
        this.transactionNumber = transactionNumber;
        this.transactionTime = transactionTime;
        this.stationName = stationName;
        this.fuelType = fuelType;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = quantity.multiply(price);
        this.status = FuelTransactionStatus.PENDING;
        this.isExportedTo1C = false;
        this.exportStatus = "NOT_EXPORTED";
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
    
    public Long getFuelCardId() {
        return fuelCardId;
    }
    
    public void setFuelCardId(Long fuelCardId) {
        this.fuelCardId = fuelCardId;
    }
    
    public String getTransactionNumber() {
        return transactionNumber;
    }
    
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }
    
    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }
    
    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
    
    public String getStationName() {
        return stationName;
    }
    
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    
    public String getStationAddress() {
        return stationAddress;
    }
    
    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }
    
    public String getFuelType() {
        return fuelType;
    }
    
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    
    public BigDecimal getQuantity() {
        return quantity;
    }
    
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public BigDecimal getOdometer() {
        return odometer;
    }
    
    public void setOdometer(BigDecimal odometer) {
        this.odometer = odometer;
    }
    
    public String getWaybillId() {
        return waybillId;
    }
    
    public void setWaybillId(String waybillId) {
        this.waybillId = waybillId;
    }
    
    public String getWorkEntryId() {
        return workEntryId;
    }
    
    public void setWorkEntryId(String workEntryId) {
        this.workEntryId = workEntryId;
    }
    
    public FuelTransactionStatus getStatus() {
        return status;
    }
    
    public void setStatus(FuelTransactionStatus status) {
        this.status = status;
    }
    
    public String getAnomalyType() {
        return anomalyType;
    }
    
    public void setAnomalyType(String anomalyType) {
        this.anomalyType = anomalyType;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public boolean isExportedTo1C() {
        return isExportedTo1C;
    }
    
    public void setExportedTo1C(boolean exportedTo1C) {
        isExportedTo1C = exportedTo1C;
    }
    
    public String getExportStatus() {
        return exportStatus;
    }
    
    public void setExportStatus(String exportStatus) {
        this.exportStatus = exportStatus;
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
