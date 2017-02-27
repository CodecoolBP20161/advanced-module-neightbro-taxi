package com.codecool.neighbrotaxi.configuration;

import com.codecool.neighbrotaxi.enums.RoleEnum;
import com.codecool.neighbrotaxi.model.Role;
import com.codecool.neighbrotaxi.model.User;
import com.codecool.neighbrotaxi.repository.RoleRepository;
import com.codecool.neighbrotaxi.repository.UserRepository;
import com.codecool.neighbrotaxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class PostConstructor {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PostConstruct
    public void fillUpDb(){
        Role role = new Role();

        if (roleRepository.findByName("ADMIN") == null) {
            role.setName("ADMIN");
            roleRepository.save(role);
        }

        if (roleRepository.findByName("USER") == null) {
            role = new Role();
            role.setName("USER");
            roleRepository.save(role);
        }
    }

    /*
    TODO: Dunno how to test with this PostConstruct
    @PostConstruct
    public void getFirstAdminInput() {
        Scanner reader = new Scanner(System.in);
        boolean regNotFinished = true;
        while (regNotFinished) {
            regNotFinished = false;

            System.out.println("Enter a name: ");
            String name = reader.next();
            System.out.println("Enter an email: ");
            String email = reader.next();

            boolean notSimilarPw = true;
            String password = "";
            while (notSimilarPw) {
                System.out.println("Enter a password: ");
                password = reader.next();
                System.out.println("Enter a password confirmation: ");
                String passwordConfirm = reader.next();

                if (Objects.equals(password, passwordConfirm)) notSimilarPw = false;
                else System.out.println("Passwords don't match!\n");
            }
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setUsername(email);
            try {
                userService.save(user);
                Set<Role> roleSet = user.getRoles();
                roleSet.add(roleRepository.findByName(RoleEnum.ADMIN.name()));
                user.setRoles(roleSet);
                userRepository.save(user);
            } catch (Exception e) {
                System.out.println("Not valid email");
                regNotFinished = true;
            }
        }
    }
    */

    @PostConstruct
    public void setupAdmin(){
        // TODO: think about how to reg a main admin on server startup.

        // Its a fast version before DEMO day
        if (userRepository.findByEmail("admin@admin.com") == null) {

            User user = new User();
            user.setEmail("admin@admin.com");
            user.setName("admin");
            user.setPassword("admin");
            user.setUsername("admin");
            userService.save(user);
            Set<Role> roleSet = user.getRoles();
            roleSet.add(roleRepository.findByName(RoleEnum.ADMIN.name()));
            user.setRoles(roleSet);
            userRepository.save(user);
        }
    }
}
