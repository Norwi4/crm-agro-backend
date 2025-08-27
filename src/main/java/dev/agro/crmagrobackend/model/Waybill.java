package dev.agro.crmagrobackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import dev.agro.crmagrobackend.model.enums.WaybillStatus;

public class Waybill {
    
    private Long id;
    private String number;
    private Long taskId;
    private Long machineId;
    private String driverId; // Employee ID
    private LocalDateTime departureTime;
    private LocalDateTime returnTime;
    private BigDecimal departureOdometer;
    private BigDecimal returnOdometer;
    private BigDecimal departureFuel;
    private BigDecimal returnFuel;
    private BigDecimal departureHours;
    private BigDecimal returnHours;
    private String route; // JSON with route points
    private String fuelTransactions; // JSON array of fuel transaction IDs
    private WaybillStatus status;
    private String notes;
    private String documentPath; // Path to generated PDF
    private boolean isExportedTo1C;
    private String exportStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public Waybill() {}
    
    public Waybill(String number, Long taskId, Long machineId, String driverId) {
        this.number = number;
        this.taskId = taskId;
        this.machineId = machineId;
        this.driverId = driverId;
        this.status = WaybillStatus.DRAFT;
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
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public Long getTaskId() {
        return taskId;
    }
    
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    
    public Long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
    
    public String getDriverId() {
        return driverId;
    }
    
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
    
    public LocalDateTime getReturnTime() {
        return returnTime;
    }
    
    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }
    
    public BigDecimal getDepartureOdometer() {
        return departureOdometer;
    }
    
    public void setDepartureOdometer(BigDecimal departureOdometer) {
        this.departureOdometer = departureOdometer;
    }
    
    public BigDecimal getReturnOdometer() {
        return returnOdometer;
    }
    
    public void setReturnOdometer(BigDecimal returnOdometer) {
        this.returnOdometer = returnOdometer;
    }
    
    public BigDecimal getDepartureFuel() {
        return departureFuel;
    }
    
    public void setDepartureFuel(BigDecimal departureFuel) {
        this.departureFuel = departureFuel;
    }
    
    public BigDecimal getReturnFuel() {
        return returnFuel;
    }
    
    public void setReturnFuel(BigDecimal returnFuel) {
        this.returnFuel = returnFuel;
    }
    
    public BigDecimal getDepartureHours() {
        return departureHours;
    }
    
    public void setDepartureHours(BigDecimal departureHours) {
        this.departureHours = departureHours;
    }
    
    public BigDecimal getReturnHours() {
        return returnHours;
    }
    
    public void setReturnHours(BigDecimal returnHours) {
        this.returnHours = returnHours;
    }
    
    public String getRoute() {
        return route;
    }
    
    public void setRoute(String route) {
        this.route = route;
    }
    
    public String getFuelTransactions() {
        return fuelTransactions;
    }
    
    public void setFuelTransactions(String fuelTransactions) {
        this.fuelTransactions = fuelTransactions;
    }
    
    public WaybillStatus getStatus() {
        return status;
    }
    
    public void setStatus(WaybillStatus status) {
        this.status = status;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public String getDocumentPath() {
        return documentPath;
    }
    
    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
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
