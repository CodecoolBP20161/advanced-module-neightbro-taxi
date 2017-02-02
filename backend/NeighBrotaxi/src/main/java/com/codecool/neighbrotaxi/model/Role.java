package com.codecool.neighbrotaxi.model;


import com.codecool.neighbrotaxi.enums.RoleEnum;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "role")
public class Role {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
    private Set<User> users;

    {
        this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return id;
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
