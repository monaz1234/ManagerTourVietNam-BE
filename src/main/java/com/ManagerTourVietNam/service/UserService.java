package com.ManagerTourVietNam.service;

import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
