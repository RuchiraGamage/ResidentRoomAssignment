package com.company.ResidentRoomManagement.controller;

import com.company.ResidentRoomManagement.model.User;
import com.company.ResidentRoomManagement.util.ApiResponse;
import com.company.ResidentRoomManagement.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/user")
public interface UserController {

    @Tag( description = "User based operations", name = Constants.USER_APIS )
    @Operation( summary = Constants.USER_APIS + "register new user",
            description = "register new resident or admin",
            responses = { @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "register new resident or admin" ) } )
    @PostMapping
    ResponseEntity<ApiResponse> registerNewUser(@RequestBody User user);

    @Tag( description = "User based operations", name = Constants.USER_APIS )
    @Operation( summary = Constants.USER_APIS + "Get user by id",
            description = "Get user by id",
            responses = { @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "Get user by id" ) } )
    @GetMapping("{user_id}")
    ResponseEntity<ApiResponse> getUserById(@PathVariable(value = "user_id") Long userId);
}
