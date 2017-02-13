package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.service.SecurityService;
import com.codecool.neighbrotaxi.service.UserService;
import com.codecool.neighbrotaxi.service.implementation.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


@Controller
@CrossOrigin
public class UserController {
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody public String login(String logout) {
        if (logout != null) return "You have been logged out successfully.";

        return null;
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    @ResponseBody public String welcome() {
        return "Successfully logged in!";
    }
}
