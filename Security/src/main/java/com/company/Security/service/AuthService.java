package com.company.Security.service;

import com.company.Security.entity.User;
import com.company.Security.model.UserModel;

public interface AuthService {
    User registerUser(UserModel userModel) throws Exception;

    void saveVerificationTokenForUser(User user, String token);

    boolean validateVerificationToken(String token) throws Exception;
}
