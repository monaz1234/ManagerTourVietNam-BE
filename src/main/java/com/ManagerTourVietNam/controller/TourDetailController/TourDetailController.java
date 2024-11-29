package com.ManagerTourVietNam.controller.TourDetailController;

import com.ManagerTourVietNam.model.Book.Book;
import com.ManagerTourVietNam.model.BookDetail.BookDetail;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;
import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.model.invoice.invoice;
import com.ManagerTourVietNam.repository.TourDetailRepository.TourDetailRepository;
import com.ManagerTourVietNam.service.TourDetailService.TourDetailService;
import com.ManagerTourVietNam.service.TourService.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api")
public class TourDetailController {
    @Autowired
    private TourDetailService tourDetailService;

    @Autowired
    private TourDetailRepository tourDetailRepository;

    @Autowired
    private TourService tourService;
    // get all tour
    @GetMapping("/tour_detail")
    public List<TourDetail> getAllTourDetail()
    {
        return tourDetailService.getAllTourDetailWithTotalPrice();
    }
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

//    @GetMapping("/tourdetailcheck/{id}")
//    public Optional<TourDetail> findTourDetailById(@PathVariable String id){
//        return tourDetailService.findTourDetailByIdTour(id);
//    }

    @GetMapping("/tourdetailbyidtour/{idtour}")
    public Optional<TourDetail> getTourDetailsByTour(@PathVariable("idtour") String idtour) {
        Optional<Tour> tourOptional = tourService.findTourById(idtour);
            return tourDetailService.findTourDetailByIdTour(tourOptional.get());

    }





}
