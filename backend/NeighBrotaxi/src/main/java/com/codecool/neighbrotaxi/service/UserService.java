package com.codecool.neighbrotaxi.service;

import com.codecool.neighbrotaxi.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


public interface UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    void save(User user);
    User findByEmail(String email);
    User findOne(Integer id);
    void update(User user);
}
