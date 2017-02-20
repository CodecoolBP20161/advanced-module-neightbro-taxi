package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.model.SerializableSessionStorage;
import com.codecool.neighbrotaxi.model.SessionStorage;
import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.service.SecurityService;
import com.codecool.neighbrotaxi.service.UserService;
import com.codecool.neighbrotaxi.service.implementation.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;


@Controller
public class UserController {
    @Autowired
    private SessionStorage sessionStorage;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody public SerializableSessionStorage login(String logout) {
        if (logout != null) {
            sessionStorage.clearAllErrorMessages();
            sessionStorage.clearAllInfoMessages();
            sessionStorage.addInfoMessage("You have been logged out successfully.");
            return new SerializableSessionStorage(sessionStorage);
        }

        return null;
    }

}
