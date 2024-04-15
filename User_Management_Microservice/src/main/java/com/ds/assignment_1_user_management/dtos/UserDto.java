package com.ds.assignment_1_user_management.dtos;

import com.ds.assignment_1_user_management.entities.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String name;
    private String username;
    private String password;
    private Role role;
}
