package com.company.Security.service;

import com.company.Security.entity.PasswordResetToken;
import com.company.Security.entity.User;
import com.company.Security.entity.VerificationToken;
import com.company.Security.model.PasswordModel;
import com.company.Security.model.UserModel;
import com.company.Security.repository.PasswordResetTokenRepository;
import com.company.Security.repository.UserRepository;
import com.company.Security.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {

    private static final int DEFAULT_PASSWORD_LENGTH = 8;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public User registerUser(UserModel userModel) throws Exception {

        if (!userModel.getPassword().trim().equals(userModel.getMatchingPassword().trim())) {
            throw new Exception("password does not match with matchingPassword ");
        }

        User user = User.
                builder().
                userName(userModel.getUserName()).
                email(userModel.getEmail()).
                firstName(userModel.getFirstName()).
                lastName(userModel.getLastName()).
                password(passwordEncoder.encode(userModel.getPassword())).
                role("USER").
                build();

        return userRepository.save(user);
    }

    @Override
    public void saveVerificationTokenForUser(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        tokenRepository.save(verificationToken);
    }

    @Override
    public void updateVerificationToken(VerificationToken verificationToken) {
        tokenRepository.saveAndFlush(verificationToken);
    }

    @Override
    public boolean validateVerificationToken(String token) throws Exception {
        VerificationToken verifyToken = tokenRepository.findByToken(token);
        if (verifyToken == null)
            throw new Exception("token not valid");

        Calendar cal = Calendar.getInstance();

        if ((verifyToken.getExpireDateTime().getTime() - cal.getTime().getTime()) <= 0) {
            //tokenRepository.delete(verifyToken);
            throw new Exception("token expired");
        }

        User user = verifyToken.getUser();
        user.setEnable(true);
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public VerificationToken findVerificationTokenByUser(User user) {
        return tokenRepository.findByUserId(user.getId());
    }

    @Override
    public PasswordResetToken savePasswordResetRequest(PasswordResetToken passwordResetToken) {
       return passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public PasswordResetToken getPasswordResetTokenBuUser(User user) {
        return passwordResetTokenRepository.findByUserId(user.getId());
    }

    @Override
    public boolean validatePasswordResetToken(String token) throws Exception {
        Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if (!passwordResetToken.isPresent())
            throw new Exception("Invalid token");

        Calendar cal = Calendar.getInstance();

        if (passwordResetToken.get().getExpirationTime().getTime() - cal.getTime().getTime() <= 0)
            throw new Exception("Token expired");

        return true;
    }

    @Override
    public User resetPassword(String token) {
        User user =passwordResetTokenRepository
                .findByToken(token).get().getUser();

        user.setPassword(passwordEncoder.encode(generateRandomPassword(DEFAULT_PASSWORD_LENGTH)));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void changePassword(User user, PasswordModel passwordModel) throws Exception {
        if (!passwordEncoder.matches(passwordModel.getOldPassword(), user.getPassword()))
            throw new Exception("Old password is not correct");

        user.setPassword(passwordEncoder.encode(passwordModel.getNewPassword()));

        userRepository.saveAndFlush(user);
    }

    public String generateRandomPassword(int codeLength){
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < codeLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);
        return output ;
    }
}
