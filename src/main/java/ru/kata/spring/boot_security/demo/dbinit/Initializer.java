//package ru.kata.spring.boot_security.demo.dbinit;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.entity.Role;
//import ru.kata.spring.boot_security.demo.entity.User;
//import ru.kata.spring.boot_security.demo.service.RoleService;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//import javax.annotation.PostConstruct;
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class Initializer {
//    private final UserService userService;
//    private final RoleService roleService;
//
//    @Autowired
//    public Initializer(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    @PostConstruct
//    public void init() {
//        Role role1 = new Role(1L, "ROLE_ADMIN");
//        Role role2 = new Role(2L, "ROLE_USER");
//
//        roleService.saveRole(role1);
//        roleService.saveRole(role2);
//
//        User user1 = new User("admin", "adminov", 26, "admin@mail.ru", "111",
//                new HashSet<>(Set.of(role1)));
//        User user2 = new User("user", "userov", 28, "user@mail.ru", "111",
//                new HashSet<>(Set.of(role2)));
//
//        userService.saveUser(user1);
//        userService.saveUser(user2);
//    }
//}
