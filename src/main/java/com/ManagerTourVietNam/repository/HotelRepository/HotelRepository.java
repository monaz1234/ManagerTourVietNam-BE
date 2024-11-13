package com.ManagerTourVietNam.repository.HotelRepository;

import com.ManagerTourVietNam.model.HotelModel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
