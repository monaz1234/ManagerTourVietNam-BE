package com.ManagerTourVietNam.controller.TourDetailController;

import com.ManagerTourVietNam.service.TourDetailService.TourDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api")
public class TourDetailController {
    @Autowired
    private TourDetailService tourDetailService;

    // lấy giá hotel
    @GetMapping("/tour_detail/hotel_price")
    public double getHotelPrice(@RequestParam("id") String idtour) {
        return tourDetailService.getHotelPrice(idtour);
    }
    // lấy giá service
    @GetMapping("/tour_detail/service_price")
    public double getService(@RequestParam("id") String idtour) {
        return tourDetailService.getServicePrice(idtour);
    }
    // lấy giá vehicles
    @GetMapping("/tour_detail/vehicles")
    public double getVehicles(@RequestParam("id") String idtour) {
        return tourDetailService.getVehiclesPrice(idtour);
    }
    // lấy ra tổng giá trị của tour, bao gồm giá service, hotel, vehicles
    @GetMapping("/tour_detail/total_price")
    public void getTotalPrice(@RequestParam("id") String idtour) {
         tourDetailService.getTotalPrice(idtour);
    }


}
