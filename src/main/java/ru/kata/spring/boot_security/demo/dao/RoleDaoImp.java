package ru.kata.spring.boot_security.demo.dao;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
//@Repository("roleDaoImp")
public class RoleDaoImp implements RoleDao{

    EntityManager entityManager;

    public RoleDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Role findRole(String role) {
        TypedQuery<Role> roleTypedQuery = entityManager.createQuery
                ("from Role r where r.role= :role", Role.class);
        return roleTypedQuery.setParameter("role", role).getSingleResult();
    }
}
