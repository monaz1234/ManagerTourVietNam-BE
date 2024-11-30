package com.ManagerTourVietNam.service.TourDetailService;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;
import com.ManagerTourVietNam.repository.HotelRepository.HotelRepository;
import com.ManagerTourVietNam.repository.TourDetailRepository.TourDetailRepository;
import com.ManagerTourVietNam.repository.VehiclesRepository.*;
import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.repository.TourDetailRepository.TourDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class TourDetailService {
    @Autowired
    private TourDetailRepository tourDetailRepository;
    // lấy danh sách tour
    public List<TourDetail> getAllTourDetail() {
        return tourDetailRepository.findAll();
    }
    public List<TourDetail> getAllTourDetailWithTotalPrice() {
        List<TourDetail> tourDetails = tourDetailRepository.findAll();
        for (TourDetail tourDetail : tourDetails) {
            double servicePrice = getServicePrice(tourDetail.getIdtour());
            double vehiclesPrice = getVehiclesPrice(tourDetail.getIdtour());
            double hotelPrice = getHotelPrice(tourDetail.getIdtour());
            double total_price = (servicePrice + vehiclesPrice + hotelPrice) * 1.16; // Thêm 16%
            tourDetail.setTotal_price(total_price);
            tourDetailRepository.save(tourDetail); // Lưu giá trị cập nhật vào DB
        }
        return tourDetails;
    }
    public double getServicePrice(String idtour) {
        return tourDetailRepository.findServicePriceByTourId(idtour);
    }
    public double getVehiclesPrice(String idtour) {
        return tourDetailRepository.findVehiclesPriceByTourId(idtour);
    }
    public double getHotelPrice(String idtour) {
        return tourDetailRepository.findHotelPriceByTourId(idtour);
    }
    // lấy tổng giá của service, vehicles, hotel x 16%, sau đó thêm vào cột
    // total_price trong bảng tour_detail
    public void getTotalPrice(String idtour) {
        Optional<TourDetail> optionalTourDetail = tourDetailRepository.findByIdtour(idtour);
        if (optionalTourDetail.isPresent()) {
            TourDetail tourDetail = optionalTourDetail.get();
            double servicePrice = getServicePrice(idtour);
            double vehiclesPrice = getVehiclesPrice(idtour);
            double hotelPrice = getHotelPrice(idtour);

            double total_price = 0;
            total_price = (servicePrice + vehiclesPrice + hotelPrice) * 1.16;

            tourDetail.setTotal_price(total_price);
            tourDetailRepository.save(tourDetail);
        }
    }

    // Lấy thông tin tour detail theo ID
    public Optional<TourDetail> getTourDetailById(String idtour) {
        return tourDetailRepository.findByIdtour(idtour);
    }

    // Lưu thông tin tour detail
    public void saveTourDetail(TourDetail tourDetail) {
        tourDetailRepository.save(tourDetail);
    }
}
