package com.codecool.neighbrotaxi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoleManager {
    @Autowired
    private HttpSecurity httpSecurity;

    private List<String> urls;

    {
        urls = new ArrayList<>(Arrays.asList("/admin/**", "/user-login",
                "/registration", "/user-logout", "user-login", "logged-in-user"));
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public void addNewUrl(String url) {
        urls.add(url);
    }

    public void setAuthoritiesForRole(String role, ArrayList<String> allowedUrls) throws Exception {

        for (String url: allowedUrls) {
            if (urls.contains(url)) {
                httpSecurity.authorizeRequests()
                        .antMatchers(url)
                        .hasAuthority(role)
                        .anyRequest().authenticated();
            } else {
                throw new NoSuchElementException("No such Url in the url list!");
            }
        }
    }
}
