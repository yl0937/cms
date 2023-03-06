package com.example.cms.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;

public class FeignConfig {

    @Qualifier(value = "mailgun")
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("api","63721b6e5a68cf8c3671ecce556e9d85-15b35dee-92e37e4b");
    }
}
