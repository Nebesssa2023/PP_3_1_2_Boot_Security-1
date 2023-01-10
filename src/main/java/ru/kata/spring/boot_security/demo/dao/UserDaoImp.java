package ru.kata.spring.boot_security.demo.dao;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Repository("userDaoImp")
public class UserDaoImp implements UserDao {


    @PersistenceContext
    EntityManager entityManager;


    @Autowired
    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u",
                User.class).getResultList();
    }

    @Override
    public User findUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void editUser(User user) {
        user.setRoles(entityManager.find(User.class, user.getId()).getRoles());
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public void deleteUserById(long id) {
        entityManager.remove(findUserById(id));
        entityManager.flush();
    }

    @Override
    public User findByUserName(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username= :username",
                User.class);
        return query.setParameter("username", username).getSingleResult();
    }

}
