package com.ds.assignment_1_user_management.controllers;

import com.ds.assignment_1_user_management.dtos.*;
import com.ds.assignment_1_user_management.entities.Role;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/users")
public interface UserController {
    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody UserRequestDto userRequestDto);

    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest);

    @GetMapping("/getById/{id}")
    ResponseEntity<UserDto> getById(@PathVariable UUID id);

    @GetMapping("/getAllByName/{name}")
    ResponseEntity<List<UserDto>> getAllByName(@PathVariable String name);

    @GetMapping("/getByUsername/{username}")
    ResponseEntity<UserDto> getByUsername(@PathVariable String username);

    @GetMapping("/getAll")
    ResponseEntity<List<UserDto>> getAll();

    @GetMapping("/getAllByRole/{role}")
    ResponseEntity<List<UserDto>> getAllByRole(@PathVariable Role role);

    @PostMapping("/save")
    ResponseEntity<UserDto> save(@Valid @RequestBody UserRequestDto userRequestDto);

    @PutMapping("/update")
    ResponseEntity<UserDto> update(@Valid @RequestBody UserUpdateDto userUpdateDto);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);
}
