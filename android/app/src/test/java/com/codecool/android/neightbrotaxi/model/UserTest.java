package com.codecool.android.neightbrotaxi.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertSame;

public class UserTest {
    User user;
    @Before
    public void setUp() throws Exception {
        user = new User(0, null, null, null, null, null, null);
    }

    @After
    public void tearDown() throws Exception {
        user = null;
    }

    @Test
    public void getId() throws Exception {
        assertSame(0, user.getId());
    }

    @Test
    public void setId() throws Exception {
        user.setId(9);
        assertSame(9, user.getId());
    }

    @Test
    public void getName() throws Exception {
        assertSame(null, user.getName());
    }

    @Test
    public void setName() throws Exception {
        user.setName("TEST");
        assertSame("TEST", user.getName());
    }

    @Test
    public void getEmail() throws Exception {
        assertSame(null, user.getEmail());
    }

    @Test
    public void setEmail() throws Exception {
        user.setEmail("TEST_EMAIL");
        assertSame("TEST_EMAIL", user.getEmail());
    }

    @Test
    public void getUsername() throws Exception {
        assertSame(null, user.getUsername());
    }

    @Test
    public void setUsername() throws Exception {
        user.setUsername("TEST");
        assertSame("TEST", user.getUsername());
    }

    @Test
    public void getPassword() throws Exception {
        assertSame(null, user.getPassword());
    }

    @Test
    public void setPassword() throws Exception {
        user.setPassword("TEST");
        assertSame("TEST", user.getPassword());
    }

    @Test
    public void getPasswordConfirm() throws Exception {
        assertSame(null, user.getPasswordConfirm());
    }

    @Test
    public void setPasswordConfirm() throws Exception {
        user.setPasswordConfirm("TEST");
        assertSame("TEST", user.getPasswordConfirm());
    }

    @Test
    public void getRoles() throws Exception {
        assertSame(null, user.getRoles());
    }

    @Test
    public void setRoles() throws Exception {
        String[] arrays = new String[0];
        user.setRoles(arrays);
        assertArrayEquals(arrays, user.getRoles());
    }
}