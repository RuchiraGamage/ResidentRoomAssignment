package com.company.Security.service;

import com.company.Security.model.UserModel;

public interface AuthService {
    UserModel registerUser(UserModel userModel) throws Exception;
}
