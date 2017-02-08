package com.codecool.neighbrotaxi.service;

import com.codecool.neighbrotaxi.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findOne(UUID id);
}
