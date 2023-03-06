package com.example.cms;

import com.example.cms.domain.SignUpForm;
import com.example.cms.service.SignUpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;

@SpringBootTest
public class SignUpCustomerServiceTest {

    @Autowired
    private SignUpService service;

    @Test
    void singUp(){
        SignUpForm form = SignUpForm.builder()
                .name("name")
                .birth(LocalDate.now())
                .email("abc@gmail.com")
                .password("1")
                .phone("01000000000")
                .build();
        Assert.notNull(service.signUp(form).getId());
    }
}
