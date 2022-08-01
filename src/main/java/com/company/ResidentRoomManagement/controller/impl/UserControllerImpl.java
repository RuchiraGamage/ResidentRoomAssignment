package com.company.ResidentRoomManagement.controller.impl;

import com.company.ResidentRoomManagement.controller.UserController;
import com.company.ResidentRoomManagement.model.User;
import com.company.ResidentRoomManagement.service.UserService;
import com.company.ResidentRoomManagement.util.ApiResponse;
import com.company.ResidentRoomManagement.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.company.ResidentRoomManagement.util.Constants.*;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<ApiResponse> registerNewUser(User user) {
        ResponseEntity<ApiResponse> response = null;

        try {
           User registeredUser =  userService.registerNewUser(user);
           response = new ResponseEntity<>(new ApiResponse(true, NEW_USER_REGISTERED, registeredUser), HttpStatus.CREATED);
        }catch (Exception e){
            response = new ResponseEntity<>(new ApiResponse(Error.ERROR, false, NEW_USER_CAN_NOT_REGISTER, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @Override
    public ResponseEntity<ApiResponse> getUserById(Long userId) {
        ResponseEntity<ApiResponse> response = null;
        try {
            response = new ResponseEntity<>(new ApiResponse(true,"User found",userService.findUserById(userId)),HttpStatus.OK);

        }catch (Exception e){
            response = new ResponseEntity<>(new ApiResponse(Error.ERROR, false,e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
