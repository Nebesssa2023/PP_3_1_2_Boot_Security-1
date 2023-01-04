package ru.kata.spring.boot_security.demo.service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.User;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Data
@Service
@Transactional(readOnly = true)
public class UserDetailServiceImp implements UserDetailsService {

    UserService userService;

//    @Contract(pure = true)
    @Autowired
    public UserDetailServiceImp(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " not found");
        } else {
            return user;
        }
    }
}
