package com.ManagerTourVietNam.repository.TourRepository;

import com.ManagerTourVietNam.model.TourModel.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, String> {

}
