package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

@Repository("roleDao")
public interface RoleDao {
    Role findRole(String role);

    void editRole(Role role);

    void saveRole(Role role);

    Role roleById(Long id);

    List<Role> allRoles();

    void deleteRole(Long id);
}
