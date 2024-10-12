package com.ManagerTourVietNam.model.ServiceModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jdk.jfr.Enabled;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Service {
    @Id
    private String id_service;
    private String name_service;
    private String description;
    private LocalDate time_start;
    private LocalDate time_end;
    private String plant;
    private boolean status;

    public String getId_service() {
        return id_service;
    }

    public void setId_service(String id_service) {
        this.id_service = id_service;
    }

    public String getName_service() {
        return name_service;
    }

    public void setName_service(String name_service) {
        this.name_service = name_service;
    }

    public LocalDate getTime_start() {
        return time_start;
    }

    public void setTime_start(LocalDate time_start) {
        this.time_start = time_start;
    }

    public LocalDate getTime_end() {
        return time_end;
    }

    public void setTime_end(LocalDate time_end) {
        this.time_end = time_end;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Service(){}

    public Service(String id_service, String name_service, String description, LocalDate time_start, LocalDate time_end, String plant, boolean status) {
        this.id_service = id_service;
        this.name_service = name_service;
        this.description = description;
        this.time_start = time_start;
        this.time_end = time_end;
        this.plant = plant;
        this.status = status;
    }








}
