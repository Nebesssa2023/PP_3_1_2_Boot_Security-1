package ru.kata.spring.boot_security.demo.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Author;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Author(name = "Victor Gabbasov", dateOfCreation = 2022)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         @NotNull BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findOne(id));
        return "users/edit";
    }

//    @PatchMapping("/{id}")
//    public String update (@ModelAttribute("user") @Valid User user,
//                          BindingResult bindingResult,
//                          @PathVariable("id") int id) {
//        if (bindingResult.hasErrors()) {
//            return "users/edit";
//        }
//        userService.update(id, user);
//        return "redirect:/users";
//    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}