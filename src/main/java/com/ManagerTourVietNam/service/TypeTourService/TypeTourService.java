package com.ManagerTourVietNam.service.TypeTourService;

import com.ManagerTourVietNam.model.TypeTourModel.TypeTour;
import com.ManagerTourVietNam.repository.TypeTourRepository.TypeTourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeTourService {
    @Autowired
    private TypeTourRepository typeTourRepository;
    // get  all type tour
    public List<TypeTour> getAllTypeTour(){
        return typeTourRepository.findAll();
    };
    //update name type tour by id
   public ResponseEntity<TypeTour> updateNameTypeTourByID(String id, String newName)
   {
       Optional<TypeTour> optionalTypeTour = typeTourRepository.findById(id);
       if(optionalTypeTour.isPresent())
       {
           TypeTour typeTour = optionalTypeTour.get();
           typeTour.setName_type(newName);
           TypeTour updatedName = typeTourRepository.save(typeTour);
           return new ResponseEntity<>(updatedName, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

}
