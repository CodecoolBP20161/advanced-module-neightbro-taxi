package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.service.UserService;
import com.codecool.neighbrotaxi.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    /**
     * Route for user registration POST request. Using the userValidator for checking valid input.
     * If every input value is valid, then return an object of the saved user. If some input is invalid, returns a list of errors.
     * @param user It gets a JSON in the request body, and parse it into a User object.
     * @param bindingResult The method will store the errors in this object.
     * @return An object. If every input value is valid: returns an object of the saved user, else: returns a list of errors.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public Object registration(@RequestBody @Valid User user, BindingResult bindingResult){
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }

        userService.save(user);
        user.setPasswordConfirm(null);
        return user;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String error, String logout) {
        if (error != null)
            return "Your username and password is invalid.";

        if (logout != null)
            return "You have been logged out successfully.";

        return "Successfully logged in!";
    }
}
