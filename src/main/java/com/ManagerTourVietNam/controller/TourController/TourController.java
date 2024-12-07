package com.ManagerTourVietNam.controller.TourController;

import com.ManagerTourVietNam.model.ServiceModel.Service;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetail;
import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.repository.TourRepository.TourRepository;
import com.ManagerTourVietNam.service.TourDetailService.TourDetailService;
import com.ManagerTourVietNam.service.TourService.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ManagerTourVietNam.model.TourDetailModel.TourDetailId;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api")
public class TourController {
    @Autowired
    private TourService tourService;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private ResourceLoader resourceLoader;
    String dirIUploadImageTour = System.getProperty("user.dir") + "/public/image/tour/";


    // get all tour
    @GetMapping("/tour")
    public List<Tour> getAllTour()
    {
        return tourService.getAllTour();
    }
    // find tour by id
    @PostMapping("/tour/{id}")
    public Optional<Tour> findTourById(@PathVariable String id)
    {
        return tourService.findTourById(id);
    }
    // update tour by id
    @PutMapping("/tour/update_tour/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable String id, @RequestBody Tour tourDetail) {
        try {
            Tour updatedTour = tourService.updateTour(id, tourDetail);
            return ResponseEntity.ok(updatedTour);
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // delete by id
//    @DeleteMapping("/tour/delete_tour/{id}")
//    public ResponseEntity<Map<String, Object>> deleteTour(@PathVariable String id)
//    {
//        return  tourService.deleteTour(id);
//    }

    @DeleteMapping("/tour/delete_tour/{id}")
    public void deleteTour(@PathVariable String id){
        tourService.deleteTour(id);
    }

    // thêm tour
    @PostMapping("/tour/add_tour")
    public Tour addTour(@RequestBody Tour tour)
    {
        return tourService.addTour(tour);
    }

//     tìm kiếm tour theo tên thành phố
    @PostMapping("/tour/find_tour_by_location")
    public List<Tour> getTourByLocation(@RequestParam String location)
    {
        return tourService.getAllTourByLocation(location);
    }
    // cập nhật giá cho cho tour theo id
//    @PutMapping("/tour/update_price")
//    public void updatePrice(@RequestParam("idtour") String idtour, @RequestParam("price") int newPrice)
//    {
//        tourService.updatePrice(idtour,newPrice);
//    }
    //cập nhật số chỗ ngồi cho tour theo id
    @PutMapping("/tour/update_place")
    public void updatePlace(@RequestParam("idtour") String idtour, @RequestParam("place") int newPlace)
    {
        tourService.updatePlace(idtour,newPlace);
    }
    //cập nhật ngày khởi hành cho tour theo id
    @PutMapping("/tour/update_depart")
    public void updateDepart(@RequestParam("idtour") String idtour, @RequestParam("depart") LocalDate newDepart)
    {
        tourService.updateDepart(idtour,newDepart);
    }

    // Lấy danh sách người dùng với phân trang
//    @GetMapping("/api/tour/phantrang")
//    public ResponseEntity<Page<Tour>> getTours(@RequestParam int page, @RequestParam int pageSize) {
//        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
//        Page<Tour> pageTours = tourRepository.findAll(pageable);
//        return ResponseEntity.ok(pageTours);
//    }

    @GetMapping("/tour/phantrang")
    public ResponseEntity<Page<Tour>> getTours(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
        Page<Tour> pageTours = tourRepository.findAll(pageable);
        return ResponseEntity.ok(pageTours);
    }

    @GetMapping("/tourcheck/{id}")
    public Optional<Tour> findTour(@PathVariable String id){
        return tourService.findTourById(id);
    }


    @GetMapping("/tour-ids")
    public List<String> getAllTourIds() {
        return tourRepository.findAll().stream()
                .map(Tour::getIdtour)
                .collect(Collectors.toList());
    }
//


    //upload anh cua hotel vao folder image/vehicle
    @PostMapping("/tour/image/upload")
    public ResponseEntity<?> uploadImageTour(@RequestParam("file") MultipartFile file, @RequestParam("tourName") String tourName) {
        // Kiểm tra nếu file không rỗng
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Vui lòng chọn một file để upload."));
        }

        try {
            // Lấy tên file từ vehicleName và gán định dạng PNG hoặc JPG
            String fileName = "img_" + tourName + ".png"; // hoặc JPG nếu cần

            // Lưu file vào thư mục

            File uploadDirFile = new File(dirIUploadImageTour);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }

            File destinationFile = new File(dirIUploadImageTour + fileName);
            file.transferTo(destinationFile);

            return ResponseEntity.ok(Collections.singletonMap("message", "Tải lên thành công: " + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Đã xảy ra lỗi khi tải lên hình ảnh: " + e.getMessage()));
        }
    }

    // Lấy hình ảnh
    @GetMapping("/tour/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {

        Resource resource = resourceLoader.getResource("file:" + dirIUploadImageTour + imageName);

        if (!resource.exists()) {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu hình ảnh không tồn tại
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @DeleteMapping("/tour/images/{imageName}")
    public ResponseEntity<?> deleteImage(@PathVariable String imageName) {
        File file = new File(dirIUploadImageTour + imageName);

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
