package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

public interface RoleService {
    Role findRole(String role);

    void saveRole(Role role);

    void editRole(Role role);

    Role roleById(Long id);

    List<Role> allRoles();

    void deleteRole(Long id);
}
