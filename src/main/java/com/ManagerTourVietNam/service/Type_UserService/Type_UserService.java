package com.ManagerTourVietNam.service.Type_UserService;


import com.ManagerTourVietNam.model.Type_user;
import com.ManagerTourVietNam.repository.Type_User.Type_UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Type_UserService {
    @Autowired
    private Type_UserRepository typeUserRepository;

    public List<Type_user> getAllType_User(){
        return typeUserRepository.findAll();
    }

    public Type_user addType_User(Type_user typeUser){
        return typeUserRepository.save(typeUser);
    }

    public Type_user UpdateType_user(String id, Type_user typeUser12){
        Optional<Type_user> optionalTypeUser = typeUserRepository.findById(id);
        if (optionalTypeUser.isPresent()){
            Type_user type_user1 = optionalTypeUser.get();
            type_user1.setName_type(typeUser12.getName_type());
            type_user1.setStatus(typeUser12.getStatus());
            type_user1.setSalary(typeUser12.getSalary());
            return typeUserRepository.save(type_user1);
        }
        return null;
    }

    public void deleteType_User(String id){
        typeUserRepository.deleteById(id);
    }

    public Optional<Type_user> findTypeUserById(String id){
        return typeUserRepository.findById(id);
    }
}
