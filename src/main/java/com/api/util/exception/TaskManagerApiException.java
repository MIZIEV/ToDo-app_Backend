package com.api.util.exception;

import org.springframework.http.HttpStatus;

public class TaskManagerApiException extends RuntimeException{

    private final String message;
    private final HttpStatus httpStatus;

    public TaskManagerApiException(String message,HttpStatus httpStatus){
        this.message=message;
        this.httpStatus=httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}