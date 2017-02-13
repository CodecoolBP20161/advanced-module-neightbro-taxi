package com.codecool.neighbrotaxi.service;

import com.codecool.neighbrotaxi.model.User;


public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findOne(Integer id);
}
