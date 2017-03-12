package com.codecool.android.neightbrotaxi.model;
import java.util.Arrays;

/**
 * This class responsible for storing user data in object.
 */
public class User {
    private Integer id;
    private String name;
    private String email;
    // Because of server response used this key in json
    private String username;
    private String password;
    private String passwordConfirm;
    private String[] roles;

    /**
     * Set object attribute based on given value.
     * @param id Integer
     * @param name String
     * @param email String
     * @param username String
     * @param password String
     * @param passwordConfirm String
     * @param roles String[]
     */
    public User(Integer id, String name, String email, String username, String password,
                String passwordConfirm, String[] roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
    }

    /**
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id Integer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String
     */
    String getUsername() {
        return username;
    }

    /**
     * @param username String
     */
    void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String
     */
    String getPassword() {
        return password;
    }

    /**
     * @param password String
     */
    void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return String
     */
    String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * @param passwordConfirm String
     */
    void setPasswordConfirm(String passwordConfirm) {this.passwordConfirm = passwordConfirm;}

    /**
     * @return String[]
     */
    String[] getRoles() {
        return roles;
    }

    /**
     * @param roles String[]
     */
    void setRoles(String[] roles) {this.roles = roles;}

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }
}
