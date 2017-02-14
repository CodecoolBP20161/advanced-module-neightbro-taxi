package com.codecool.neighbrotaxi.service;

import com.codecool.neighbrotaxi.model.User;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findOne(UUID id);
//    String login(String urlParams) throws UnirestException;
}
