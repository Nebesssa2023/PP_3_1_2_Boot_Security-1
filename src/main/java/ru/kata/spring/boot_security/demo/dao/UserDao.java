package ru.kata.spring.boot_security.demo.dao;




import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
@Repository("userDao")
public interface UserDao {
    List<User> index();
    User show(long id);
    void save(User user);
    void update(User user);
    void delete(long id);
    User findByUserName(String username);
}
