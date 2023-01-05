package ru.kata.spring.boot_security.demo.dao;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Repository("roleDaoImp")
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    public RoleDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findRole(String role) {
        TypedQuery<Role> roleTypedQuery = entityManager.createQuery
                ("SELECT r FROM Role r WHERE r.role= :role", Role.class);
        return roleTypedQuery.setParameter("role", role).getSingleResult();
    }
}
