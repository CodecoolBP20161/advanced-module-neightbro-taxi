package com.codecool.neighbrotaxi.service;

import com.codecool.neighbrotaxi.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findOne(UUID id);
    void login(HttpServletRequest request, User user);
}
