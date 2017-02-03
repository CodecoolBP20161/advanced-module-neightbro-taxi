package com.codecool.neighbrotaxi.service.implementation;

import com.codecool.neighbrotaxi.AbstractTest;
import com.codecool.neighbrotaxi.enums.RoleEnum;
import com.codecool.neighbrotaxi.model.Role;
import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.repository.RoleRepository;
import com.codecool.neighbrotaxi.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@Transactional
@SpyBean(BCryptPasswordEncoder.class)
public class UserServiceImplTest extends AbstractTest {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private User user;

    @Before
    public void setUp() throws Exception {
        userRepository.deleteAll();
        user = new User();
        user.setEmail("email");
        user.setPassword("password");
        user.setName("name");
    }

    @Test
    public void findByEmail() throws Exception {
        userRepository.save(user);

        User user = userService.findByEmail("email");

        assertEquals(this.user.getId(), user.getId());
    }

    @Test
    public void findByEmail_NoUserFound_ReturnNull() throws Exception {
        userRepository.save(user);

        User user = userService.findByEmail("notEmail");

        assertNull(user);

    }

    @Test
    public void save_PasswordHashingCalled() throws Exception {

        userService.save(user);

        verify(bCryptPasswordEncoder).encode("password");
    }

    @Test
    public void save_PasswordHashed() throws Exception {
        
        userService.save(user);

        assertNotEquals("password", user.getPassword());
    }

    @Test
    public void save_RolesSetted() throws Exception {
        Role role = new Role();
        role.setName(RoleEnum.USER.name());
        roleRepository.save(role);
        role = new Role();
        role.setName(RoleEnum.ADMIN.name());
        roleRepository.save(role);

        userService.save(user);

        assertEquals(2, user.getRoles().size());
    }

    @Test
    public void save_UserSavedIntoDB() throws Exception {

        userService.save(user);

        assertNotNull(userRepository.findOne(user.getId()));
    }
}