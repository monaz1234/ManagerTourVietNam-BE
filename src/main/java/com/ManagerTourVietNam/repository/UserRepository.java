package com.ManagerTourVietNam.repository;

import com.ManagerTourVietNam.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository <User, String> {

    Page<User> findByNameContaining(String name, Pageable pageable);
    List<User> findByIduserContainingOrNameContainingOrEmailContaining(String iduser, String name, String email);
}
