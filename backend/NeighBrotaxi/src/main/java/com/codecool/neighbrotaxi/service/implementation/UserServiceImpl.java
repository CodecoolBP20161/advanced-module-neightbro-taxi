package com.codecool.neighbrotaxi.service.implementation;

import com.codecool.neighbrotaxi.enums.RoleEnum;
import com.codecool.neighbrotaxi.model.Role;
import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.repository.RoleRepository;
import com.codecool.neighbrotaxi.repository.UserRepository;
import com.codecool.neighbrotaxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
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
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(RoleEnum.USER.name());
        if (role == null) {
            role = new Role();
            role.setName(RoleEnum.USER.name());
            roleRepository.save(role);
        }
        roles.add(roleRepository.findByName(RoleEnum.USER.name()));
        user.setRoles(roles);
        userRepository.save(user);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }
}
