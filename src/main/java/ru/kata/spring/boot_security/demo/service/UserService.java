package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> index();
    User show(long id);
    void save(User user);
    void update(Long id, User user);
    void delete(long id);
    User findByUserName(String username);
}
