package com.socialapp.backend.exception.user;

public class ApiResponseException extends RuntimeException{
    public ApiResponseException(String message) {
        super(message);
    }
}
