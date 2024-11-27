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

    @Query("SELECT s FROM Service s WHERE s.id_service LIKE %:id% OR s.name_service LIKE %:name%")
    List<Service> findById_serviceContainingOrName_serviceContaining(@Param("id") String id, @Param("name") String name);



}
