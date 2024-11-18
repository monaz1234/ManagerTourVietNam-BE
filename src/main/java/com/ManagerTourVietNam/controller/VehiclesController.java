package com.ManagerTourVietNam.controller;

import com.ManagerTourVietNam.model.VehiclesModel.Vehicles;
import com.ManagerTourVietNam.service.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VehiclesController {
    @Autowired
    private VehiclesService vehiclesService;
    @Autowired
    private ResourceLoader resourceLoader;
    String dirIUploadImageVehicle = System.getProperty("user.dir") + "/public/image/vehicle/";
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
    public Vehicles updateVehicles(@PathVariable String id, @RequestBody Vehicles vehiclesDetail){
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



    //upload anh cua vehicle vao folder image/vehicle
    @PostMapping("/api/vehicle/image/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("vehicleName") String vehicleName) {
        // Kiểm tra nếu file không rỗng
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Vui lòng chọn một file để upload."));
        }

        try {
            // Lấy tên file từ vehicleName và gán định dạng PNG hoặc JPG
            String fileName = "img_" + vehicleName + ".png"; // hoặc JPG nếu cần

            // Lưu file vào thư mục

            File uploadDirFile = new File(dirIUploadImageVehicle);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }

            File destinationFile = new File(dirIUploadImageVehicle + fileName);
            file.transferTo(destinationFile);

            return ResponseEntity.ok(Collections.singletonMap("message", "Tải lên thành công: " + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Đã xảy ra lỗi khi tải lên hình ảnh: " + e.getMessage()));
        }
    }

    // Lấy hình ảnh
    @GetMapping("/api/vehicle/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {

        Resource resource = resourceLoader.getResource("file:" + dirIUploadImageVehicle + imageName);

        if (!resource.exists()) {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu hình ảnh không tồn tại
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @DeleteMapping("/api/vehicle/images/{imageName}")
    public ResponseEntity<?> deleteImage(@PathVariable String imageName) {
        File file = new File(dirIUploadImageVehicle + imageName);

        // Kiểm tra xem file có tồn tại không
        if (!file.exists()) {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu hình ảnh không tồn tại
        }

        // Thực hiện xóa file
        if (file.delete()) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Xóa hình ảnh thành công: " + imageName));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Đã xảy ra lỗi khi xóa hình ảnh."));
        }
    }





}
