package com.ds.assignment_1_user_management.services;

import com.ds.assignment_1_user_management.dtos.UserRequestDto;
import com.ds.assignment_1_user_management.dtos.UserDto;
import com.ds.assignment_1_user_management.dtos.UserUpdateDto;
import com.ds.assignment_1_user_management.entities.Role;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto getById(UUID id);

    List<UserDto> getAllByName(String name);

    UserDto getByUsername(String username);

    List<UserDto> getAll();

    List<UserDto> getAllByRole(Role role);

    UserDto save(UserRequestDto userRequestDto);

    UserDto update(UserUpdateDto userUpdateDto);

    void deleteById(UUID id);
}
