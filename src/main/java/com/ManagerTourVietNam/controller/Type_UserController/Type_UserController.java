package com.ManagerTourVietNam.controller.Type_UserController;


import com.ManagerTourVietNam.model.Type_user;
import com.ManagerTourVietNam.service.Type_UserService.Type_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Type_UserController {
    @Autowired
    private Type_UserService typeUserService;

    @GetMapping("api/type_users")
    public List<Type_user> GetALLType_User(){
        return typeUserService.getAllType_User();
    }

    @PostMapping("api/type_user")
    public Type_user addtypeUser(@RequestBody Type_user typeUser ){
        return typeUserService.addType_User(typeUser);
    }

    
}
