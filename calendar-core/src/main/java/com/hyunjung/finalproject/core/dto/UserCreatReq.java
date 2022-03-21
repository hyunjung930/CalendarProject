package com.hyunjung.finalproject.core.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class UserCreatReq {

    private final String name;
    private final String email;
    private final String password;
    private final LocalDate birthday;
}
