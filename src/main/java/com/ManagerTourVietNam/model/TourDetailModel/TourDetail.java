package com.ManagerTourVietNam.model.TourDetailModel;
import com.ManagerTourVietNam.model.HotelModel.Hotel;
import com.ManagerTourVietNam.model.VehiclesModel.Vehicles;
import jakarta.persistence.*;

import java.time.LocalDate;
import com.ManagerTourVietNam.model.ServiceModel.Service;
import com.ManagerTourVietNam.model.TourModel.Tour;
@Entity
@Table(name = "tour_detail")
@IdClass(TourDetailId.class)
public class TourDetail {
    @Id
    private String idtour;
    @Id
    private String id_vehicles;
    @Id
    private String id_hotel;
    @Id
    private String id_service;

    private LocalDate depart;       // Ngày khởi hành
    private double total_price;
    private int place;              // số chỗ
    private boolean is_deleted;

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

    public String getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getId_service() {
        return id_service;
    }

    public void setId_service(String id_service) {
        this.id_service = id_service;
    }

    public LocalDate getDepart() {
        return depart;
    }

    public void setDepart(LocalDate depart) {
        this.depart = depart;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public TourDetail(String idtour, String id_vehicles, String id_hotel, String id_service, LocalDate depart, double total_price, int place, boolean is_deleted) {
        this.idtour = idtour;
        this.id_vehicles = id_vehicles;
        this.id_hotel = id_hotel;
        this.id_service = id_service;
        this.depart = depart;
        this.total_price = total_price;
        this.place = place;
        this.is_deleted = is_deleted;
    }

    public TourDetail()
    {

    }
    @ManyToOne
    @JoinColumn(name = "idtour", insertable = false, updatable = false)
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "id_vehicles", insertable = false, updatable = false)
    private Vehicles vehicles;

    @ManyToOne
    @JoinColumn(name = "id_hotel", insertable = false, updatable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "id_service", insertable = false, updatable = false)
    private Service service;

    // Getters and Setters
    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Vehicles getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
    }

    public void set_hotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Hotel getHotel(){
        return hotel;
    }


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

}