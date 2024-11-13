package com.ManagerTourVietNam.service.TourDetailService;

import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;
import com.ManagerTourVietNam.repository.HotelRepository.HotelRepository;
import com.ManagerTourVietNam.repository.ServiceRepository.ServiceRepository;
import com.ManagerTourVietNam.repository.TourDetailRepository.TourDetailRepository;
import com.ManagerTourVietNam.repository.VehiclesRepository.VehiclesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TourDetailService {
    @Autowired
    private TourDetailRepository tourDetailRepository;

    public double getServicePrice(String idtour) {
        return tourDetailRepository.findServicePriceByTourId(idtour);
    }

    public double getVehiclesPrice(String idtour) {
        return tourDetailRepository.findVehiclesPriceByTourId(idtour);
    }

    public double getHotelPrice(String idtour) {
        return tourDetailRepository.findHotelPriceByTourId(idtour);
    }

    // lấy tổng giá của service, vehicles, hotel x 16%, sau đó thêm vào cột total_price trong bảng tour_detail
    public void getTotalPrice(String idtour) {
        Optional<TourDetail> optionalTourDetail = tourDetailRepository.findByIdtour(idtour);

        if(optionalTourDetail.isPresent())
        {
            TourDetail tourDetail = optionalTourDetail.get();
            double servicePrice = getServicePrice(idtour);
            double vehiclesPrice = getVehiclesPrice(idtour);
            double hotelPrice = getHotelPrice(idtour);

            double total_price = 0;
            total_price =( servicePrice + vehiclesPrice + hotelPrice) * 0.16;

            tourDetail.setTotal_price(total_price);
            tourDetailRepository.save(tourDetail);

        }
    }



}
