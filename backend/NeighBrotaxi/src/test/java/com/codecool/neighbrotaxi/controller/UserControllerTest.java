package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.AbstractTest;
import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.service.UserService;
import com.codecool.neighbrotaxi.validator.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by cave on 2017.02.03..
 */
@Transactional
@MockBean(UserService.class)
@MockBean(BindingResult.class)
@MockBean(UserValidator.class)
public class UserControllerTest extends AbstractTest {
    @Autowired
    private UserService userService;

    private User user;

    @Autowired
    private UserController userController;

    @Autowired
    private BindingResult bindingResult;

    @Autowired
    private UserValidator userValidator;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setName("name");
        user.setPassword("pw");
        user.setEmail("email");

    }

    @Test
    public void registration_HasErrorsInBindingResult_ReturnListOfErrors() throws Exception {
        ArrayList<ObjectError> listOfErrors = new ArrayList<>();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(listOfErrors);

        Object object = userController.registration(user, bindingResult);

        assertEquals(listOfErrors, object);
    }

    @Test
    public void registration_HasNoErrorsInBindingResult_CallSaveUser() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(false);

        User returnedUser = (User) userController.registration(user, bindingResult);

        verify(userService).save(user);
    }

    @Test
    public void registration_HasNoErrorsInBindingResult_ReturnSavedUser() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.findOne(user.getId())).thenReturn(user);

        User returnedUser = (User) userController.registration(user, bindingResult);

        assertEquals(user, returnedUser);
    }

    @Test
    public void registration_CallValidate() throws Exception {

        userController.registration(user, bindingResult);

        verify(userValidator).validate(user, bindingResult);
    }
}