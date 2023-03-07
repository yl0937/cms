package com.example.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorCode{

    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST,"이미 가입됨"),

    ALREADY_VERIFY_USER(HttpStatus.BAD_REQUEST,"이미 인증됨"),

    EXPIRE_CODE(HttpStatus.BAD_REQUEST,"인증 시간 만료"),

    WRONG_VERIFICATION(HttpStatus.BAD_REQUEST,"잘못된 인증 시도 입니다."),

    NOT_FOUND_USER(HttpStatus.BAD_REQUEST,"일치하는 회원이 없습니다.");
    private final HttpStatus httpStatus;
    private String detail;
}
