package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

public interface RoleService {
    Role findRole(String role);

    void saveRole(Role role);

    void removeRoleById(Long id);
}
