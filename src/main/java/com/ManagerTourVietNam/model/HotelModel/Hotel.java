package com.ManagerTourVietNam.model.HotelModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
@Entity
@Table (name="hotel")
public class Hotel {
    @Id
    @Column(name = "id_hotel")
    private String id_hotel; // tương ứng với cột 'id_hotel'

    private String name_hotel; // tương ứng với cột 'name_hotel'
    private String description; // tương ứng với cột 'description'
    private String image; // tương ứng với cột 'image'
    private boolean status; // tương ứng với cột 'status' (giá trị boolean)
    @Column(name = "price")
    private double price;

    public  Hotel()
    {

    }
    public String getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getName_hotel() {
        return name_hotel;
    }

    public void setName_hotel(String name_hotel) {
        this.name_hotel = name_hotel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
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

    public Hotel(String id_hotel, String name_hotel, String description, String image, boolean status, double price) {
        this.id_hotel = id_hotel;
        this.name_hotel = name_hotel;
        this.description = description;
        this.image = image;
        this.status = status;
        this.price = price;
    }
}
