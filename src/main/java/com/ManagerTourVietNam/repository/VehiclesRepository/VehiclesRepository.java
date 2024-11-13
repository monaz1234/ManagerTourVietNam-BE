package com.ManagerTourVietNam.repository.VehiclesRepository;

import com.ManagerTourVietNam.model.HotelModel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehiclesRepository extends JpaRepository<Hotel, String> {

}
