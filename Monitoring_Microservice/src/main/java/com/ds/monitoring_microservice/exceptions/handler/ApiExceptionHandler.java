package com.ds.monitoring_microservice.exceptions.handler;

import com.ds.monitoring_microservice.exceptions.DeviceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(DeviceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ApiExceptionResponse> handleNotFound(Exception exception) {
        return new ResponseEntity<>(getExceptionBody(exception.getMessage(), NOT_FOUND), NOT_FOUND);
    }

    private ApiExceptionResponse getExceptionBody(String message, HttpStatus status) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse();
        apiExceptionResponse.setCurrentTime(LocalDateTime.now());
        apiExceptionResponse.setMessage(message);
        apiExceptionResponse.setStatus(status.value());
        return apiExceptionResponse;
    }
}
