package com.ManagerTourVietNam.service;


import com.ManagerTourVietNam.model.Vehicles;
import com.ManagerTourVietNam.repository.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiclesService {
    @Autowired
    private VehiclesRepository vehiclesRepository;

    //danh sach phuong tien
    public List<Vehicles> getALLVehicles(){
        return vehiclesRepository.findAll();
    }

    // thêm phuong tien
    public Vehicles addVehicles(Vehicles vehicles){
        return vehiclesRepository.save(vehicles);
    }

    //sua thong tin phuong tien
    public Vehicles updateVehicles(String id,Vehicles vehiclesDetails){
        Optional<Vehicles> optionalVehicles = vehiclesRepository.findById(id);
        if(optionalVehicles.isPresent()){
            Vehicles vehicles =optionalVehicles.get();
            vehicles.setName_vehicles(vehiclesDetails.getName_vehicles());
            vehicles.setPlace_vehicles(vehiclesDetails.getPlace_vehicles());
            vehicles.setDriver(vehiclesDetails.getDriver());
            vehicles.setImage(vehiclesDetails.getImage());
            vehicles.setDescription(vehiclesDetails.getDescription());
            vehicles.setStatus(vehiclesDetails.getStatus());
            return vehiclesRepository.save(vehicles);
        }
        return null;
    }

    //xoa phuong tien
    public void deleteVehicles(String id)
    {
        vehiclesRepository.deleteById(id);
    }

    //tim kiem phuong tien
    public Optional<Vehicles> findVehiclesById(String id){

        return vehiclesRepository.findById(id);
    }

}
