package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.service.SecurityService;
import com.codecool.neighbrotaxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@CrossOrigin
public class UserController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody public String login(String error, String logout) {
        if (error != null)
            return "Your username and password is invalid.";

        if (logout != null)
            return "You have been logged out successfully.";

        return "Please Log In!";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    @ResponseBody public String welcome() {
        return "Successfully logged in!";
    }
}
