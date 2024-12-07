package com.ManagerTourVietNam.controller.TourDetailController;


import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;
import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.repository.TourDetailRepository.TourDetailRepository;

import com.ManagerTourVietNam.controller.ApplyPromotionRequest;
import com.ManagerTourVietNam.model.Promotion;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;
import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.service.BookService.SequenceGeneratorService;

import com.ManagerTourVietNam.service.PromotionService;

import com.ManagerTourVietNam.service.TourDetailService.TourDetailService;
import com.ManagerTourVietNam.service.TourService.TourService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    @PostMapping("tour_detail/create")
    public ResponseEntity<TourDetail> createTourdetail(@RequestBody TourDetail tourDetail) {
        // Tạo mã ID tự động

        //String generatedId = sequenceGeneratorService.generateBookId();
        if (tourDetail.getIdtourdetail() == null || tourDetail.getIdtourdetail().isEmpty()) {
            String generatedId = sequenceGeneratorService.generateTourDetailId();
            tourDetail.setIdtourdetail(generatedId);
        }
        // Gán mã ID cho Book
        //book.setIdbook(generatedId);

        // Lưu vào cơ sở dữ liệu
        TourDetail savedTourDetail = tourDetailRepository.save(tourDetail);

        return ResponseEntity.ok(savedTourDetail);
    }


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

        // Kiểm tra mã khuyến mãi trong database
        Optional<Promotion> promotion = promotionService.findByCode(request.getCode());
        System.out.println("Request nhận được: idtour=" + request.getIdtour() + ", code=" + request.getCode() + ", quantity=" + request.getQuantity());

        if (optionalTourDetail.isPresent()) {
            TourDetail tourDetail = optionalTourDetail.get();

            // Kiểm tra nếu số lượng chỗ trống là 0
            if (tourDetail.getPlace() <= 0) {
                Map<String, Object> response = new HashMap<>();
                response.put("error", "Tour đã hết chỗ. Không thể đặt thêm.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            String promotionCode = promotion.get().getPromotion_code();
            System.out.println("promotion_code: " + promotionCode); // Log giá trị promotion_code

            double basePrice = tourDetail.getTotal_price();
            System.out.println("Base Price: " + basePrice); // Log giá trị basePrice

            double discount = checkPromotionCode(request.getCode(), basePrice);
            double finalPrice = (basePrice * request.getQuantity()) - discount;

            // Cập nhật số lượng chỗ trống
            int updatedPlace = tourDetail.getPlace() - request.getQuantity();
            tourDetail.setPlace(updatedPlace);
            tourDetailService.saveTourDetail(tourDetail);

            Map<String, Object> response = new HashMap<>();
            response.put("finalPrice", finalPrice);
            response.put("discount", discount);
            response.put("promotion_code", promotionCode);

            System.out.println("Final Price: " + finalPrice);
            System.out.println("Discount: " + discount);
            System.out.println("promotion_code: " + promotionCode);
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


    @PutMapping("/tour_detail/update/{id}")
    public ResponseEntity<?> updateTourDetail(@PathVariable String id, @RequestBody TourDetail updatedTourDetail) {
        // Tìm kiếm TourDetail hiện tại theo ID
        Optional<TourDetail> existingTourDetailOptional = tourDetailRepository.findByIdtour(id);

        if (existingTourDetailOptional.isPresent()) {
            TourDetail existingTourDetail = existingTourDetailOptional.get();

            // Cập nhật các trường từ `updatedTourDetail`
            existingTourDetail.setTour(updatedTourDetail.getTour());
            existingTourDetail.setPlace(updatedTourDetail.getPlace());
            existingTourDetail.setHotel(updatedTourDetail.getHotel());
            existingTourDetail.setVehicles(updatedTourDetail.getVehicles());
            existingTourDetail.setService(updatedTourDetail.getService());
            existingTourDetail.setTotal_price(updatedTourDetail.getTotal_price());

            // Lưu thông tin cập nhật vào cơ sở dữ liệu
            TourDetail savedTourDetail = tourDetailRepository.save(existingTourDetail);

            return ResponseEntity.ok(savedTourDetail);
        } else {
            // Nếu không tìm thấy, trả về lỗi 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TourDetail không tồn tại với ID: " + id);
        }
    }

    @GetMapping("/Find/tour_detail/{idtour}")
    public ResponseEntity<?> getTourDetailsByIdtour(@PathVariable String idtour) {
        List<TourDetail> tourDetails = tourDetailService.getSingleTourDetailByIdtour(idtour); // Lấy danh sách
        if (tourDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tour details found for idtour: " + idtour);
        }
        return ResponseEntity.ok(tourDetails);
    }









}
