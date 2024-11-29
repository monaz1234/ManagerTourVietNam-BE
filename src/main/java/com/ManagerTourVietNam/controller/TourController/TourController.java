package com.ManagerTourVietNam.controller.TourController;

import com.ManagerTourVietNam.model.ServiceModel.Service;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;
import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.repository.TourRepository.TourRepository;
import com.ManagerTourVietNam.service.TourDetailService.TourDetailService;
import com.ManagerTourVietNam.service.TourService.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetailId;
import java.util.Map;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api")
public class TourController {
    @Autowired
    private TourService tourService;
    @Autowired
    private TourRepository tourRepository;


    // get all tour
    @GetMapping("/tour")
    public List<Tour> getAllTour()
    {
        return tourService.getAllTour();
    }
    // find tour by id
    @PostMapping("/tour/{id}")
    public Optional<Tour> findTourById(@PathVariable String id)
    {
        return tourService.findTourById(id);
    }
    // update tour by id
    @PutMapping("/tour/update_tour/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable String id, @RequestBody Tour tourDetail) {
        try {
            Tour updatedTour = tourService.updateTour(id, tourDetail);
            return ResponseEntity.ok(updatedTour);
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // delete by id
    @DeleteMapping("/tour/delete_tour/{id}")
    public ResponseEntity<Map<String, Object>> deleteTour(@PathVariable String id)
    {
        return  tourService.deleteTour(id);
    }
    // thêm tour
    @PostMapping("/tour/add_tour")
    public Tour addTour(@RequestBody Tour tour)
    {
        return tourService.addTour(tour);
    }

//     tìm kiếm tour theo tên thành phố
    @PostMapping("/tour/find_tour_by_location")
    public List<Tour> getTourByLocation(@RequestParam String location)
    {
        return tourService.getAllTourByLocation(location);
    }
    // cập nhật giá cho cho tour theo id
//    @PutMapping("/tour/update_price")
//    public void updatePrice(@RequestParam("idtour") String idtour, @RequestParam("price") int newPrice)
//    {
//        tourService.updatePrice(idtour,newPrice);
//    }
    //cập nhật số chỗ ngồi cho tour theo id
    @PutMapping("/tour/update_place")
    public void updatePlace(@RequestParam("idtour") String idtour, @RequestParam("place") int newPlace)
    {
        tourService.updatePlace(idtour,newPlace);
    }
    //cập nhật ngày khởi hành cho tour theo id
    @PutMapping("/tour/update_depart")
    public void updateDepart(@RequestParam("idtour") String idtour, @RequestParam("depart") LocalDate newDepart)
    {
        tourService.updateDepart(idtour,newDepart);
    }

    // Lấy danh sách người dùng với phân trang
//    @GetMapping("/api/tour/phantrang")
//    public ResponseEntity<Page<Tour>> getTours(@RequestParam int page, @RequestParam int pageSize) {
//        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
//        Page<Tour> pageTours = tourRepository.findAll(pageable);
//        return ResponseEntity.ok(pageTours);
//    }

    @GetMapping("/tour/phantrang")
    public ResponseEntity<Page<Tour>> getTours(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
        Page<Tour> pageTours = tourRepository.findAll(pageable);
        return ResponseEntity.ok(pageTours);
    }

    @GetMapping("/tourcheck/{id}")
    public Optional<Tour> findTour(@PathVariable String id){
        return tourService.findTourById(id);
    }


    @GetMapping("/tour-ids")
    public List<String> getAllTourIds() {
        return tourRepository.findAll().stream()
                .map(Tour::getIdtour)
                .collect(Collectors.toList());
    }
//

}
