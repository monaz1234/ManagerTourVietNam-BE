package com.ManagerTourVietNam.controller.TourDetailController;


import com.ManagerTourVietNam.model.Book.Book;
import com.ManagerTourVietNam.model.BookDetail.BookDetail;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;
import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.model.invoice.invoice;
import com.ManagerTourVietNam.repository.TourDetailRepository.TourDetailRepository;

import com.ManagerTourVietNam.controller.ApplyPromotionRequest;
import com.ManagerTourVietNam.model.Promotion;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;
import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.service.PromotionService;

import com.ManagerTourVietNam.service.TourDetailService.TourDetailService;
import com.ManagerTourVietNam.service.TourService.TourService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ManagerTourVietNam.repository.TourDetailRepository.TourDetailRepository;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

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

    @Autowired
    private PromotionService promotionService;

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





    @PostMapping("/apply-promotion")
    public ResponseEntity<Map<String, Object>> applyPromotion(@RequestBody ApplyPromotionRequest request) {
        Optional<TourDetail> optionalTourDetail = tourDetailService.getTourDetailById(request.getIdtour());
        System.out.println("Request nhận được: idtour=" + request.getIdtour() + ", code=" + request.getCode() + ", quantity=" + request.getQuantity());
        if (optionalTourDetail.isPresent()) {
            TourDetail tourDetail = optionalTourDetail.get();

            double basePrice = tourDetail.getTotal_price();
            System.out.println("Base Price: " + basePrice); // Log giá trị basePrice
            double discount = checkPromotionCode(request.getCode(), basePrice);
            double finalPrice = (basePrice * request.getQuantity()) - discount;

            /* Cập nhật số lượng chỗ trống
            int updatedPlace = tourDetail.getPlace() - request.getQuantity();
            tourDetail.setPlace(updatedPlace);
            tourDetailService.saveTourDetail(tourDetail);*/

            Map<String, Object> response = new HashMap<>();
            response.put("finalPrice", finalPrice);
            response.put("discount", discount);
            System.out.println("Final Price: " + finalPrice);
            System.out.println("Discount: " + discount);
            //response.put("remainingPlace", updatedPlace);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    private double checkPromotionCode(String code, double basePrice) {
        System.out.println("Code nhận được: " + code); // In giá trị `code`
        Optional<Promotion> promotion = promotionService.findByCode(code); // Tìm khuyến mãi theo `code`
        if (promotion.isPresent()) {
            System.out.println("Khuyến mãi tìm thấy: " + promotion.get());
            double discount = promotion.get().getDiscount(); // Lấy giá trị giảm giá
            return basePrice * (discount / 100); // Áp dụng tỷ lệ giảm giá
        } else {
            System.out.println("Mã khuyến mãi không hợp lệ."); // Log lỗi
            return 0;
        }
    }








}
