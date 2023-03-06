package com.example.cms.domain;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SignUpForm {

    private String email;
    private String name;
    private String password;
    private LocalDate birth;
    private String phone;
}
