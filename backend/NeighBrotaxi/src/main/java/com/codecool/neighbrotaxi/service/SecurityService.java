package com.codecool.neighbrotaxi.service;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    public String findLoggedInUsername();
    public void autoLogin(String username, String password);
}
