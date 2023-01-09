package ru.kata.spring.boot_security.demo.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="roles")
public class Role implements Serializable, GrantedAuthority {
    static long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "role", unique = true, nullable = false)
    String role;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;

    public Role(String role) {
        super();
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }

}

