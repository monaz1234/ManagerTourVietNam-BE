package com.ManagerTourVietNam.controller.TypeTourController;

import com.ManagerTourVietNam.model.TypeTourModel.TypeTour;
import com.ManagerTourVietNam.service.TypeTourService.TypeTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api")
public class TypeTourController {
    @Autowired
    private TypeTourService typeTourService;
    //get all type tour
    @GetMapping("/type_tour")
    public List<TypeTour> getAllTypeTour()
    {
        return typeTourService.getAllTypeTour();
    }
    @PostMapping("/type_tour/update_name")
    public ResponseEntity updateNameTypeTourByID(@RequestParam("id") String id, @RequestParam("name") String newName)
    {
       return typeTourService.updateNameTypeTourByID(id,newName);
    }
}
