package com.hyunjung.finalproject.core.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Table(name= "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String name;
    public String email;
    private String password;
    private LocalDateTime birthday;
    private LocalDateTime createdAt = LocalDateTime.now();

    public User(String name, String email, String password, LocalDateTime birthday) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }
}
