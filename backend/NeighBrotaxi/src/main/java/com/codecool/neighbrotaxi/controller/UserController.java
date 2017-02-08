package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.service.SecurityService;
import com.codecool.neighbrotaxi.service.UserService;
import com.codecool.neighbrotaxi.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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

    @RequestMapping(value = "/logged-in-user", method = RequestMethod.GET)
    @ResponseBody public User loggedInUser(){
        return userService.findByUsername(securityService.findLoggedInUsername());
    }
}
