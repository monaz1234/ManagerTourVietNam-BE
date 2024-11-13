package com.ManagerTourVietNam.repository.ServiceRepository;

import com.ManagerTourVietNam.model.ServiceModel.Service;
import com.ManagerTourVietNam.model.ServiceModel.ServiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface ServiceRepository extends JpaRepository<Service, String>{
    long countByStatus(boolean status);

    List<Service> findByStatus(boolean status);


}
