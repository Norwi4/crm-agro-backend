package dev.agro.crmagrobackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import dev.agro.crmagrobackend.model.enums.TaskType;
import dev.agro.crmagrobackend.model.enums.TaskStatus;

public class Task {
    
    private Long id;
    private String title;
    private String description;
    private TaskType type;
    private Long fieldId;
    private Long machineId;
    private String assignees; // JSON array of employee IDs
    private LocalDateTime startPlan;
    private LocalDateTime endPlan;
    private LocalDateTime startActual;
    private LocalDateTime endActual;
    private String checklist; // JSON array of checklist items
    private TaskStatus status;
    private BigDecimal plannedArea;
    private BigDecimal actualArea;
    private BigDecimal plannedHours;
    private BigDecimal actualHours;
    private BigDecimal plannedFuel;
    private BigDecimal actualFuel;
    private String notes;
    private Integer priority;
    private Long parentTaskId;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public Task() {}
    
    public Task(String title, TaskType type, Long fieldId, Long machineId) {
        this.title = title;
        this.type = type;
        this.fieldId = fieldId;
        this.machineId = machineId;
        this.status = TaskStatus.PLANNED;
        this.priority = 1;
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
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public TaskType getType() {
        return type;
    }
    
    public void setType(TaskType type) {
        this.type = type;
    }
    
    public Long getFieldId() {
        return fieldId;
    }
    
    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }
    
    public Long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
    
    public String getAssignees() {
        return assignees;
    }
    
    public void setAssignees(String assignees) {
        this.assignees = assignees;
    }
    
    public LocalDateTime getStartPlan() {
        return startPlan;
    }
    
    public void setStartPlan(LocalDateTime startPlan) {
        this.startPlan = startPlan;
    }
    
    public LocalDateTime getEndPlan() {
        return endPlan;
    }
    
    public void setEndPlan(LocalDateTime endPlan) {
        this.endPlan = endPlan;
    }
    
    public LocalDateTime getStartActual() {
        return startActual;
    }
    
    public void setStartActual(LocalDateTime startActual) {
        this.startActual = startActual;
    }
    
    public LocalDateTime getEndActual() {
        return endActual;
    }
    
    public void setEndActual(LocalDateTime endActual) {
        this.endActual = endActual;
    }
    
    public String getChecklist() {
        return checklist;
    }
    
    public void setChecklist(String checklist) {
        this.checklist = checklist;
    }
    
    public TaskStatus getStatus() {
        return status;
    }
    
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
    
    public BigDecimal getPlannedArea() {
        return plannedArea;
    }
    
    public void setPlannedArea(BigDecimal plannedArea) {
        this.plannedArea = plannedArea;
    }
    
    public BigDecimal getActualArea() {
        return actualArea;
    }
    
    public void setActualArea(BigDecimal actualArea) {
        this.actualArea = actualArea;
    }
    
    public BigDecimal getPlannedHours() {
        return plannedHours;
    }
    
    public void setPlannedHours(BigDecimal plannedHours) {
        this.plannedHours = plannedHours;
    }
    
    public BigDecimal getActualHours() {
        return actualHours;
    }
    
    public void setActualHours(BigDecimal actualHours) {
        this.actualHours = actualHours;
    }
    
    public BigDecimal getPlannedFuel() {
        return plannedFuel;
    }
    
    public void setPlannedFuel(BigDecimal plannedFuel) {
        this.plannedFuel = plannedFuel;
    }
    
    public BigDecimal getActualFuel() {
        return actualFuel;
    }
    
    public void setActualFuel(BigDecimal actualFuel) {
        this.actualFuel = actualFuel;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public Integer getPriority() {
        return priority;
    }
    
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    
    public Long getParentTaskId() {
        return parentTaskId;
    }
    
    public void setParentTaskId(Long parentTaskId) {
        this.parentTaskId = parentTaskId;
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
