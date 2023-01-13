package ru.kata.spring.boot_security.demo.controllers;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Author;


@Author(name = "Victor Gabbasov", dateOfCreation = 2023)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
@RequestMapping("/")
public class LoginController {


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping()
    public String goToLogin() {
        return "redirect:/login";
    }
}
