package com.ds.assignment_1_user_management.exceptions.handler;

import com.ds.assignment_1_user_management.exceptions.AlreadyExistingUsernameException;
import com.ds.assignment_1_user_management.exceptions.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ds.assignment_1_user_management.utils.ValidationMessages.VALIDATION_EXCEPTION_MESSAGE;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ApiExceptionResponse> handleNotFound(Exception exception) {
        return new ResponseEntity<>(getExceptionBody(exception.getMessage(), NOT_FOUND), NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistingUsernameException.class)
    @ResponseStatus(CONFLICT)
    public ResponseEntity<ApiExceptionResponse> handleConflict(Exception exception) {
        return new ResponseEntity<>(getExceptionBody(exception.getMessage(), CONFLICT), CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, String> errors = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage
                ));
        ApiExceptionResponse apiExceptionResponse = getExceptionBody(VALIDATION_EXCEPTION_MESSAGE, BAD_REQUEST);
        apiExceptionResponse.setErrors(errors);
        return new ResponseEntity<>(apiExceptionResponse, BAD_REQUEST);
    }

    private ApiExceptionResponse getExceptionBody(String message, HttpStatus status) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse();
        apiExceptionResponse.setCurrentTime(LocalDateTime.now());
        apiExceptionResponse.setMessage(message);
        apiExceptionResponse.setStatus(status.value());
        return apiExceptionResponse;
    }
}
