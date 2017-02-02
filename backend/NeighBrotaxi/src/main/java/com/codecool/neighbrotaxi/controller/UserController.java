package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.model.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute(value = "registrationForm") User user){
        return null;
    }
}
