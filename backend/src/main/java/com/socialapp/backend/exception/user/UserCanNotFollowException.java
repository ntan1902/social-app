package com.socialapp.backend.exception.user;

public class UserCanNotFollowException extends RuntimeException{
    public UserCanNotFollowException(String message) {
        super(message);
    }
}
