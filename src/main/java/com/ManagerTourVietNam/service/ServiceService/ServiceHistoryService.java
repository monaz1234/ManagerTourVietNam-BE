package com.ManagerTourVietNam.service.ServiceService;

import com.ManagerTourVietNam.model.ServiceModel.ServiceHistory;
import com.ManagerTourVietNam.repository.ServiceRepository.ServiceHistoryRepository;
import com.ManagerTourVietNam.repository.ServiceRepository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.time.LocalDateTime;
@Service
public class ServiceHistoryService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceHistoryRepository serviceHistoryRepository;

    //lấy lịch sử update của dịch vụ
    public void updateServiceWithHistory(String id_service,String newNameService, boolean newStatus, String newDescription, String changedBy) {
        Optional<com.ManagerTourVietNam.model.ServiceModel.Service> optionalService = serviceRepository.findById(id_service);
        if (optionalService.isPresent()) {
            com.ManagerTourVietNam.model.ServiceModel.Service service = optionalService.get();
            ServiceHistory serviceHistory = new ServiceHistory();
            serviceHistory.setId_service(id_service);
            serviceHistory.setOld_name_service(service.getName_service());
            serviceHistory.setNew_name_service(newNameService);
            serviceHistory.setTimestamp(LocalDateTime.now());
            serviceHistory.setOld_status(service.isStatus());
            serviceHistory.setNew_status(newStatus);
            serviceHistory.setOld_description(service.getDescription());
            serviceHistory.setNew_description(newDescription);
            serviceHistory.setChanged_by(changedBy);
            serviceHistory.setNotes("Cập nhật thông tin dịch vụ");

            // Cập nhật dịch vụ
            service.setName_service(newNameService);
            service.setStatus(newStatus);
            service.setDescription(newDescription);
            serviceRepository.save(service);

            serviceHistoryRepository.save(serviceHistory);
        }
    }

}
