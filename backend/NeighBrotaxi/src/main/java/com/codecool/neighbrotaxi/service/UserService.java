package com.codecool.neighbrotaxi.service;

import com.codecool.neighbrotaxi.model.User;


public interface UserService {
    void save(User user);
    void findByEmail(String email);
}
