package com.ManagerTourVietNam.repository.ServiceRepository;

import com.ManagerTourVietNam.model.ServiceModel.Service;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ServiceRepository extends JpaRepository<Service, String>{

}
