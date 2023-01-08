package ru.kata.spring.boot_security.demo.service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.entity.Role;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Data
@Service
@Transactional(readOnly = true)
public class RoleServiceImp implements RoleService{

    RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role findRole(String role) {
        return roleDao.findRole(role);
    }

    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Override
    public void removeRoleById(Long id) {
        roleDao.removeRoleById(id);
    }
}
