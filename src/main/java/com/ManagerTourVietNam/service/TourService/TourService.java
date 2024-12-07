package com.ManagerTourVietNam.service.TourService;

import com.ManagerTourVietNam.model.TourDetailModel.TourDetailId;
import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.TourDetailRepository.TourDetailRepository;
import com.ManagerTourVietNam.repository.TourRepository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TourService {
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TourDetailRepository tourDetailRepository;
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
//    public ResponseEntity<Tour> updateTour(String id, Tour tourDetail)
//    {
//        Optional<Tour> optionalTour = tourRepository.findById(id);
//        if(optionalTour.isPresent())
//        {
//            Tour tour = optionalTour.get();
//            tour.setIdtour(tourDetail.getIdtour());
//            tour.setIdtour_type(tourDetail.getIdtour_type());
//            tour.setTourname(tourDetail.getTourname());
//            tour.setLocation(tourDetail.getLocation());
//            tour.setStatus(tourDetail.isStatus());
//            tour.setDescription(tourDetail.getDescription());
//            tour.setImage(tourDetail.getImage());
//
//            Tour updatedTour = tourRepository.save(tour);
//            return new ResponseEntity<>(updatedTour, HttpStatus.OK);
//        }
//        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    public Tour updateTour(String id, Tour tourDetails) throws UserPrincipalNotFoundException {
        return tourRepository.findById(id).map(tour -> {
            tour.setTourname(tourDetails.getTourname());
            tour.setDescription(tourDetails.getDescription());
            tour.setLocation(tourDetails.getLocation());
            tour.setImage(tourDetails.getImage());
            tour.setStatus(tourDetails.isStatus());
            tour.setIs_deleted(tourDetails.isIs_deleted());
//            tour.setIdtour_type(tourDetails.getIdtour_type());
            if (tourDetails.getIdtour_type() != null) {
                tour.setIdtour_type(tourDetails.getIdtour_type());
            } else {
                tour.setIdtour_type(null);
            }

            return tourRepository.save(tour);
        }).orElseThrow(() -> new UserPrincipalNotFoundException("User not found with id " + id));
    }



    // xóa tour theo id
//    public ResponseEntity<Map<String, Object>> deleteTour(String id)
//    {
//        Optional<Tour> optionalTour = tourRepository.findById(id);
//        Optional<TourDetail> optionalTourDetail = tourDetailRepository.findByIdtour(id);
//        if(optionalTour.isPresent() && optionalTourDetail.isPresent())
//        {
//            Tour tour = optionalTour.get();
//            TourDetail tourDetail = optionalTourDetail.get();
//            if(!tour.isIs_deleted() && !tourDetail.isIs_deleted())
//            {
//                //đánh dấu đã xóa mềm
//                tour.setIs_deleted(true);
//                tourDetail.setIs_deleted(true);
//                // lưu
//                Tour updatedTour = tourRepository.save(tour);
//                TourDetail updatedTourDetail = tourDetailRepository.save(tourDetail);
//                // tạo map trả về 2 đối tượng
//                Map<String, Object> response = new HashMap<>();
//                response.put("updatedTour", updatedTour);
//                response.put("updatedTourDetail", updatedTourDetail);
//
//                return new ResponseEntity<>(response,HttpStatus.OK);
//
//            }else {
//                return new ResponseEntity<>(HttpStatus.CONFLICT);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    public void deleteTour(String id) {
        tourRepository.deleteById(id);
    }
    // thêm tour
    public Tour addTour(Tour tour)
    {
        return tourRepository.save(tour);
    }

    // tìm kiếm tour theo tên thành phố
    public List<Tour> getAllTourByLocation(String location)
    {
        return tourRepository.findByLocationContaining(location);
    }

    // cập nhật giá tour theo id tour
//    public void updatePrice(String idtour, int newPrice) {
//        Optional<TourDetail> optionalTourDetail = tourDetailRepository.findByIdtour(idtour);
//        if (optionalTourDetail.isPresent()) {
//            TourDetail tourDetail = optionalTourDetail.get();
//            tourDetail.setTotal_price(newPrice);
//            tourDetailRepository.save(tourDetail);
//        }
//    }
    // cập nhật số chỗ theo id tour
    public void updatePlace(String idtour, int newPlace)
    {
        Optional<TourDetail> optionalTourDetail = tourDetailRepository.findByIdtour(idtour);
        if(optionalTourDetail.isPresent())
        {
            TourDetail tourDetail = optionalTourDetail.get();
            tourDetail.setPlace(newPlace);
            tourDetailRepository.save(tourDetail);
        }
    }
    // cập nhật ngày khởi hành theo id tour
    public void updateDepart(String idtour, LocalDate newDepart)
    {
        Optional<TourDetail> optionalTourDetail = tourDetailRepository.findByIdtour(idtour);
        if(optionalTourDetail.isPresent())
        {
            TourDetail tourDetail = optionalTourDetail.get();
            tourDetail.setDepart(newDepart);
            tourDetailRepository.save(tourDetail);
        }
    }




}
