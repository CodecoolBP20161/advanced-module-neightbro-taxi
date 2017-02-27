package com.codecool.neighbrotaxi.service.implementation;

import com.codecool.neighbrotaxi.enums.RoleEnum;
import com.codecool.neighbrotaxi.model.Role;
import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.repository.RoleRepository;
import com.codecool.neighbrotaxi.repository.UserRepository;
import com.codecool.neighbrotaxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

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

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void login(HttpServletRequest request, User user) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }
}
