package com.task.service.imple;

import com.task.model.Role;
import com.task.model.User;
import com.task.repository.RoleRepository;
import com.task.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImple {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    public UserServiceImple(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


//    @PostConstruct
//    public void init() {
//        // Create roles
//        Role adminRole = new Role();
//        adminRole.setName("ADMIN");
//        roleRepository.save(adminRole);
//
//        Role userRole = new Role();
//        userRole.setName("USER");
//        roleRepository.save(userRole);
//
//        // Create users
//        User admin = new User();
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("admin"));
//        admin.setRoles(new HashSet<>(Set.of(adminRole)));
//        userRepository.save(admin);
//
//        User user = new User();
//        user.setUsername("user");
//        user.setPassword(passwordEncoder.encode("password"));
//        user.setRoles(new HashSet<>(Set.of(userRole)));
//        userRepository.save(user);
//    }
}
