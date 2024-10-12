package com.ManagerTourVietNam.controller;

import com.ManagerTourVietNam.model.Vehicles;
import com.ManagerTourVietNam.service.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VehiclesController {
    @Autowired
    private VehiclesService vehiclesService;

    //lay thong tin phuong tien
    @GetMapping("/api/vehicles")
    public List<Vehicles> getAllVehicles(){
        return vehiclesService.getALLVehicles();
    }

    //them phuong tien moi
    @PostMapping("/api/vehicle")
    public Vehicles addVehicle(@RequestBody Vehicles vehicles){
        return vehiclesService.addVehicles(vehicles);
    }

    //sua thong tin phuong tien
    @PutMapping("/api/vehicles/{id}")
    public Vehicles updateVehicles(@PathVariable String id,@RequestBody Vehicles vehiclesDetail){
        return vehiclesService.updateVehicles(id,vehiclesDetail);
    }

    //Xoa thong tin phuong tien
    @DeleteMapping("/api/vehicles/{id}")
    public void deleteVehicles(@PathVariable String id){
        vehiclesService.deleteVehicles(id);
    }

    //Tim kiem thong tin phuong tien
    @GetMapping("/api/vehicles/{id}")
    public Optional<Vehicles> findVehicles(@PathVariable String id)
    {
        return vehiclesService.findVehiclesById(id);
    }

}
