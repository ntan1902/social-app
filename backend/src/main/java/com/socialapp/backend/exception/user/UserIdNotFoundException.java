package com.socialapp.backend.exception.user;

public class UserIdNotFoundException extends RuntimeException{
    public UserIdNotFoundException(String message) {
        super(message);
    }
}
