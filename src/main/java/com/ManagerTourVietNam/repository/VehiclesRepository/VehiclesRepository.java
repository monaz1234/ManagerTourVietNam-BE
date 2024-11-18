package com.ManagerTourVietNam.repository.VehiclesRepository;

import com.ManagerTourVietNam.model.VehiclesModel.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehiclesRepository extends JpaRepository<Vehicles, String> {

}
