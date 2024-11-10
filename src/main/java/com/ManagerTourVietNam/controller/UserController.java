package com.ManagerTourVietNam.controller;

import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.UserRepository;
import com.ManagerTourVietNam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class    UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    // lấy thông tin người dùng
    @GetMapping("/api/users")
    public List<User> GetAllUser(){
        return userService.getAllUsers();
    }


    // Thêm người dùng mới
    @PostMapping("/api/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // Sửa thông tin người dung
    @PutMapping("/api/user/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User userDetail){
        return userService.updateUser(id, userDetail);
    }


    // Xóa thông tin người dùng
    @DeleteMapping("/api/user/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }

    // Tìm kiếm thông tin người dùng
    @GetMapping("/api/user/{id}")
    public Optional<User> findUserById(@PathVariable String id){
        return userService.findUserById(id);
    }

    @GetMapping("/api/user/phantrang")
    public Page<User> getUsers(
            @RequestParam int page,
            @RequestParam int pageSize) {
        // Chuyển page từ người dùng (bắt đầu từ 1) sang hệ thống của Spring Data (bắt đầu từ 0)
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return userRepository.findAll(pageable);
    }






}
