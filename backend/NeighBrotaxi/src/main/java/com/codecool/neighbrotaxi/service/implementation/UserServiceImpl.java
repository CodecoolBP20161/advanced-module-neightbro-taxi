package com.codecool.neighbrotaxi.service.implementation;

import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.repository.RoleRepository;
import com.codecool.neighbrotaxi.repository.UserRepository;
import com.codecool.neighbrotaxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Saving user object into the database with the UserRepository's save method.
     * @param user The object of the User class. This is the user we want to save into the database.
     */
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        logger.debug("Setted up user object: " + user.toString());
        userRepository.save(user);
    }

    /**
     * This method finds a user by their email address, using the UserRepository's findByEmail method.
     * @param email The email of the user we are looking for.
     * @return An object of the User class, with the valid fields queried from the database.
     */
    @Override
    public User findByEmail(String email) {
        logger.debug("Find user by this email: " + email);
        return userRepository.findByEmail(email);
    }

    /**
     * Find one user by their ID.
     * @param id Query the row where the id is the same as this given one.
     * @return A User object, with the valid fields queried from the database.
     */
    @Override
    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    /**
     * The method updates the columns of the corresponding user (based on ID) with the given fields.
     * Updates every columns, except the 'password' and 'id' ones!
     * @param user A user object parsed from a JSON string. The database entry will be updated based on this object.
     */
    @Override
    public void update(User user) {
        User userInDb = findOne(user.getId());
        userInDb.setName(user.getName());
        userInDb.setEmail(user.getEmail());
        userInDb.setRoles(user.getRoles());

        userRepository.save(userInDb);
        // TODO: modify all properties (username too)
        // But first of all we need the login user story beacuse there are more properties for the user!
    }
}
