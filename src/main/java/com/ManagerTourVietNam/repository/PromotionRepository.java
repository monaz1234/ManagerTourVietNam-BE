package com.ManagerTourVietNam.repository;
import com.ManagerTourVietNam.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface PromotionRepository extends JpaRepository<Promotion, String> {
    // Tìm kiếm theo tên khuyến mãi, không phân biệt hoa thường
    List<Promotion> findByNameContainingIgnoreCase(String name);

    Optional<Promotion> findByCode(String code);

//    List<Promotion> findByCodePromotion(String code);

    @Query("SELECT p FROM Promotion p WHERE p.code = :code")
    List<Promotion> findByCodePromotion(@Param("code") String code);

}
