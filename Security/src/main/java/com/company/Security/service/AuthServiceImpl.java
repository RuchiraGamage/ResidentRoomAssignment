package com.company.Security.service;

import com.company.Security.entity.User;
import com.company.Security.model.UserModel;
import com.company.Security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel registerUser(UserModel userModel) throws Exception {

        if (!userModel.getPassword().trim().equals(userModel.getMatchingPassword().trim())){
            throw new Exception("password does not match with matchingPassword ");
        }

        User user = User.
                builder().
                userName(userModel.getUserName()).
                email(userModel.getEmail()).
                firstName(userModel.getFirstName()).
                lastName(userModel.getLastName()).
                password(userModel.getPassword()).build();

        User savedInstance = userRepository.save(user);
        userModel.setId(savedInstance.getId());

        return userModel;
    }
}
