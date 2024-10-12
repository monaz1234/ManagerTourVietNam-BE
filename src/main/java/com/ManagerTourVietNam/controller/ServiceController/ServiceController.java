package com.ManagerTourVietNam.controller.ServiceController;


import com.ManagerTourVietNam.model.ServiceModel.ResponseObject;
import com.ManagerTourVietNam.model.ServiceModel.Service;
import com.ManagerTourVietNam.repository.ServiceRepository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/service")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("")
    List<Service> getAllService(){
        return serviceRepository.findAll();
    }
    // Thêm service
    @PostMapping("/{insertService}")
    ResponseEntity<ResponseObject> insertService(@RequestBody Service newService){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Insert product successfully",serviceRepository.save(newService))
        );
    }
}
