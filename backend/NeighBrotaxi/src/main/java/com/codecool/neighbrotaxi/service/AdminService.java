package com.codecool.neighbrotaxi.service;


import com.codecool.neighbrotaxi.model.User;

import java.util.List;

public interface AdminService {
    List<User> getAllUser();
    void deleteUser(Integer userID);
}
