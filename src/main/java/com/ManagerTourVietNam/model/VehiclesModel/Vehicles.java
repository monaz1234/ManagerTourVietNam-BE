package com.ManagerTourVietNam.model.VehiclesModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
@Entity
@Table(name = "vehicles")
public class Vehicles {
    @Id
    @Column(name = "id_vehicles", length = 4)
    private String id_vehicles;
    @Column(name = "name_vehicles")
    private String name_vehicles;
    @Column(name = "place_vehicles")
    private int place_vehicles;
    @Column(name = "driver")
    private String driver; // tương ứng với cột 'driver'
    @Column(name = "image")
    private String image; // tương ứng với cột 'image'
    @Column(name = "description")
    private String description; // tương ứng với cột 'description'
    @Column(name = "status")
    private boolean status; // tương ứng với cột 'status' (một giá trị boolean)
    @Column(name = "price")
    private double price;

    public Vehicles(String id_vehicles, String name_vehicles, int place_vehicles, String driver, String image, String description, boolean status, double price) {
        this.id_vehicles = id_vehicles;
        this.name_vehicles = name_vehicles;
        this.place_vehicles = place_vehicles;
        this.driver = driver;
        this.image = image;
        this.description = description;
        this.status = status;
        this.price = price;
    }

    public Vehicles()
    {

    }
    public String getId_vehicles() {
        return id_vehicles;
    }

    public void setId_vehicles(String id_vehicles) {
        this.id_vehicles = id_vehicles;
    }

    public String getName_vehicles() {
        return name_vehicles;
    }

    public void setName_vehicles(String name_vehicles) {
        this.name_vehicles = name_vehicles;
    }

    public int getPlace_vehicles() {
        return place_vehicles;
    }

    public void setPlace_vehicles(int place_vehicles) {
        this.place_vehicles = place_vehicles;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
