package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

@Repository("roleDao")
public interface RoleDao {
    Role findRole(String role);

}
