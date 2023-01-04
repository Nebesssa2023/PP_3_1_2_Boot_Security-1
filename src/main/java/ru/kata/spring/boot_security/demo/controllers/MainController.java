package ru.kata.spring.boot_security.demo.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Author;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;


@Author(name = "Victor Gabbasov", dateOfCreation = 2022)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
@RequestMapping("/users")
public class MainController {

    UserService userService;

    @Contract(pure = true)
    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/user")
    public String userPage(@NotNull Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin/users")
    public String findAll(@NotNull Model model) {
        model.addAttribute("findAll", userService.findAll());
        return "findAll";
    }

    @GetMapping("/admin/{id}")
    public String findOne(@PathVariable("id") Long id, @NotNull Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "findOne";
    }

    @PostMapping("/admin/users")
    public String create(@ModelAttribute("user")
                             @Valid User user, @NotNull BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "new";
        } else {
            userService.save(user);
            return "redirect:/admin/users";
        }
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(@NotNull Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findOne(id));
        return "edit";
    }

    @PatchMapping("/admin/users/{id}")
    public String update(@Valid User user, @NotNull BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            userService.update(user);
            return "redirect:/admin/users";
        }
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
