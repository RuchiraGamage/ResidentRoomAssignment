package com.company.Security.controller;

import com.company.Security.entity.PasswordResetToken;
import com.company.Security.entity.User;
import com.company.Security.entity.VerificationToken;
import com.company.Security.event.RegistrationCompleteEvent;
import com.company.Security.model.PasswordModel;
import com.company.Security.model.UserModel;
import com.company.Security.repository.UserRepository;
import com.company.Security.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private UserRepository userRepository;

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

    @GetMapping("verifyRegistration")
    public ResponseEntity<String> verifyRegistration(@RequestParam("token") String token) {
        ResponseEntity<String> result;
        try {
            authService.validateVerificationToken(token);
            result = new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>("Fail verification : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @GetMapping("user/{userId}/resendVerificationToken")
    public ResponseEntity<String> resendVerificationToken(@PathVariable("userId") long userId, final HttpServletRequest request) {
        ResponseEntity<String> result = null;
        try {
            Optional<User> user = userRepository.findById(userId);
            if (!user.isPresent())
                throw new Exception("user not found");

            VerificationToken verificationToken = authService.findVerificationTokenByUser(user.get());
            resendVerificationTokenMail(verificationToken, request);
            result = new ResponseEntity<>("Re-send the verification token", HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>("resend verification token fail : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    private void resendVerificationTokenMail(VerificationToken verificationToken, HttpServletRequest request) {
        String token = UUID.randomUUID().toString();
        verificationToken.setToken(token);
        authService.updateVerificationToken(verificationToken);

        // send mail to the user need to implement
        // get template and bind data senEmail()
        String url = applicationUrl(request) + "/verifyRegistration" +
                "?token=" + token;

        log.info("click the link to verify your account : {} ", url);
    }

    @GetMapping("/request/passwordReset")
    public ResponseEntity<String> sendPasswordResetRequest(@RequestParam("email") String email, HttpServletRequest request) {
        ResponseEntity<String> result = null;

        try {
            User user = userRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("user does not exists for mail : " + email));

            PasswordResetToken passwordResetToken = authService.getPasswordResetTokenBuUser(user);
            String newToken = UUID.randomUUID().toString();

            if (passwordResetToken == null) {
                passwordResetToken = new PasswordResetToken(newToken, user);
            } else {
                passwordResetToken.setToken(newToken);
            }

            authService.savePasswordResetRequest(passwordResetToken);
            sendPasswordResetMailToUser(passwordResetToken, request);
            result = new ResponseEntity<>("password reset mail sent", HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    private void sendPasswordResetMailToUser(PasswordResetToken passwordResetToken, HttpServletRequest request) {
        String url = applicationUrl(request) + "/passwordReset" +
                "?token=" + passwordResetToken.getToken();
        log.info("send password reset mail to your inbox click the url to set password : " + url);
    }

    @PostMapping("/passwordReset")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token){
        ResponseEntity<String> result = null;
        try {
            User updatedUser = null;
            if (authService.validatePasswordResetToken(token))
                updatedUser = authService.resetPassword(token);

            sendMailWithResetPassword(updatedUser);

            result = new ResponseEntity<>("password reset successfully check the mail", HttpStatus.OK);
        }catch (Exception e){
            result = new ResponseEntity<>("password reset fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    private void sendMailWithResetPassword(User updatedUser) {
        log.info("send password in email");
    }

    @PostMapping("/changePassword")
    public ResponseEntity<PasswordModel> changePassword(@RequestBody PasswordModel passwordModel){
        ResponseEntity<PasswordModel> result = null ;

        try {
            Optional<User> user = userRepository.findByEmail(passwordModel.getEmail());
            if (!user.isPresent())
                throw new Exception("User doesn't exists");

            authService.changePassword(user.get(), passwordModel);
            result = new ResponseEntity<>(PasswordModel.builder().isSuccess(true).build(), HttpStatus.OK);

        }catch (Exception e){
            result = new ResponseEntity<>(PasswordModel.builder().isSuccess(false).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
