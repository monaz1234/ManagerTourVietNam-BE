package com.ManagerTourVietNam.service.ServiceService;

import com.ManagerTourVietNam.dto.dtoService.ServiceStatistics;
import com.ManagerTourVietNam.repository.ServiceRepository.ServiceHistoryRepository;
import com.ManagerTourVietNam.repository.ServiceRepository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {
        @Autowired
        private ServiceRepository serviceRepository;

        @Autowired
        private ServiceHistoryRepository serviceHistoryRepository;

        // lấy danh sách các service
        public List<com.ManagerTourVietNam.model.ServiceModel.Service> getAllService(){
            return serviceRepository.findAll();
        }

        // thêm service
        public com.ManagerTourVietNam.model.ServiceModel.Service addService(com.ManagerTourVietNam.model.ServiceModel.Service service){
            return serviceRepository.save(service);
        }
        // by ID
//        public ResponseEntity<com.ManagerTourVietNam.model.ServiceModel.Service> updateService(String id, com.ManagerTourVietNam.model.ServiceModel.Service serviceDetail){
//            Optional<com.ManagerTourVietNam.model.ServiceModel.Service> optionalService = serviceRepository.findById(id);
//
//            if(optionalService.isPresent()){
//                com.ManagerTourVietNam.model.ServiceModel.Service service = optionalService.get();
//                service.setId_service(serviceDetail.getId_service());
//                service.setName_service(serviceDetail.getName_service());
//                service.setDescription(serviceDetail.getDescription());
//                service.setTime_start(serviceDetail.getTime_start());
//                service.setTime_end(serviceDetail.getTime_end());
//                service.setPlant(serviceDetail.getPlant());
//                service.setStatus(serviceDetail.isStatus());
//
//                com.ManagerTourVietNam.model.ServiceModel.Service updatedService = serviceRepository.save(service);
//                return new ResponseEntity<>(updatedService, HttpStatus.OK);
//            }else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        }

    public com.ManagerTourVietNam.model.ServiceModel.Service updateService(String id, com.ManagerTourVietNam.model.ServiceModel.Service serviceDetails) throws UserPrincipalNotFoundException {
        return serviceRepository.findById(id).map(service -> {
            service.setName_service(serviceDetails.getName_service());
            service.setTime_start(serviceDetails.getTime_start());
            service.setTime_end(serviceDetails.getTime_end());
            service.setStatus(serviceDetails.isStatus());
            service.setPrice(serviceDetails.getPrice());
            service.setPlant(serviceDetails.getPlant());
            service.setDescription(serviceDetails.getDescription());
            return serviceRepository.save(service); // Lưu thay đổi vào cơ sở dữ liệu
        }).orElseThrow(() -> new UserPrincipalNotFoundException("User not found with id " + id));
    }



        // xóa theo id
        public void deleteService(String id){
             serviceRepository.deleteById(id);
        }
        // tìm kiếm theo id
        public Optional<com.ManagerTourVietNam.model.ServiceModel.Service> findService(String id){
            return serviceRepository.findById(id);
        }
        // cập nhật trạng thái dịch vụ hàng loạt
        public List<com.ManagerTourVietNam.model.ServiceModel.Service> updateServiceStatusBatch(List<String> id_service, boolean newStatus){
            List<com.ManagerTourVietNam.model.ServiceModel.Service> services = serviceRepository.findAllById(id_service);
            services.forEach(
                    service -> {
                        service.setStatus(newStatus);
                    }
            );
            List<com.ManagerTourVietNam.model.ServiceModel.Service> updatedServices = serviceRepository.saveAll(services);
            // In ra danh sách dịch vụ sau khi cập nhật
            System.out.println("After Update Services: " + updatedServices);
            return updatedServices;
        }
        // thống kê dịch vụ đang hoạt động và không hoạt động
        public ServiceStatistics getServiceStatistics(){
            long totalServices = serviceRepository.count(); // tổng số service
            long activeServices = serviceRepository.countByStatus(true); // dịch vụ đang hoạt động
            long inactiveServices = serviceRepository.countByStatus(false); // dịch vụ ngừng hoạt động
            return new ServiceStatistics(activeServices,inactiveServices, totalServices);
        }
        // hiển thị danh sách dịch vụ đang hoạt động
        public List<com.ManagerTourVietNam.model.ServiceModel.Service> getActiveService(boolean status){
            return serviceRepository.findByStatus(status);
        }
        // hiển thị danh sách dịch vụ ngưng hoạt động
        public List<com.ManagerTourVietNam.model.ServiceModel.Service> getInactiveService(boolean status){
            return serviceRepository.findByStatus(status);
        }



}
