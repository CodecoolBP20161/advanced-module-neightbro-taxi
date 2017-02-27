package com.codecool.neighbrotaxi.service;


import com.codecool.neighbrotaxi.model.Role;
import com.codecool.neighbrotaxi.model.User;

import java.util.List;

public interface AdminService {
    List<User> getAllUser();
    List<Role> getAllRole();
    void deleteUser(Integer userID);
}
