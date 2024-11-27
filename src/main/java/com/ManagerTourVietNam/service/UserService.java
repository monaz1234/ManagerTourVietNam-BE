package com.ManagerTourVietNam.service;

import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String id, User userDetails) throws UserPrincipalNotFoundException {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setBirth(userDetails.getBirth());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            user.setPoints(userDetails.getPoints());
            user.setReward(userDetails.getReward());
            user.setSalary(userDetails.getSalary());
            user.setStatus(userDetails.getStatus());

            if (userDetails.getTypeUser() != null) {
                user.setTypeUser(userDetails.getTypeUser());
            } else {
                user.setTypeUser(null);
            }

            return userRepository.save(user);
        }).orElseThrow(() -> new UserPrincipalNotFoundException("User not found with id " + id));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public Map<String, Object> getUsersWithPagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<User> pageUsers = userRepository.findAll(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("users", pageUsers.getContent());
        response.put("currentPage", pageUsers.getNumber());
        response.put("totalItems", pageUsers.getTotalElements());
        response.put("totalPages", pageUsers.getTotalPages());

        return response;
    }

    public List<User> searchUsers(String query) {
        // Tìm kiếm theo từ khóa (có thể là iduser, name, hoặc email)
        return userRepository.findByIduserContainingOrNameContainingOrEmailContaining(query, query, query);
    }

    public boolean checkIfUserExists(String email) {
        return userRepository.existsByEmail(email);
    }

}
