package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.service.SecurityService;
import com.codecool.neighbrotaxi.service.UserService;
import com.codecool.neighbrotaxi.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public User userLogin(@RequestBody User user, HttpServletRequest request) {
        userService.login(request, user);
        if (Objects.equals(securityService.findLoggedInUsername(), "")) return null;
        return userService.findByUsername(securityService.findLoggedInUsername());
    }

    @RequestMapping(value = "/logged-in-user", method = RequestMethod.GET)
    public User loggedInUser(){
        return userService.findByUsername(securityService.findLoggedInUsername());
    }
}
