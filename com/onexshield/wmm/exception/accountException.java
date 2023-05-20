package com.onexshield.wmm.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class accountException  extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {accountRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(accountRequestException e){
        apiException apiException = new apiException(
                e.getMessage(),
                e.getHttpStatus(),
                ZonedDateTime.now(ZoneId.of("GMT"))
        );
        return new ResponseEntity<>(apiException, e.getHttpStatus());
    }
}
