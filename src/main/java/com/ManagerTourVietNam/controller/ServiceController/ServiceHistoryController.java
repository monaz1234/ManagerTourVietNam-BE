package com.ManagerTourVietNam.controller.ServiceController;

import com.ManagerTourVietNam.dto.dtoService.UpdateServiceRequest;
import com.ManagerTourVietNam.model.ServiceModel.ServiceHistory;
import com.ManagerTourVietNam.service.ServiceService.ServiceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api")
public class ServiceHistoryController {
    @Autowired
    private ServiceHistoryService serviceHistoryService;

    //
    @PutMapping("service/history")
    public void updateServiceWithHistory(@RequestBody UpdateServiceRequest request) {
        serviceHistoryService.updateServiceWithHistory(
                request.getId_service(),
                request.getNewNameService(),
                request.isNewStatus(),
                request.getNewDescription(),
                request.getChangedBy()
        );
    }

}
