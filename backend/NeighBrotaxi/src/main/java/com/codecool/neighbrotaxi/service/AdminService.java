package com.codecool.neighbrotaxi.service;


import com.codecool.neighbrotaxi.model.Role;
import com.codecool.neighbrotaxi.model.User;

import java.util.List;

public interface AdminService {
    List<User> getAllUser();
    List<Role> getAllRole();
    void addRole(Role role);
    void deleteUser(Integer userID);
    void deleteRole(Integer roleID);
}
