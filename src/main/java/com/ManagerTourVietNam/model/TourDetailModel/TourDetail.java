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
    @Column(name = "idtourdetail", length = 4)
    private String idtourdetail;

    @Id
    @Column(name = "idtour",  length = 4)
    private String idtour;
    @Id
    @Column(name = "id_vehicles", length = 4)
    private String id_vehicles;
    @Id
    @Column(name = "id_hotel", length = 4)
    private String id_hotel;
    @Id
    @Column(name = "id_service", length = 4)
    private String id_service;

    @Column(name = "depart")
    private LocalDate depart;
    @Column(name = "total_price")// Ngày khởi hành
    private double total_price;
    @Column(name = "place")
    private int place;
    @Column(name = "is_deleted")// số chỗ
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

    public String getIdtourdetail() {
        return idtourdetail;
    }

    public void setIdtourdetail(String idtourdetail) {
        this.idtourdetail = idtourdetail;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public TourDetail()
    {

    }

    public TourDetail(String idtourdetail, String idtour, String id_vehicles, String id_hotel, String id_service, LocalDate depart, double total_price, int place, boolean is_deleted, Tour tour, Vehicles vehicles, Hotel hotel, Service service) {
        this.idtourdetail = idtourdetail;
        this.idtour = idtour;
        this.id_vehicles = id_vehicles;
        this.id_hotel = id_hotel;
        this.id_service = id_service;
        this.depart = depart;
        this.total_price = total_price;
        this.place = place;
        this.is_deleted = is_deleted;
        this.tour = tour;
        this.vehicles = vehicles;
        this.hotel = hotel;
        this.service = service;
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