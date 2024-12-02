package com.ManagerTourVietNam.repository;
import com.ManagerTourVietNam.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface PromotionRepository extends JpaRepository<Promotion, String> {
    // Tìm kiếm theo tên khuyến mãi, không phân biệt hoa thường
    List<Promotion> findByNameContainingIgnoreCase(String name);
    Optional<Promotion> findByCode(String code);
}
