package com.socialapp.backend.exception.handler;

import com.socialapp.backend.exception.api.ApiException;
import com.socialapp.backend.exception.user.UserCanNotFollowException;
import com.socialapp.backend.exception.user.UserIdNotFoundException;
import com.socialapp.backend.exception.user.UsernameNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
@Log4j2
public class ApiExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        // 1. Create payload containing exception details.
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        // 2. Return response entity
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        // 1. Create payload containing exception details.
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        // 2. Return response entity
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {UserIdNotFoundException.class})
    public ResponseEntity<Object> handleUserIdNotFoundException(UserIdNotFoundException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        // 1. Create payload containing exception details.
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        // 2. Return response entity
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {UserCanNotFollowException.class})
    public ResponseEntity<Object> handleUserCanNotFollowException(UserCanNotFollowException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        // 1. Create payload containing exception details.
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        // 2. Return response entity
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
