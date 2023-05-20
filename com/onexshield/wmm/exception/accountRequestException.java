package com.onexshield.wmm.exception;

import org.springframework.http.HttpStatus;

public class accountRequestException extends RuntimeException{

    private HttpStatus httpStatus;
    public accountRequestException(String message) {
        super(message);
    }

    public accountRequestException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
