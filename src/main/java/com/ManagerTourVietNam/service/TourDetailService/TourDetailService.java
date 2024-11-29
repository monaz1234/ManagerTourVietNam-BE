package com.ManagerTourVietNam.service.TourDetailService;

import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetailId;
import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.model.invoice.invoice;
import com.ManagerTourVietNam.repository.HotelRepository.HotelRepository;
import com.ManagerTourVietNam.repository.TourDetailRepository.TourDetailRepository;
import com.ManagerTourVietNam.repository.VehiclesRepository.*;
import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.repository.TourDetailRepository.TourDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public TourDetail addTourDetail(TourDetail tourDetail) {
        return tourDetailRepository.save(tourDetail);
    }

    public TourDetail updateTourDetail(String idtour, String id_vehicles, String id_hotel, String id_service, TourDetail tourSupDetails)
            throws UserPrincipalNotFoundException {

        // Tạo đối tượng TourDetailId từ các thông tin
        TourDetailId tourDetailId = new TourDetailId(idtour, id_vehicles, id_hotel, id_service);

        return tourDetailRepository.findById(tourDetailId).map(tourDetailSub -> {
            // Cập nhật các trường
            if (tourSupDetails.getId_service() != null) {
                tourDetailSub.setId_service(tourSupDetails.getId_service());
            }
            if (tourSupDetails.getIdtour() != null) {
                tourDetailSub.setIdtour(tourSupDetails.getIdtour());
            }
            if (tourSupDetails.getId_hotel() != null) {
                tourDetailSub.setId_hotel(tourSupDetails.getId_hotel());
            }
            if (tourSupDetails.getId_vehicles() != null) {
                tourDetailSub.setId_vehicles(tourSupDetails.getId_vehicles());
            }
            if (tourSupDetails.getDepart() != null) {
                tourDetailSub.setDepart(tourSupDetails.getDepart());
            }
            if (tourSupDetails.getTotal_price() > 0) {
                tourDetailSub.setTotal_price(tourSupDetails.getTotal_price());
            }
            if (tourSupDetails.getPlace() > 0) {
                tourDetailSub.setPlace(tourSupDetails.getPlace());
            }
            tourDetailSub.setIs_deleted(tourSupDetails.isIs_deleted());

            // Lưu và trả về bản ghi đã được cập nhật
            return tourDetailRepository.save(tourDetailSub);
        }).orElseThrow(() -> new UserPrincipalNotFoundException("Tour detail not found with id " + tourDetailId));
    }


    public void deleteTourDetail(String idtour, String id_vehicles, String id_hotel, String id_service) {
        TourDetailId tourDetailId = new TourDetailId(idtour, id_vehicles, id_hotel, id_service);
        tourDetailRepository.deleteById(tourDetailId);
    }











}
