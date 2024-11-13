package com.ManagerTourVietNam.controller.TourController;

import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;
import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.service.TourDetailService.TourDetailService;
import com.ManagerTourVietNam.service.TourService.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetailId;
import java.util.Map;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api")
public class TourController {
    @Autowired
    private TourService tourService;


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
    public ResponseEntity<Tour> updateTour(@PathVariable String id, @RequestBody Tour tourDetail)
    {
        return tourService.updateTour(id,tourDetail);
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
//

}
