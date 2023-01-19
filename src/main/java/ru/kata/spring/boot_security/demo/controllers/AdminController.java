package ru.kata.spring.boot_security.demo.controllers;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.Author;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Author(name = "Victor Gabbasov", dateOfCreation = 2022)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
@RequestMapping("/admin")
public class AdminController {

    UserService userService;
    RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getAdminPage(Model model) {
        List<User> userList = userService.getAllUsers();
        User user = userService.findByUserName(getCurrentUsername());
        model.addAttribute("roles", user.getRoles());
        model.addAttribute("user", user);
        model.addAttribute("users", userList);
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "adminPage2";
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
