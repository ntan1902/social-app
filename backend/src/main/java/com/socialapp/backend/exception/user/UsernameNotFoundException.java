package com.socialapp.backend.exception.user;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
