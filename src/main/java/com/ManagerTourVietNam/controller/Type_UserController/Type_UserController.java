package com.ManagerTourVietNam.controller.Type_UserController;


import com.ManagerTourVietNam.model.Type_user;
import com.ManagerTourVietNam.service.Type_UserService.Type_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("api/type_user/{id}")
    public Type_user edittypeUser(@PathVariable String id, @RequestBody Type_user type_user12){
        return typeUserService.UpdateType_user(id, type_user12);
    }

    @DeleteMapping("api/type_user/{id}")
    public void deletetypeUser(@PathVariable String id){
        typeUserService.deleteType_User(id);
    }


    @GetMapping("api/type_user/{id}")
    public Optional<Type_user> findTypeUserById(@PathVariable String id){
        return typeUserService.findTypeUserById(id);
    }



    
}
