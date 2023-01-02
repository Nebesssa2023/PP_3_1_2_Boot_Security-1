package ru.kata.spring.boot_security.demo.dao;




import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserDao {
    List<User> index();
    User show(long id);
    void save(User user);
    void update(User user);
    void delete(long id);
}
