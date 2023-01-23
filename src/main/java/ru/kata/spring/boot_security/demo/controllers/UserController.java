package ru.kata.spring.boot_security.demo.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.Author;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Author(name = "Victor Gabbasov", dateOfCreation = 2023)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUser(Model model, Principal principal) {
        UserDetails messages = userService.loadUserByUsername(principal.getName());
        model.addAttribute("messages", messages);
        return "user";
    }
}
