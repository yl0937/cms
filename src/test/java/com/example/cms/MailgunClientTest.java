package com.example.cms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest()
class MailgunClientTest {


//    private final MailgunClient mailgunClient;
//
//    MailgunClientTest(MailgunClient mailgunClient) {
//        this.mailgunClient = mailgunClient;
//    }

    @Test
    public void EmailTest(){
        System.out.println("test");
        SendMailForm form =  SendMailForm.builder().from("elim0937@naver.com")
                .to("elim0937@naver.com")
                .subject("test")
                .text("test").build();
        System.out.println(form);
//        mailgunClient.sendEmail(form);
//        Assert.notNull(mailgunClient.sendEmail(form).getBody());
    }

}