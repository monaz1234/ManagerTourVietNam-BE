package com.ManagerTourVietNam.controller.ServiceController;


import com.ManagerTourVietNam.model.ServiceModel.Service;
import com.ManagerTourVietNam.dto.dtoService.ServiceStatistics;
import com.ManagerTourVietNam.model.ServiceModel.ServiceHistory;
import com.ManagerTourVietNam.service.ServiceService.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    // get all service
    @GetMapping("/services")
    public List<Service> getAllService(){
        return serviceService.getAllService();
    }

    // add service
    @PostMapping("/service")
    public Service addService(@RequestBody Service service){
        return serviceService.addService(service);
    }

    // update service
    @PutMapping("/service/{id}")
    public ResponseEntity<Service> updateService(@PathVariable String id, @RequestBody Service serviceDetail){
        return serviceService.updateService(id,serviceDetail);
    }

    // delete service by id
    @DeleteMapping("/service/{id}")
    public void deleteService(@PathVariable String id){

        serviceService.deleteService(id);
    }

    //find service bt id
    @GetMapping("/service/{id}")
    public Optional<Service> findService(@PathVariable String id){
        return serviceService.findService(id);
    }

    // update status batch
    @PutMapping("/service/status")
    public List<Service> updateServiceStatusBatch(@RequestParam List<String> id_service, @RequestBody Map<String, Boolean> requestBody) {
        boolean newStatus = requestBody.get("newStatus");
        return serviceService.updateServiceStatusBatch(id_service, newStatus);
    }

    // thống kê các dịch vụ đang hoạt động
    @GetMapping("/service/status")
    public ServiceStatistics getServiceStatistics(){
        return serviceService.getServiceStatistics();
    }

    // hiển thị danh sách dịch vụ đang hoạt động
    @GetMapping("service/status/activeService")
    public List<Service> getActiveService(){
        return serviceService.getActiveService(true);
    }

    // hiển thị danh sách dịch vụ ngưng hoạt động
    @GetMapping("service/status/inactiveService")
    public List<Service> getInactiveService(){
        return serviceService.getInactiveService(false);
    }


}
