package com.company.ResidentRoomManagement.service;

import com.company.ResidentRoomManagement.Exception.UserNotFoundException;
import com.company.ResidentRoomManagement.model.User;

public interface UserService {
    User registerNewUser(User user);

    User findUserById(Long userId) throws UserNotFoundException;
}
