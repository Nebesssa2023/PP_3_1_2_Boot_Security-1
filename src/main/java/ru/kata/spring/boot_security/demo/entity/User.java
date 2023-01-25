package ru.kata.spring.boot_security.demo.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.*;

@EqualsAndHashCode
@Setter
@Getter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "users")
public class User implements Serializable, UserDetails {
    static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    String name;

    @Column(name = "last_name")
    @NotEmpty(message = "Lastname should not be empty")
    @Size(min = 2, max = 30, message = "Lastname should be between 2 and 30 characters")
    String lastName;

    @Column(name = "age")
    @Min(value = 1, message = "Age should be greater than 0")
    int age;

    @Column(name = "username", unique = true)
    @NotEmpty(message = "Username should not be empty")
    @Email(message = "This is insert, not email")
    String username;

    @Column(name = "password")
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 3, max = 200, message = "Password should be between 8 and 100 characters")
    String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "roles_id")})
    Set<Role> roles;

    public User(String name, String lastName, int age, String username, String password, Set<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public String getRoleString() {
        return roles.toString().substring(5);
    }
}
