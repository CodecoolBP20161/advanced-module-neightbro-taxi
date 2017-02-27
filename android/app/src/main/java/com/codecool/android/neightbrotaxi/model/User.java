package com.codecool.android.neightbrotaxi.model;

import java.util.Arrays;

public class User {
    private final static String TAG = User.class.getSimpleName() + "<>";

    private Integer id;
    private String name;
    private String email;
    // Because of server response used this key in json
    private String username;
    private String password;
    private String passwordConfirm;
    private Arrays roles;

    public User(Integer id, String name, String email, String username, String password, String passwordConfirm, Arrays roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Arrays getRoles() {
        return roles;
    }

    public void setRoles(Arrays roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", roles=" + roles +
                '}';
    }
}
