package com.ManagerTourVietNam.repository.TourDetailRepository;

import com.ManagerTourVietNam.model.Promotion;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface TourDetailRepository extends JpaRepository<TourDetail, TourDetailId> {

    @Query("SELECT t FROM TourDetail t WHERE t.idtour = :idtour")
    Optional<TourDetail> findByIdtour(@Param("idtour") String idtour);

    //price---------------------------------------------------------------------------------
    @Query("SELECT s.price FROM TourDetail td JOIN td.service s WHERE td.idtour = :idtour")
    int findServicePriceByTourId(@Param("idtour") String idtour);

    @Query("SELECT v.price FROM TourDetail td JOIN td.vehicles v WHERE td.idtour = :idtour")
    int findVehiclesPriceByTourId(@Param("idtour") String idtour);

    @Query("SELECT h.price FROM TourDetail td JOIN td.hotel h WHERE td.idtour = :idtour")
    int findHotelPriceByTourId(@Param("idtour") String idtour);

    @Query("SELECT p FROM Promotion p WHERE p.promotion_code = :promotion_code AND p.status = true")
    Optional<Promotion> findByPromotionCode(@Param("promotion_code") String promotion_code);

}