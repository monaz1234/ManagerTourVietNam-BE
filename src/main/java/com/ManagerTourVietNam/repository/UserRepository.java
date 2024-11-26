package com.ManagerTourVietNam.repository;

import com.ManagerTourVietNam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository <User, String> {
    // Tìm người dùng theo email
    boolean existsByEmail(String email);
}
