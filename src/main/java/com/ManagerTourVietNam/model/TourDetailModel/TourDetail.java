package com.ManagerTourVietNam.model.TourDetailModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDate;
@Entity
@Table(name = "tour_detail")
public class TourDetail {
    @Id
    private String idTour;           // ID của tour
    private String idVehicles;       // ID phương tiện
    private String idHotel;          // ID khách sạn
    private LocalDate depart;       // Ngày khởi hành
    private int price;               // Giá tour
    private int place;               // Số lượng chỗ
    private String idService;        // ID dịch vụ

    public TourDetail()
    {

    }
    public TourDetail(String idTour, String idVehicles, String idHotel, LocalDate depart,
                      int price, int place, String idService) {
        this.idTour = idTour;
        this.idVehicles = idVehicles;
        this.idHotel = idHotel;
        this.depart = depart;
        this.price = price;
        this.place = place;
        this.idService = idService;
    }

    // Getter và Setter
    public String getIdTour() {
        return idTour;
    }

    public void setIdTour(String idTour) {
        this.idTour = idTour;
    }

    public String getIdVehicles() {
        return idVehicles;
    }

    public void setIdVehicles(String idVehicles) {
        this.idVehicles = idVehicles;
    }

    public String getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }

    public LocalDate getDepart() {
        return depart;
    }

    public void setDepart(LocalDate depart) {
        this.depart = depart;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    @Override
    public String toString() {
        return "TourDetail{" +
                "idTour='" + idTour + '\'' +
                ", idVehicles='" + idVehicles + '\'' +
                ", idHotel='" + idHotel + '\'' +
                ", depart=" + depart +
                ", price=" + price +
                ", place=" + place +
                ", idService='" + idService + '\'' +
                '}';
    }
}

