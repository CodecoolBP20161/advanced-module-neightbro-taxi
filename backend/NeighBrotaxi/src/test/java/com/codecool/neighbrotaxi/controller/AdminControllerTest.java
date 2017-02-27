package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.AbstractTest;
import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.repository.UserRepository;
import com.codecool.neighbrotaxi.service.AdminService;
import com.codecool.neighbrotaxi.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@Transactional
@MockBean(Model.class)
@MockBean(AdminService.class)
public class AdminControllerTest extends AbstractTest {
    @Autowired
    private AdminController adminController;

    @Autowired
    private AdminService adminService;

    @Autowired
    private Model model;

    private User user;


    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setName("name");
        user.setPassword("pw");
        user.setEmail("email@email.com");
    }

    @Test
    public void getAllUsers_RenderTheCorrectTemplate() throws Exception {

        String renderedPage = adminController.getAllUsers(model);

        assertEquals("admin_users", renderedPage);
    }

    @Test
    public void getAllUsers_AddCorrectUsersIntoModel() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(adminService.getAllUser()).thenReturn(users);


        adminController.getAllUsers(model);

        verify(model, atLeastOnce()).addAttribute("user_list", users);
    }

    @Test
    public void deleteUser_ReturnValue_RedirectToUsersListPage() throws Exception {

        String redirectUrl = adminController.deleteUser("1");

        assertEquals("redirect:/admin/users", redirectUrl);
    }

    @Test
    public void deleteUser_CallDeleteUserFromUserService() throws Exception {
        user.setId(1);

        adminController.deleteUser("1");

        verify(adminService, times(1)).deleteUser(1);
    }

    @Test
    public void home_RenderValidTemplate() throws Exception {

        String renderedPage = adminController.home();

        assertEquals("admin_page", renderedPage);
    }
}