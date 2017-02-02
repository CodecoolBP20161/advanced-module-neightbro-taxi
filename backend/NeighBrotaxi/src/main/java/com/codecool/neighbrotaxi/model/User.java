package com.codecool.neighbrotaxi.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * Created by cave on 2017.02.02..
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String passwordConfirm;
    private Set<Role> roles;


    {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
