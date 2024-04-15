package com.ds.assignment_1_user_management.exceptions;

public class AlreadyExistingUsernameException extends RuntimeException {
    public AlreadyExistingUsernameException(String message) {
        super(message);
    }
}
