package ru.kata.spring.boot_security.demo.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "role", unique = true)
    String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    Set<User> users = new HashSet<>();

    public Role(String role) {
        super();
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
