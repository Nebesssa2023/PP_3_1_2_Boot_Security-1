package ru.kata.spring.boot_security.demo.controllers;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
@RestController
@RequestMapping(path = {"/admin"})
public class AdminRestController {
    UserService userService;
    RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user/newUser")
    public ResponseEntity<User> createNewUser(@RequestBody @Valid User user,
                                              @RequestBody @Valid Role role,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            StringBuilder errmsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errmsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
        }
        roleService.saveRole(role);
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<User> editUserById(@RequestBody @Valid User user,
                                             BindingResult bindingResult,
                                             @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()){
            StringBuilder errormsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errormsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
        }
        userService.editUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/viewUser")
    public ResponseEntity<User> showUser(Authentication authentication) {
        return new ResponseEntity<>((User) authentication.getPrincipal(), HttpStatus.OK);
    }

    @GetMapping("/user/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/user/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(roleService.findRoleById(id), HttpStatus.OK);
    }
}
