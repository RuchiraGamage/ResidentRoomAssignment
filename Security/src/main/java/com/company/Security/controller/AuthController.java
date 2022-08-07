package com.company.Security.controller;

import com.company.Security.entity.User;
import com.company.Security.event.RegistrationCompleteEvent;
import com.company.Security.model.UserModel;
import com.company.Security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController(value = "auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("register")
    public ResponseEntity<User> registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        ResponseEntity<User> result = null;

        try {
            User registeredUser = authService.registerUser(userModel);
            publisher.publishEvent(new RegistrationCompleteEvent(registeredUser, applicationUrl(request)));
            result = new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (Exception e) {
            result = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }

    @PostMapping("verifyRegistration")
    public ResponseEntity<String> verifyRegistration(@RequestParam("token") String token) {
        ResponseEntity<String> result;
        try {
            authService.validateVerificationToken(token);
            result = new ResponseEntity<>("Success",HttpStatus.OK);
        }catch (Exception e){
            result = new ResponseEntity<>("Fail verification : " + e.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }
}
