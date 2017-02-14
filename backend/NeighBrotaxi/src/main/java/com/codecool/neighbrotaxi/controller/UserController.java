package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.service.SecurityService;
import com.codecool.neighbrotaxi.service.UserService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
@CrossOrigin
public class UserController {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody public String login(String error, String logout) {
        System.out.println(securityService.findLoggedInUsername());
        if (error != null) {
            return "Your username and password is invalid!";
        }

        if (logout != null) return "You have been logged out successfully.";

        return null;
    }

    @RequestMapping(value = "/user-login", method = RequestMethod.POST)
    @ResponseBody public String userLogin(@RequestBody User user){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("username", user.getUsername());
        params.add("password", user.getPassword());
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/login").queryParams(params).build();
        try {
            return userService.login(uriComponents.getQuery());
        } catch (UnirestException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    @ResponseBody public String welcome() {
        return "Successfully logged in!";
    }
}
