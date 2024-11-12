package com.ManagerTourVietNam.controller;

import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.UserRepository;
import com.ManagerTourVietNam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.stream.Collectors;



import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class    UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Lấy thông tin tất cả người dùng
    @GetMapping("/api/users")
    public List<User> GetAllUser() {
        return userService.getAllUsers();
    }

    // Thêm người dùng mới
    @PostMapping("/api/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // Sửa thông tin người dùng
    @PutMapping("/api/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetail) {
        try {
            User updatedUser = userService.updateUser(id, userDetail);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // You can customize the error message as needed
        }
    }

    // Xóa người dùng
    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Tìm kiếm thông tin người dùng theo id
    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Lấy danh sách người dùng với phân trang
    @GetMapping("/api/user/phantrang")
    public ResponseEntity<Page<User>> getUsers(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
        Page<User> pageUsers = userRepository.findAll(pageable);
        return ResponseEntity.ok(pageUsers);
    }

    @GetMapping("/api/user-ids")
    public List<String> getAllUserIds() {
        return userRepository.findAll().stream()
                .map(User::getIduser)
                .collect(Collectors.toList());
    }

    @GetMapping("api/users/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String query) {
        List<User> users = userService.searchUsers(query);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



}
