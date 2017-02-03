package com.codecool.neighbrotaxi.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "role")
public class Role {
    @Id
    private String id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    {
        this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
