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
        user.setPasswordConfirm(null);
        return user;
    }

    /**
     * Route for user data update PUT request. Update the user with the given values in the JSON object.
     * @param user The Spring Framework parses the JSON - in the RequestBody - into a User object, and give it to the UserService's update method.
     * @return The updated user from the database.
     */
    @CrossOrigin
    @RequestMapping(value = "/update-user", method = RequestMethod.PUT)
    public Object updateUser(@RequestBody @Valid User user, BindingResult bindingResult){
        // @Valid validate the email field
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }

        userService.update(user);
        // TODO: Return the SessionStorage as JSON object with the necessary messages.
        // Needed the login story's branch!!
        // TODO: setup permissions on WebSecurityConfig (login branch needed)
        return userService.findOne(user.getId());
    }
}
