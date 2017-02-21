package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.model.SerializableSessionStorage;
import com.codecool.neighbrotaxi.model.SessionStorage;
import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.service.SecurityService;
import com.codecool.neighbrotaxi.service.UserService;
import com.codecool.neighbrotaxi.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;


@RestController
@CrossOrigin
public class RestUserController {
    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SessionStorage sessionStorage;


    /**
     * Route for user registration POST request. Using the userValidator for checking valid input.
     * If every input value is valid, then return an object of the saved user. If some input is invalid, returns a list of errors.
     * @param user It gets a JSON in the request body, and parse it into a User object.
     * @param bindingResult The method will store the errors in this object.
     * @return An object. If every input value is valid: returns an object of the saved user, else: returns a list of errors.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public Object registration(@RequestBody @Valid User user, BindingResult bindingResult){
        user.setUsername(user.getEmail());
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }

        userService.save(user);
        user.setPasswordConfirm(null);
        return user;
    }

    @RequestMapping(value = "/user-login", method = RequestMethod.POST)
    public SerializableSessionStorage userLogin(@RequestBody User user, HttpServletRequest request) {
        sessionStorage.clearAllErrorMessages();
        sessionStorage.clearAllInfoMessages();

        try {
            userService.login(request, user);
        } catch (AuthenticationException e) {
            sessionStorage.addErrorMessage("Invalid username or password!");
            return new SerializableSessionStorage(sessionStorage);
        }

        if (!Objects.equals(sessionStorage.getLoggedInUser().getName(), "anonymous")) {
            sessionStorage.addErrorMessage("Already logged in.");
        } else {
            sessionStorage.setLoggedInUser(userService.findByUsername(securityService.findLoggedInUsername()));
            sessionStorage.addInfoMessage("Successfully logged in!");
        }
        return new SerializableSessionStorage(sessionStorage);
    }

    @RequestMapping(value = "/logged-in-user", method = RequestMethod.GET)
    public Object loggedInUser(){
        sessionStorage.clearAllErrorMessages();
        sessionStorage.clearAllInfoMessages();

        if (Objects.equals(sessionStorage.getLoggedInUser().getName(), "anonymous")) {
            sessionStorage.addErrorMessage("Nobody is logged in!");
            return new SerializableSessionStorage(sessionStorage);
        } else {
            sessionStorage.setLoggedInUser(userService.findByUsername(sessionStorage.getLoggedInUser().getUsername()));
        }

        return sessionStorage.getLoggedInUser();
    }
}
