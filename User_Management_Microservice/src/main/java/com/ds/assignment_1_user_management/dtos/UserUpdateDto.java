package com.ds.assignment_1_user_management.dtos;

import com.ds.assignment_1_user_management.utils.ValidationMessages;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    private UUID id;
    private String name;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]{2,5}.[A-Za-z]{2,4}$", message = ValidationMessages.USERNAME_VALIDATION_PATTERN_MESSAGE)
    private String username;
    @Pattern(regexp = "^[A-Z][A-Za-z0-9+_.-]{5,10}$", message = ValidationMessages.PASSWORD_VALIDATION_PATTERN_MESSAGE)
    private String password;
}
