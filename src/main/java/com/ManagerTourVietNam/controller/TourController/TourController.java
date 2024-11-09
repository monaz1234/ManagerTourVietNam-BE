package com.ManagerTourVietNam.controller.TourController;

import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.service.TourService.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Tour> deleteTour(@PathVariable String id)
    {
        return  tourService.deleteTour(id);
    }
}
