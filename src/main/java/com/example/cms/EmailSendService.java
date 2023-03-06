package com.example.cms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendService {
    private final MailgunClient mailgunClient;

    public void sendEmail(){
        SendMailForm form = SendMailForm.builder()
                .from("test.my.com")
                .to("elim0937@naver.com")
                .subject("test")
                .text("test")
                .build();
        mailgunClient.sendEmail(form);
    };
}
