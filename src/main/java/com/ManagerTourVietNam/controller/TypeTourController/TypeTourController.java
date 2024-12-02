package com.ManagerTourVietNam.controller.TypeTourController;

import com.ManagerTourVietNam.model.ServiceModel.Service;
import com.ManagerTourVietNam.model.TypeTourModel.TypeTour;
import com.ManagerTourVietNam.repository.TypeTourRepository.TypeTourRepository;
import com.ManagerTourVietNam.service.TypeTourService.TypeTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api")
public class TypeTourController {
    @Autowired
    private TypeTourService typeTourService;

    @Autowired
    private TypeTourRepository typeTourRepository;
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

    @PutMapping("/type_tour/{id}")
    public ResponseEntity<TypeTour> updatetypeTour(@PathVariable String id, @RequestBody TypeTour typeTourDetail) {
        try {
            TypeTour updatedService = typeTourService.updateTypeTour(id, typeTourDetail);
            return ResponseEntity.ok(updatedService); // Trả về đối tượng đã cập nhật
        } catch (UserPrincipalNotFoundException e) {
            // Log lỗi chi tiết để dễ dàng kiểm tra
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/type_tour/{id}")
    public void deleteTypeTour(@PathVariable String id){

        typeTourService.deleteTypeTour(id);
    }


}
