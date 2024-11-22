package com.ManagerTourVietNam.model.ServiceModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.Enabled;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table (name="service")
public class Service {
    @Id
    @Column(name="id_service", length = 4)
    private String id_service;
    @Column(name = "name_service")
    private String name_service;
    @Column(name = "description")
    private String description;
    @Column(name = "time_start")
    private LocalDate time_start;
    @Column(name = "time_end")
    private LocalDate time_end;
    @Column(name = "plant")
    private String plant;
    @Column(name = "ststus")
    private boolean status;
    @Column(name="price")
    private double price;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Service(String id_service, String name_service, String description, LocalDate time_start, LocalDate time_end, String plant, boolean status, double price) {
        this.id_service = id_service;
        this.name_service = name_service;
        this.description = description;
        this.time_start = time_start;
        this.time_end = time_end;
        this.plant = plant;
        this.status = status;
        this.price = price;
    }

    public Service(){}



    @Override
    public String toString() {
        return "Service{" +
                "id_service='" + id_service + '\'' +
                ", name_service='" + name_service + '\'' +
                ", description='" + description + '\'' +
                ", time_start=" + time_start +
                ", time_end=" + time_end +
                ", plant='" + plant + '\'' +
                ", status=" + status +
                '}';
    }
}
