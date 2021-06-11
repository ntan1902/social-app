package com.socialapp.backend.exception.handler;

import com.socialapp.backend.exception.api.ApiException;
import com.socialapp.backend.exception.user.UserCanNotFollowException;
import com.socialapp.backend.exception.user.UserIdNotFoundException;
import com.socialapp.backend.exception.user.UsernameNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
@Log4j2
public class ApiExceptionHandler {
    @NotNull
    private ResponseEntity<Object> getResponse(HttpStatus httpStatus, String message) {
        // 1. Create payload containing exception details.
        ApiException apiException = new ApiException(
                message,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        // 2. Return response entity
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return getResponse(httpStatus, exception.getMessage());

    }


    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        return getResponse(httpStatus, exception.getMessage());
    }

    @ExceptionHandler(value = {UserIdNotFoundException.class})
    public ResponseEntity<Object> handleUserIdNotFoundException(UserIdNotFoundException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        return getResponse(httpStatus, exception.getMessage());
    }

    @ExceptionHandler(value = {UserCanNotFollowException.class})
    public ResponseEntity<Object> handleUserCanNotFollowException(UserCanNotFollowException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        return getResponse(httpStatus, exception.getMessage());
    }
}
