package ru.kata.spring.boot_security.demo.controllers;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Author;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
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
        return "adminPage";
    }

    @GetMapping("/newUser")
    public String getNewUser(@ModelAttribute("user") User user) {
        return "redirect:/admin";
    }

    @PostMapping("/newUser")
    public String createNewUser(@ModelAttribute("user") @Valid User user,
                         Role role,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin";
        }
        roleService.saveRole(role);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("edit/{id}")
    public String getUserForEditById(Model model, @PathVariable("id") Long id) {
        model.addAttribute("role", roleService.getAllRoles());
        model.addAttribute("user", userService.findUserById(id));
        return "redirect:/admin";
    }

    @PatchMapping("/edit/{id}")
    public String editUserById(@ModelAttribute("user") @Valid User user,
                                BindingResult bindingResult,
                               @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin";
        }
        userService.editUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
