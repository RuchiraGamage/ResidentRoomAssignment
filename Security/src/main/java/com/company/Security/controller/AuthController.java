package com.company.Security.controller;

import com.company.Security.model.UserModel;
import com.company.Security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public ResponseEntity<UserModel> registerUser(@RequestBody UserModel userModel){
        ResponseEntity<UserModel> result = null;

        try {
            result = new ResponseEntity<>(authService.registerUser(userModel), HttpStatus.CREATED);
        }catch (Exception e){
            result = new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
