package com.ManagerTourVietNam.controller;

import com.ManagerTourVietNam.model.HotelModel.Hotel;
import com.ManagerTourVietNam.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private ResourceLoader resourceLoader;
    String dirIUploadImageHotel = System.getProperty("user.dir") + "/public/image/hotel/";
    //lay thong tin khach san
    @GetMapping("/api/hotels")
    public List<Hotel> getAllHotel(){
        return hotelService.getAllHotel();
    }

    //them khach san

    @PostMapping("/api/hotel")
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    //sua thong tin khach san
    @PutMapping("/api/hotel/{id}")
    public Hotel updateHotel(@PathVariable String id, @RequestBody Hotel hotelDetail){
        return hotelService.updateHotel(id,hotelDetail);
    }

    //Xoa thong tin phuong tien
    @DeleteMapping("/api/hotel/{id}")
    public void deleteHotel(@PathVariable String id){
        hotelService.deleteHotel(id);
    }

    //Tim kiem thong tin phuong tien
    @GetMapping("/api/hotel/{id}")
    public Optional<Hotel> findHotel(@PathVariable String id){
        return hotelService.findHotelById(id);
    }


    //upload anh cua hotel vao folder image/vehicle
    @PostMapping("/api/hotel/image/upload")
    public ResponseEntity<?> uploadImageHotel(@RequestParam("file") MultipartFile file, @RequestParam("hotelName") String hotelName) {
        // Kiểm tra nếu file không rỗng
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Vui lòng chọn một file để upload."));
        }

        try {
            // Lấy tên file từ vehicleName và gán định dạng PNG hoặc JPG
            String fileName = "img_" + hotelName + ".png"; // hoặc JPG nếu cần

            // Lưu file vào thư mục

            File uploadDirFile = new File(dirIUploadImageHotel);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }

            File destinationFile = new File(dirIUploadImageHotel + fileName);
            file.transferTo(destinationFile);

            return ResponseEntity.ok(Collections.singletonMap("message", "Tải lên thành công: " + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Đã xảy ra lỗi khi tải lên hình ảnh: " + e.getMessage()));
        }
    }

    // Lấy hình ảnh
    @GetMapping("/api/hotel/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {

        Resource resource = resourceLoader.getResource("file:" + dirIUploadImageHotel + imageName);

        if (!resource.exists()) {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu hình ảnh không tồn tại
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @DeleteMapping("/api/hotel/images/{imageName}")
    public ResponseEntity<?> deleteImage(@PathVariable String imageName) {
        File file = new File(dirIUploadImageHotel + imageName);

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
