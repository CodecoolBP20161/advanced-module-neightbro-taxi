package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.model.Role;
import com.codecool.neighbrotaxi.model.SessionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
    @Autowired
    SessionStorage sessionStorage;

    @RequestMapping(value = "/admin-login", method = RequestMethod.POST)
    public String login() {
        return "login";
    }
}
