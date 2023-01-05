package ru.kata.spring.boot_security.demo.controllers;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Author;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping(value = {"/users", "/"})
    public String index(@NotNull Model model) {
        User user = userService.findByUserName(getCurrentUsername());
        model.addAttribute("roles", user.getRoles());
        model.addAttribute("user", user);
        model.addAttribute("users", userService.index());
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "users";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user,
                          @RequestParam(value = "roleAdmin", required = false) String roleAdmin,
                          @RequestParam(value = "roleUser", required = false) String roleUser) {
        Set<Role> roles = new HashSet<>();
        if (roleAdmin != null) {
            roles.add(roleService.findRole(roleAdmin));
        }
        if (roleUser != null) {
            roles.add(roleService.findRole(roleUser));
        }
        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") @NotNull User user,
                         @NotNull HttpServletRequest request) {

        Set<Role> roles = user.getRoles();
        String roleUser = request.getParameter("ROLE_USER");
        String roleAdmin = request.getParameter("ROLE_ADMIN");
        if (roleAdmin != null) {
            roles.add(roleService.findRole(roleAdmin));
        }
        if (roleUser != null) {
            roles.add(roleService.findRole(roleUser));
        }
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id){
        userService.delete(id);

        return "redirect:/admin/users";
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
