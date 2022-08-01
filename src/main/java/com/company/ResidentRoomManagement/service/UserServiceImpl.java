package com.company.ResidentRoomManagement.service;

import com.company.ResidentRoomManagement.Exception.UserNotFoundException;
import com.company.ResidentRoomManagement.model.Resident;
import com.company.ResidentRoomManagement.model.User;
import com.company.ResidentRoomManagement.repository.ResidentRepository;
import com.company.ResidentRoomManagement.repository.UserRepository;
import com.company.ResidentRoomManagement.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public User registerNewUser(User user) {
        User registeredUser = null;
        if (user.getUserGroupId() == Constants.admin){
            registeredUser = userRepository.save(user);
        }else if (user.getUserGroupId() == Constants.resident){
            registeredUser = userRepository.save(user);

            Resident resident = new Resident();
            resident.setUser(user);

            residentRepository.save(resident);
        }

        return registeredUser;
    }

    @Override
    public User findUserById(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent())
            throw new UserNotFoundException("User Not found");

        return user.get();
    }
}
