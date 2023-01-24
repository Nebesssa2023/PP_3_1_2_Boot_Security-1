package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    User findUserById(long id);

    void saveUser(User user);

    void editUser(Long id, User user);

    void deleteUserById(long id);

    User findByUserName(String username);
}
