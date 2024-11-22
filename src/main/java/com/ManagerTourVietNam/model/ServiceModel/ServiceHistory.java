package com.ManagerTourVietNam.model.ServiceModel;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table (name ="servicehistory")
public class ServiceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history")
    private Long id_history;
    @Column(name = "id_service", length = 4)
    private String id_service;
    @Column(name = "old_name_service")
    private String old_name_service;
    @Column(name = "new_name_service")
    private String new_name_service;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Column(name = "old_status")
    private boolean old_status;
    @Column(name = "new_status")
    private boolean new_status;
    @Column(name = "old_description")
    private String old_description;
    @Column(name = "new_description")
    private String new_description;
    @Column(name = "changed_by")
    private String changed_by;
    @Column(name = "notes")
    private String notes;

    public ServiceHistory(Long id_history, String id_service, String old_name_service, String new_name_service, LocalDateTime timestamp, boolean old_status, boolean new_status, String old_description, String new_description, String changed_by, String notes) {
        this.id_history = id_history;
        this.id_service = id_service;
        this.old_name_service = old_name_service;
        this.new_name_service = new_name_service;
        this.timestamp = timestamp;
        this.old_status = old_status;
        this.new_status = new_status;
        this.old_description = old_description;
        this.new_description = new_description;
        this.changed_by = changed_by;
        this.notes = notes;
    }

    public ServiceHistory(){

    }


    public Long getId_history() {
        return id_history;
    }

    public void setId_history(Long   id_history) {
        this.id_history = id_history;
    }

    public String getId_service() {
        return id_service;
    }

    public void setId_service(String id_service) {
        this.id_service = id_service;
    }
    public String getOld_name_service() {
        return old_name_service;
    }

    public void setOld_name_service(String old_name_service) {
        this.old_name_service = old_name_service;
    }

    public String getNew_name_service() {
        return new_name_service;
    }

    public void setNew_name_service(String new_name_service) {
        this.new_name_service = new_name_service;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isOld_status() {
        return old_status;
    }

    public void setOld_status(boolean old_status) {
        this.old_status = old_status;
    }

    public boolean isNew_status() {
        return new_status;
    }

    public void setNew_status(boolean new_status) {
        this.new_status = new_status;
    }

    public String getOld_description() {
        return old_description;
    }

    public void setOld_description(String old_description) {
        this.old_description = old_description;
    }

    public String getNew_description() {
        return new_description;
    }

    public void setNew_description(String new_description) {
        this.new_description = new_description;
    }

    public String getChanged_by() {
        return changed_by;
    }

    public void setChanged_by(String changed_by) {
        this.changed_by = changed_by;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "ServiceHistory{" +
                "id_history=" + id_history +
                ", id_service='" + id_service + '\'' +
                ", old_name_service='" + old_name_service + '\'' +
                ", new_name_service='" + new_name_service + '\'' +
                ", timestamp=" + timestamp +
                ", old_status=" + old_status +
                ", new_status=" + new_status +
                ", old_description='" + old_description + '\'' +
                ", new_description='" + new_description + '\'' +
                ", changed_by='" + changed_by + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
