package com.ManagerTourVietNam.model.TourDetailModel;
import java.io.Serializable;

public class TourDetailId implements Serializable {

    private String idtour;
    private String id_vehicles;
    private String id_service;
    private String id_hotel;

    public TourDetailId() {

    }
    public TourDetailId(String idtour) {
        this.idtour = idtour;
    }


    public TourDetailId(String idtour, String id_vehicles, String id_hotel, String id_service) {
        this.idtour = idtour;
        this.id_vehicles = id_vehicles;
        this.id_hotel = id_hotel;
        this.id_service = id_service;
    }

    public String getIdtour() {
        return idtour;
    }

    public void setIdtour(String idtour) {
        this.idtour = idtour;
    }

    public String getId_vehicles() {
        return id_vehicles;
    }

    public void setId_vehicles(String id_vehicles) {
        this.id_vehicles = id_vehicles;
    }

    public String getId_service() {
        return id_service;
    }

    public void setId_service(String id_service) {
        this.id_service = id_service;
    }

    public String getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }
}