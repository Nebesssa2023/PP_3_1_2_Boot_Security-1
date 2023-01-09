package ru.kata.spring.boot_security.demo.controllers;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    @GetMapping
    public String adminPage(@NotNull Model model) {
        model.addAttribute("users", userService.index());
        return "admin";
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable("id") Long id, @NotNull Model model) {
        model.addAttribute("user", userService.show(id));
        return "findOne";
    }

    @GetMapping(value = {"/users"})
    public String showAllUsers(@NotNull Model model) {
        List<User> userList = userService.index();
        User user = userService.findByUserName(getCurrentUsername());
        model.addAttribute("roles", user.getRoles());
        model.addAttribute("user", user);
        model.addAttribute("users", userList);
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "findAll";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         Role role,
                         @NotNull BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        roleService.saveRole(role);
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@NotNull Model model, @PathVariable("id") Long id) {
        model.addAttribute("roles", roleService.allRoles());
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PatchMapping("/edit/{id}")
    public String update (@ModelAttribute("user") @Valid User user,
                          Role role,
                          @NotNull BindingResult bindingResult,
                          @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        roleService.saveRole(role);
        userService.update(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.delete(id);

        return "redirect:/admin/users";
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
