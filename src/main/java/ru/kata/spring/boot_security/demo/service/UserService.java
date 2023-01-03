package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.dao.UserRegistrationDto;
import ru.kata.spring.boot_security.demo.entity.User;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
