package com.ManagerTourVietNam.controller;

import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/api/users")
    public List<User> GetAllUser(){
        return userService.getAllUsers();
    }
}
