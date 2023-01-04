package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.Role;

public interface RoleDao {
    Role findRole(String role);
}
