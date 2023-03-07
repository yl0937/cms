package com.example.cms.application;

import com.example.cms.SendMailForm;
import com.example.cms.client.mailgun.MailgunClient;
import com.example.cms.domain.SignUpForm;
import com.example.cms.domain.model.Customer;
import com.example.cms.exception.CustomException;
import com.example.cms.exception.ErrorCode;
import com.example.cms.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpApplication {
//    private final MailgunClient mailgunClient;
    private final SignUpService signUpService;

    public void customerVerify(String email,String code){
        signUpService.verifyEamil(email,code);
        return ;
    };

    public String customerSignUp(SignUpForm form){
        if(signUpService.isEmailExists(form.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        }else {
            Customer customer = signUpService.signUp(form);
            LocalDateTime now = LocalDateTime.now();

            String code =getRandomcode();
            SendMailForm sendMailForm = SendMailForm.builder().from("elim0937@naver.com")
                    .to(form.getEmail())
                    .subject("Verication Email!")
                    .text(getVerificationEmailBody(form.getEmail(),form.getName(),getRandomcode()))
                    .build();
//            mailgunClient.sendEmail(sendMailForm);
            signUpService.ChangeCustomerValidateEmail(customer.getId(),code);
        }
        return "회원 가입에 성공하였습니다.";
    }


    private String getRandomcode(){return RandomStringUtils.random(10,true,true);
    }
    private String getVerificationEmailBody(String email,String name,String code){
        StringBuilder builder = new StringBuilder();
        return builder.append("Hello").append(name).append("! Please Click Link for verifivation\n\n")
                .append("http://localhost:8081/signup/customer/verify/?email=")
                .append(email)
                .append("&code=")
                .append(code).toString();
    }


}
