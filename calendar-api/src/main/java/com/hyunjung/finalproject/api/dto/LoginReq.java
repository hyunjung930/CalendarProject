package com.hyunjung.finalproject.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class LoginReq {

    @NotBlank
    private final String email;
    @Size(min = 6, message = "6자리 이상 입력해 주세요")
    @NotBlank
    private final String password;

}
