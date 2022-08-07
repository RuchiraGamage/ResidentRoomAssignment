package com.company.Security.service;

import com.company.Security.entity.User;
import com.company.Security.entity.VerificationToken;
import com.company.Security.model.UserModel;
import com.company.Security.repository.UserRepository;
import com.company.Security.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Override
    public User registerUser(UserModel userModel) throws Exception {

        if (!userModel.getPassword().trim().equals(userModel.getMatchingPassword().trim())){
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
        VerificationToken verificationToken = new VerificationToken(user,token);
        tokenRepository.save(verificationToken);
    }

    @Override
    public boolean validateVerificationToken(String token) throws Exception {
        VerificationToken verifyToken = tokenRepository.findByToken(token);
        if (verifyToken == null)
            throw new Exception("token not valid");

        Calendar cal = Calendar.getInstance();

        if ((verifyToken.getExpireDateTime().getTime() - cal.getTime().getTime()) <= 0){
            tokenRepository.delete(verifyToken);
            throw new Exception("token expired");
        }

        User user = verifyToken.getUser();
        user.setEnable(true);
        userRepository.saveAndFlush(user);
        return true;
    }
}
