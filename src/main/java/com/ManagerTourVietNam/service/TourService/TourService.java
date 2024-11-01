package com.ManagerTourVietNam.service.TourService;

import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.repository.TourRepository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {
    @Autowired
    private TourRepository tourRepository;

    // lấy danh sách tour
    public List<Tour> getAllTour()
    {
        return tourRepository.findAll();
    }
    // tìm tour theo id
    public Optional<Tour> findTourById(String id)
    {
        return tourRepository.findById(id);
    }
    // cập nhật tour
    public ResponseEntity<Tour> updateTour(String id, Tour tourDetail)
    {
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if(optionalTour.isPresent())
        {
            Tour tour = optionalTour.get();
            tour.setIdtour(tourDetail.getIdtour());
            tour.setIdtour_type(tourDetail.getIdtour_type());
            tour.setTourname(tourDetail.getTourname());
            tour.setLocation(tourDetail.getLocation());
            tour.setStatus(tourDetail.isStatus());
            tour.setDescription(tourDetail.getDescription());
            tour.setImage(tourDetail.getImage());

            Tour updatedTour = tourRepository.save(tour);
            return new ResponseEntity<>(updatedTour, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // xóa tour theo id
    public ResponseEntity<Tour> deleteTour(String id)
    {
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if(optionalTour.isPresent())
        {
            Tour tour = optionalTour.get();
            tour.setIs_deleted(!tour.isIs_deleted());

            Tour updatedTour = tourRepository.save(tour);
            return new ResponseEntity<>(updatedTour,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
