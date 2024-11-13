package com.ManagerTourVietNam.repository.TourRepository;

import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;
import com.ManagerTourVietNam.model.TourModel.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, String> {
    List<Tour> findByLocationContaining(String location);

}

