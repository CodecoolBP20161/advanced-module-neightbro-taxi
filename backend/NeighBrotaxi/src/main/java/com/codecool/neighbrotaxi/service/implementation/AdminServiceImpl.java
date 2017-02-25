package com.codecool.neighbrotaxi.service.implementation;

import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.repository.RoleRepository;
import com.codecool.neighbrotaxi.repository.UserRepository;
import com.codecool.neighbrotaxi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void deleteUser(Integer userID) { userRepository.delete(userID);
    }
}
