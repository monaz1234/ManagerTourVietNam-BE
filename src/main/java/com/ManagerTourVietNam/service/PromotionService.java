package com.ManagerTourVietNam.service;


import com.ManagerTourVietNam.model.Promotion;
import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;
    // danh sách người dùng
    public List<Promotion> getAllPromotion(){
        return promotionRepository.findAll();
    }
    public Promotion addPromotion(Promotion promotion){
        return promotionRepository.save(promotion);
    }

    // Sửa thông tin km
    public Promotion updatePromotion(String promotion_code, Promotion promotionDetails){
        Optional<Promotion> optionalPromotion = promotionRepository.findById(promotion_code);
        if (optionalPromotion.isPresent()){
            Promotion promotion = optionalPromotion.get();
            promotion.setCode(promotionDetails.getCode());
            promotion.setName(promotionDetails.getName());
            promotion.setDescription(promotionDetails.getDescription());
            promotion.setStatus(promotionDetails.isStatus());
            return promotionRepository.save(promotion);
        }
        return null;
    }

    // Xóa khuyến mãi
    public void deletePromotion(String promotion_code){
        promotionRepository.deleteById(promotion_code);
    }

    // Tìm kiếm khuyến mãi
    public Optional<Promotion> findUserById(String promotion_code){
        return promotionRepository.findById(promotion_code);
    }

    // Tìm kiếm khuyến mãi theo tên hoặc mã
    public List<Promotion> searchPromotions(String query) {
        return promotionRepository.findByNameContainingIgnoreCase(query); // Hoặc mã tương tự nếu bạn có phương thức tìm kiếm theo mã
    }








}
