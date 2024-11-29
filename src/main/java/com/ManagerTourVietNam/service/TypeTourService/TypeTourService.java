package com.ManagerTourVietNam.service.TypeTourService;

import com.ManagerTourVietNam.model.PaymentModel.PaymentMethod;
import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.model.TypeTourModel.TypeTour;
import com.ManagerTourVietNam.repository.TypeTourRepository.TypeTourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
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


    public Optional<TypeTour> getTypeTour(String id){
        return typeTourRepository.findById(id);
    }

    public void deleteTypeTour(String id){
        typeTourRepository.deleteById(id);
    }

    public ResponseEntity<TypeTour> addTypeTour(TypeTour typetour) {
        if (typetour.getIdtour_type() == null ||
                typetour.getName_type() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            // Lưu phương thức thanh toán vào cơ sở dữ liệu
            TypeTour createdTypeTour = typeTourRepository.save(typetour);
            return new ResponseEntity<>(createdTypeTour, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Xử lý lỗi vi phạm ràng buộc dữ liệu (ví dụ: trùng lặp khóa chính)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            // Xử lý lỗi chung
            e.printStackTrace(); // In lỗi ra console để kiểm tra
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public TypeTour updateTypeTour(String id, TypeTour typeTourDetails) throws UserPrincipalNotFoundException {
        return typeTourRepository.findById(id).map(typetours -> {
            typetours.setName_type(typeTourDetails.getName_type());
            return typeTourRepository.save(typetours); // Lưu thay đổi vào cơ sở dữ liệu
        }).orElseThrow(() -> new UserPrincipalNotFoundException("User not found with id " + id));
    }



}
