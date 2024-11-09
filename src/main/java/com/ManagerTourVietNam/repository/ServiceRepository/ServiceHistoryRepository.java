package com.ManagerTourVietNam.repository.ServiceRepository;

import com.ManagerTourVietNam.model.ServiceModel.ServiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceHistoryRepository extends JpaRepository<ServiceHistory, String> {
}
