package com.socialapp.backend.exception.handler;

import com.socialapp.backend.exception.api.ApiException;
import com.socialapp.backend.exception.user.ApiResponseException;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;
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

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception exception) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return getResponse(httpStatus, "Unknown error");

    }

    @ExceptionHandler(value = {ApiResponseException.class})
    public ResponseEntity<Object> handleApiResponseException(ApiResponseException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return getResponse(httpStatus, exception.getMessage());

    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return getResponse(httpStatus, exception.getMessage());

    }

    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<Object> handleBindException(BindException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        return getResponse(httpStatus, exception.getMessage());
    }

    @ExceptionHandler(value = {DataAccessException.class})
    public ResponseEntity<Object> handleDataAccessException(DataAccessException exception) {
        log.error(exception.getMessage());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        return getResponse(httpStatus, "Oops!!! Something's wrong with your information");
    }
}
