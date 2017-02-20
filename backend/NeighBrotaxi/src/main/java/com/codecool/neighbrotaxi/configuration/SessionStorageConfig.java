package com.codecool.neighbrotaxi.configuration;

import com.codecool.neighbrotaxi.model.SessionStorage;
import com.codecool.neighbrotaxi.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.beans.Transient;
import java.util.ArrayList;

@Configuration
public class SessionStorageConfig {

    @Bean
    @Scope(value = "session", proxyMode= ScopedProxyMode.TARGET_CLASS)
    SessionStorage sessionStorage(){

        User user = new User();
        user.setName("anonymus");
        user.setEmail("anonymus@anonymus.com");

        SessionStorage sessionStorage = new SessionStorage();
        sessionStorage.setErrorMessages(new ArrayList<>());
        sessionStorage.setInfoMessages(new ArrayList<>());
        sessionStorage.setLoggedInUser(user);
        return sessionStorage;
    }
}
