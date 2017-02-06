package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.service.UserService;
import com.codecool.neighbrotaxi.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
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
    @CrossOrigin
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public Object registration(@RequestBody @Valid User user, BindingResult bindingResult){
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }

        userService.save(user);
        return userService.findOne(user.getId());
    }
}
