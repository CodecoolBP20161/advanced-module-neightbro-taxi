package com.codecool.neighbrotaxi.service;


public interface SecurityService {
    String findLoggedInEmail();
    void autoLogin(String email, String password);
}
