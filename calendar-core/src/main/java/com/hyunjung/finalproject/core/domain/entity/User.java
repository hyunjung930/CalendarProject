package com.hyunjung.finalproject.core.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Table(name= "users")
@Entity
public class User extends BaseEntity{

    public String name;
    public String email;
    private String password;
    private LocalDateTime birthday;

    public User(String name, String email, String password, LocalDateTime birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }
}
