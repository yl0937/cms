package com.example.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler({
            CustomException.class
    })
    public ResponseEntity<ExceptionResopnse> customResuestException(final CustomException c){
        log.warn("api Exception",c.getErrorCode());
        return ResponseEntity.badRequest().body(new ExceptionResopnse(c.getMessage(),c.getErrorCode()));
    }


    @Getter
    @ToString
    @AllArgsConstructor
    public static class ExceptionResopnse{
        private String message;
        private ErrorCode errorCode;
    }
}
