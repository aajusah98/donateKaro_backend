package com.example.donatekaro.controller;

import com.example.donatekaro.dto.UserRequest;
import com.example.donatekaro.model.User;
import com.example.donatekaro.service.UserService;
import com.example.donatekaro.views.UserViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService service;

        @PostMapping("/registration")
    public Object regisration(@RequestBody UserRequest userRequest) throws NumberFormatException, IOException {
        return service.registration(userRequest);
    }


        @PutMapping("/updateUser/{userId}")
    public Object updateUser(@PathVariable long userId,@RequestBody UserRequest userRequest)throws  NumberFormatException,IOException{

        return service.updateUserById(userId,userRequest);
    }

    //login
    @PostMapping("login")
    public Object login(@RequestBody UserRequest userRequest) {
        return service.login(userRequest);
    }

    @GetMapping("/users")
    public List<UserViews> getAllUser(){
        return service.getAllUsers();
    }

    @GetMapping("/user/profile/{userId}")
    public UserViews getUserById(@PathVariable long userId){
            return service.getUserProfile(userId);
    }

    @DeleteMapping("/delete/user/{userId}")

    public Object deleteProduct(@PathVariable long userId) {
        return service.deleteUser(userId);
    }

}
