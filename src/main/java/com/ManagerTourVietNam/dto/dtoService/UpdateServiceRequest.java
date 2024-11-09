package com.ManagerTourVietNam.dto.dtoService;

public class UpdateServiceRequest {
    private String id_service;
    private String newNameService;
    private boolean newStatus;
    private String newDescription;
    private String changedBy;

    // Getters và Setters
    public String getId_service() {
        return id_service;
    }

    public void setId_service(String id_service) {
        this.id_service = id_service;
    }

    public String getNewNameService() {
        return newNameService;
    }

    public void setNewNameService(String newNameService) {
        this.newNameService = newNameService;
    }
    public boolean isNewStatus() {
        return newStatus;
    }

    public void setNewStatus(boolean newStatus) {
        this.newStatus = newStatus;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }
}
