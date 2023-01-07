package ru.kata.spring.boot_security.demo.service;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

@Data
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RegistrationService {
    UserService userService;
    RoleService roleService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserService userService,
                               RoleService roleService,
                               PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        var encodePass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePass);
        userService.save(user);
    }
}
