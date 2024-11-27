package com.ManagerTourVietNam.controller.ServiceController;


import com.ManagerTourVietNam.model.ServiceModel.Service;
import com.ManagerTourVietNam.dto.dtoService.ServiceStatistics;
import com.ManagerTourVietNam.model.ServiceModel.ServiceHistory;
import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.ServiceRepository.ServiceRepository;
import com.ManagerTourVietNam.service.ServiceService.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping(path = "/api")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/api/services")
    public List<Service> getAllService() {
        return serviceService.getAllService(); // Fetch all services
    }

    // add service
    @PostMapping("/api/service")
    public Service addService(@RequestBody Service service){
        return serviceService.addService(service);
    }

    // update service
    @PutMapping("/api/service/{id}")
    public ResponseEntity<Service> updateService(@PathVariable String id, @RequestBody Service serviceDetail){
        return serviceService.updateService(id,serviceDetail);
    }

    // delete service by id
    @DeleteMapping("/api/service/{id}")
    public void deleteService(@PathVariable String id){

        serviceService.deleteService(id);
    }

    //find service bt id
    @GetMapping("/api/service/{id}")
    public Optional<Service> findService(@PathVariable String id){
        return serviceService.findService(id);
    }

    // update status batch
    @PutMapping("/api/service/status")
    public List<Service> updateServiceStatusBatch(@RequestParam List<String> id_service, @RequestBody Map<String, Boolean> requestBody) {
        boolean newStatus = requestBody.get("newStatus");
        return serviceService.updateServiceStatusBatch(id_service, newStatus);
    }

    // thống kê các dịch vụ đang hoạt động
    @GetMapping("/api/service/status")
    public ServiceStatistics getServiceStatistics(){
        return serviceService.getServiceStatistics();
    }

    // hiển thị danh sách dịch vụ đang hoạt động
    @GetMapping("/api/service/status/activeService")
    public List<Service> getActiveService(){
        return serviceService.getActiveService(true);
    }

    // hiển thị danh sách dịch vụ ngưng hoạt động
    @GetMapping("/api/service/status/inactiveService")
    public List<Service> getInactiveService(){
        return serviceService.getInactiveService(false);
    }




    // Lấy danh sách người dùng với phân trang
    @GetMapping("/api/service/phantrang")
    public ResponseEntity<Page<Service>> getServices(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
        Page<Service> pageServices = serviceRepository.findAll(pageable);
        return ResponseEntity.ok(pageServices);
    }

    @GetMapping("/api/service-ids")
    public List<String> getAllServiceIds() {
        return serviceRepository.findAll().stream()
                .map(Service::getId_service)
                .collect(Collectors.toList());
    }

    @GetMapping("api/services/search")
    public ResponseEntity<List<Service>> searchServices(@RequestParam String query) {
        List<Service> services = serviceRepository.findById_serviceContainingOrName_serviceContaining(query, query);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }


}
