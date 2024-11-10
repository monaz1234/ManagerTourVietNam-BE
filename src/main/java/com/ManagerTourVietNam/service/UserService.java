package com.ManagerTourVietNam.service;

import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // danh sách người dùng
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    // thêm người dùng
    public User addUser(User user){
        return userRepository.save(user);
    }



    public User updateUser(String id, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userDetails.getName());
            user.setBirth(userDetails.getBirth());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            user.setPoints(userDetails.getPoints());
            user.setReward(userDetails.getReward());
            user.setSalary(userDetails.getSalary());
            user.setStatus(userDetails.getStatus());

            // Cập nhật typeUser từ userDetails
            if (userDetails.getTypeUser() != null) {
                user.setTypeUser(userDetails.getTypeUser());
            } else {
                // Xử lý trường hợp không có thông tin về typeUser (nếu cần)
                user.setTypeUser(null); // Hoặc sử dụng mặc định nếu cần
            }

            return userRepository.save(user);
        }
        return null;
    }



    // Xóa người dùng
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }


    // Tìm kiếm người dùng
    public Optional<User> findUserById(String id){
        return userRepository.findById(id);
    }
    // Cập nhật phương thức phân trang



    // Lấy danh sách người dùng phân trang
    public Page<User> getUsersWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);  // Tạo Pageable với số trang và kích thước trang
        return userRepository.findAll(pageable);  // Trả về danh sách phân trang
    }


}
