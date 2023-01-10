package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

@Repository("userDao")
public interface UserDao {
    List<User> getAllUsers();

    User findUserById(long id);

    void saveUser(User user);

    void editUser(User user);

    void deleteUserById(long id);

    User findByUserName(String username);
}
