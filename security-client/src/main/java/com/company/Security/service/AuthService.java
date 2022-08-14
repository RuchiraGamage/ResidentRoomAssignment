package com.company.Security.service;

import com.company.Security.entity.PasswordResetToken;
import com.company.Security.entity.User;
import com.company.Security.entity.VerificationToken;
import com.company.Security.model.PasswordModel;
import com.company.Security.model.UserModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AuthService {
    User registerUser(UserModel userModel) throws Exception;

    @Transactional
    void saveVerificationTokenForUser(User user, String token);

    void updateVerificationToken(VerificationToken verificationToken);

    boolean validateVerificationToken(String token) throws Exception;

    VerificationToken findVerificationTokenByUser(User user);

    PasswordResetToken savePasswordResetRequest(PasswordResetToken passwordResetToken);

    PasswordResetToken getPasswordResetTokenBuUser(User user);

    boolean validatePasswordResetToken(String token) throws Exception;

    User resetPassword(String token);

    void changePassword(User user, PasswordModel passwordModel) throws Exception;
}
