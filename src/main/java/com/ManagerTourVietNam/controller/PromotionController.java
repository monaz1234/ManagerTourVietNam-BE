package com.ManagerTourVietNam.controller;


import com.ManagerTourVietNam.model.Promotion;
import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;
    // lấy danh sách km
    @GetMapping("/api/promotions")
    public List<Promotion> GetAllPromotion(){
        return promotionService.getAllPromotion();
    }
    // Thêm khuyến mãi mới
    @PostMapping("/api/promotions")
    public ResponseEntity<Promotion> addPromotion(@RequestBody Promotion promotion) {
        Promotion newPromotion = promotionService.addPromotion(promotion);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPromotion);
    }

    //Sửa khuyến mãi
    @PutMapping("/api/promotions/{promotion_code}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable String promotion_code, @RequestBody Promotion promotionDetail) {
        Promotion updatedPromotion = promotionService.updatePromotion(promotion_code, promotionDetail);
        if (updatedPromotion != null) {
            return ResponseEntity.ok(updatedPromotion);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Xóa thông tin km
    @DeleteMapping("/api/promotions/{promotion_code}")
    public void deletePromotion(@PathVariable String promotion_code){
        promotionService.deletePromotion(promotion_code);
    }

    // Tìm kiếm thông tin km
    @GetMapping("/api/promotions/{promotion_code}")
    public Optional<Promotion> findUserById(@PathVariable String promotion_code){
        return promotionService.findUserById(promotion_code);
    }
    // Tìm kiếm khuyến mãi theo query
    @GetMapping("/api/promotions/search")
    public List<Promotion> searchPromotions(@RequestParam String query) {
        return promotionService.searchPromotions(query);
    }


}
