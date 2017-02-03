package com.codecool.neighbrotaxi.validator;

import com.codecool.neighbrotaxi.AbstractTest;
import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by kalman on 2017.02.02..
 */
@MockBean(UserService.class)
@MockBean(Errors.class)
@Transactional
public class UserValidatorTest extends AbstractTest {

    @Autowired
    private UserService userService;

    @Autowired
    private Errors errors;

    @Autowired
    private UserValidator userValidator;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setEmail("email");
        user.setPassword("password");
        user.setPasswordConfirm("password");
    }

    @Test
    public void validate_AllFieldValid_NoErrorsInBinding() throws Exception {

        userValidator.validate(user, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
        verify(errors, never()).rejectValue(anyString(), anyString(), anyString());
    }

    @Test
    public void validate_EmailFieldNotValid_EmailAlreadyInUse() {
        when(userService.findByEmail(anyString())).thenReturn(new User());

        userValidator.validate(user, errors);

        verify(errors).rejectValue("email", "Duplicate.user.email", "Email already in database");
    }

    @Test
    public void validate_PassLength_Short() {
        user.setPassword("har");
        user.setPasswordConfirm("har");
        userValidator.validate(user, errors);

        verify(errors).rejectValue("password", "Size.userForm.password", "The size of the password is incorrect");
    }

    @Test
    public void validate_PassLength_Long() {
        user.setPassword("harrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        user.setPasswordConfirm("harrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        userValidator.validate(user, errors);

        verify(errors).rejectValue("password", "Size.userForm.password", "The size of the password is incorrect");
    }

}