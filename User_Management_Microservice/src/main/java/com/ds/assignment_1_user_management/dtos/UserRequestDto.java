package com.ds.assignment_1_user_management.dtos;

import com.ds.assignment_1_user_management.entities.Role;
import com.ds.assignment_1_user_management.utils.ValidationMessages;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = ValidationMessages.BLANK_NAME_VALIDATION_MESSAGE)
    private String name;
    @NotBlank(message = ValidationMessages.BLANK_USERNAME_VALIDATION_MESSAGE)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]{2,5}\\.[A-Za-z]{2,4}$", message = ValidationMessages.USERNAME_VALIDATION_PATTERN_MESSAGE)
    private String username;
    @NotBlank(message = ValidationMessages.BLANK_PASSWORD_VALIDATION_MESSAGE)
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[!@#$%^&*.]).{7,}$", message = ValidationMessages.PASSWORD_VALIDATION_PATTERN_MESSAGE)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
