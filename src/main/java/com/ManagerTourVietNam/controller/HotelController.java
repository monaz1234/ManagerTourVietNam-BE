package com.ManagerTourVietNam.controller;

import com.ManagerTourVietNam.model.Hotel;
import com.ManagerTourVietNam.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    //lay thong tin khach san
    @GetMapping("/api/hotels")
    public List<Hotel> getAllHotel(){
        return hotelService.getAllHotel();
    }

    //them khach san

    @GetMapping("/api/hotel")
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    //sua thong tin khach san
    @PutMapping("/api/hotel/{id}")
    public Hotel updateHotel(@PathVariable String id, @RequestBody Hotel hotelDetail){
        return hotelService.updateHotel(id,hotelDetail);
    }

    //Xoa thong tin phuong tien
    @DeleteMapping("/api/hotel/{id}")
    public void deleteHotel(@PathVariable String id){
        hotelService.deleteHotel(id);
    }

    //Tim kiem thong tin phuong tien
    @GetMapping("/api/hotel/{id}")
    public Optional<Hotel> findHotel(@PathVariable String id){
        return hotelService.findHotelById(id);
    }

}
