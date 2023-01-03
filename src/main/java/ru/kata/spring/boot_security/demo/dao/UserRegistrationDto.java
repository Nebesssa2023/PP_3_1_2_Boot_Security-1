package ru.kata.spring.boot_security.demo.dao;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class UserRegistrationDto {
    String name;
    String lastName;
    String email;
    String password;

    public UserRegistrationDto(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
