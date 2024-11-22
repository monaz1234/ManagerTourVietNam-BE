package com.ManagerTourVietNam.model.TourModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name= "tour" )
public class Tour {
    @Id
    @Column(name = "idtour", length = 4)
    private String idtour;           // ID của tour
    @Column(name = "idtour_type")
    private String idtour_type;
    @Column(name = "tourname")// ID loại tour
    private String tourname;         // Tên tour
    @Column(name = "location")
    private String location;
    @Column(name = "status")// Địa điểm tour
    private boolean status;
    @Column(name = "description")// Trạng thái tour
    private String description;
    @Column(name = "image")// Mô tả tour
    private String image;
    @Column(name = "is_deleted")// Đường dẫn đến hình ảnh của tour
    private boolean is_deleted;

    public Tour()
    {

    }

    public String getIdtour() {
        return idtour;
    }

    public void setIdtour(String idtour) {
        this.idtour = idtour;
    }

    public String getIdtour_type() {
        return idtour_type;
    }

    public void setIdtour_type(String idtour_type) {
        this.idtour_type = idtour_type;
    }

    public String getTourname() {
        return tourname;
    }

    public void setTourname(String tourname) {
        this.tourname = tourname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Tour(String idtour, String idtour_type, String tourname, String location, boolean status, String description, String image, boolean is_deleted) {
        this.idtour = idtour;
        this.idtour_type = idtour_type;
        this.tourname = tourname;
        this.location = location;
        this.status = status;
        this.description = description;
        this.image = image;
        this.is_deleted = is_deleted;
    }
}
