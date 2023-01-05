package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserServiceJpa {
    List<User> findAll();
    User findOne(long id);
    boolean save(User user);
    void update(User user);
    boolean delete(long id);
    User findByUserName(String username);
}
