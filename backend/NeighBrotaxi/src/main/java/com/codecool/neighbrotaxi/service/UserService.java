package com.codecool.neighbrotaxi.service;

import com.codecool.neighbrotaxi.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by cave on 2017.02.02..
 */
public interface UserService {
    void save(User user);
    void findByEmail(String email);
}
