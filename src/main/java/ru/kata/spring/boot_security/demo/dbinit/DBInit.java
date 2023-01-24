//package ru.kata.spring.boot_security.demo.dbinit;
//
//
//import lombok.AccessLevel;
//import lombok.experimental.FieldDefaults;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import ru.kata.spring.boot_security.demo.entity.Role;
//import ru.kata.spring.boot_security.demo.entity.User;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.HashSet;
//import java.util.Set;
//
//
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@Component
//@Transactional
//public class DBInit implements ApplicationRunner {
//
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Autowired
//    public DBInit(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        Role admin = new Role("ROLE_ADMIN");
//        Role user = new Role("ROLE_USER");
//        Set<Role> roleSetAdmin = new HashSet<>();
//        roleSetAdmin.add(admin);
//        User adminUser = new User("Admin", "Adminov", 30, "admin@mail.ru",
//                "$2a$12$1zdunqaHtPP.bmoo6/FJZe9otLFFixn6TR7As8sa4ns3TeWDnWWnq", roleSetAdmin);
//        Set<Role> roleSetUser = new HashSet<>();
//        roleSetUser.add(user);
//        User casualUser = new User("User", "Userov", 40, "user@mail.ru",
//                "$2a$12$MhP7mSHU8AOun4DLgI7Gd.1ofXk.vojoQBoPDOzLAzqYmmUbaRUeG", roleSetUser);
//
//        entityManager.persist(adminUser);
//        entityManager.persist(casualUser);
//
//        System.out.println("Admin created. Username: admin@mail.ru, password: admin");
//        System.out.println("User created. Username: user@mail.ru, password: user");
//    }
//}
