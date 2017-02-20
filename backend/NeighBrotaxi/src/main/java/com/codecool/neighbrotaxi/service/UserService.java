package com.codecool.neighbrotaxi.service;

import com.codecool.neighbrotaxi.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    void login(HttpServletRequest request, User user);
    User findOne(Integer id);
}
