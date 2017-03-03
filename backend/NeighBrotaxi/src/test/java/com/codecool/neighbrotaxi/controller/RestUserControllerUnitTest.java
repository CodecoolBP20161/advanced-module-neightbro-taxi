package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.AbstractTest;
import com.codecool.neighbrotaxi.model.SerializableSessionStorage;
import com.codecool.neighbrotaxi.model.SessionStorage;
import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.service.SecurityService;
import com.codecool.neighbrotaxi.service.UserService;
import com.codecool.neighbrotaxi.validator.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


@Transactional
@MockBean(SecurityService.class)
@MockBean(HttpServletRequest.class)
@MockBean(SessionStorage.class)
@MockBean(UserService.class)
@MockBean(BindingResult.class)
@MockBean(UserValidator.class)
public class RestUserControllerUnitTest extends AbstractTest {
    @Autowired
    private UserService userService;

    private User user;

    @Autowired
    private RestUserController restUserController;

    @Autowired
    private BindingResult bindingResult;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SessionStorage sessionStorage;

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

        Object object = restUserController.registration(user, bindingResult);

        assertEquals(listOfErrors, object);
    }

    @Test
    public void registration_HasNoErrorsInBindingResult_CallSaveUser() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(false);

        restUserController.registration(user, bindingResult);

        verify(userService).save(user);
    }

    @Test
    public void registration_HasNoErrorsInBindingResult_ReturnSavedUser() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.findOne(user.getId())).thenReturn(user);

        User returnedUser = (User) restUserController.registration(user, bindingResult);

        assertEquals(user, returnedUser);
    }

    @Test
    public void registration_CallValidate() throws Exception {

        restUserController.registration(user, bindingResult);

        verify(userValidator).validate(user, bindingResult);
    }

    @Test
    public void userLogin_AlreadyLoggedIn_ShouldAddErrorMessageToTheSessionStorage() throws Exception {
        user.setUsername(user.getEmail());
        when(securityService.findLoggedInUsername()).thenReturn(user.getUsername());
        HttpServletRequest request = mock(HttpServletRequest.class);

        restUserController.userLogin(user, request);

        verify(sessionStorage, times(1)).addErrorMessage(anyString());
    }

    @Test
    public void userLogin_AlreadyLoggedIn_ShouldReturnSerializableSessionStorageObject() throws Exception {
        user.setUsername(user.getEmail());
        when(securityService.findLoggedInUsername()).thenReturn(user.getUsername());
        HttpServletRequest request = mock(HttpServletRequest.class);

        Object returnedObject = restUserController.userLogin(user, request);

        assertEquals(SerializableSessionStorage.class, returnedObject.getClass());
    }

    @Test
    public void userLogin_AlreadyLoggedIn_ShouldAddValidErrorMessages() throws Exception {
        user.setUsername(user.getEmail());
        when(securityService.findLoggedInUsername()).thenReturn(user.getUsername());
        HttpServletRequest request = mock(HttpServletRequest.class);

        restUserController.userLogin(user, request);

        verify(sessionStorage, times(1)).addErrorMessage("Already logged in.");
    }

    @Test
    public void userLogin_InvalidUsernameOrPassword_ShouldAddErrorMessageToTheSessionStorage() throws Exception {
        when(securityService.findLoggedInUsername()).thenReturn("anonymous");
        HttpServletRequest request = mock(HttpServletRequest.class);
        doThrow(new BadCredentialsException("invalidCredentials")).when(userService).login(request, user);

        restUserController.userLogin(user, request);

        verify(sessionStorage, times(1)).addErrorMessage(anyString());
    }

    @Test
    public void userLogin_InvalidUsernameOrPassword_ShouldReturnSerializableSessionStorageObject() throws Exception {
        when(securityService.findLoggedInUsername()).thenReturn("anonymous");
        HttpServletRequest request = mock(HttpServletRequest.class);
        doThrow(new BadCredentialsException("invalidCredentials")).when(userService).login(request, user);

        Object returnedObject = restUserController.userLogin(user, request);

        assertEquals(SerializableSessionStorage.class, returnedObject.getClass());
    }

    @Test
    public void userLogin_InvalidUsernameOrPassword_ShouldAddValidErrorMessage() throws Exception {
        when(securityService.findLoggedInUsername()).thenReturn("anonymous");
        HttpServletRequest request = mock(HttpServletRequest.class);
        doThrow(new BadCredentialsException("invalidCredentials")).when(userService).login(request, user);

        restUserController.userLogin(user, request);

        verify(sessionStorage, times(1)).addErrorMessage("Invalid username or password!");
    }

    @Test
    public void userLogin_SuccessfulAuth_ShouldCallSessionStorageLoggedInUserSetter() throws Exception {
        when(securityService.findLoggedInUsername()).thenReturn("anonymous");
        HttpServletRequest request = mock(HttpServletRequest.class);
        doNothing().when(userService).login(request, user);
        when(userService.findByUsername(anyString())).thenReturn(user);

        restUserController.userLogin(user, request);

        verify(sessionStorage, times(1)).setLoggedInUser(user);
    }

    @Test
    public void userLogin_SuccessfulAuth_ShouldAddInfoMessageToTheSessionStorage() throws Exception {
        when(securityService.findLoggedInUsername()).thenReturn("anonymous");
        HttpServletRequest request = mock(HttpServletRequest.class);
        doNothing().when(userService).login(request, user);
        when(userService.findByUsername(anyString())).thenReturn(user);

        restUserController.userLogin(user, request);

        verify(sessionStorage, times(1)).addInfoMessage(anyString());
    }

    @Test
    public void userLogin_SuccessfulAuth_ShouldAddErrorMessageToTheSessionStorage() throws Exception {
        when(securityService.findLoggedInUsername()).thenReturn("anonymous");
        HttpServletRequest request = mock(HttpServletRequest.class);
        doNothing().when(userService).login(request, user);
        when(userService.findByUsername(anyString())).thenReturn(user);

        Object returnedObject = restUserController.userLogin(user, request);

        assertEquals(SerializableSessionStorage.class, returnedObject.getClass());
    }

    @Test
    public void userLogin_SuccessfulAuth_ShouldAddValidErrorMessage() throws Exception {
        when(securityService.findLoggedInUsername()).thenReturn("anonymous");
        HttpServletRequest request = mock(HttpServletRequest.class);
        doNothing().when(userService).login(request, user);
        when(userService.findByUsername(anyString())).thenReturn(user);

        restUserController.userLogin(user, request);

        verify(sessionStorage, times(1)).addInfoMessage("Successfully logged in!");
    }


}