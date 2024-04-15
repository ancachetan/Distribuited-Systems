package com.ds.assignment_1_user_management.controllers.impl;

import com.ds.assignment_1_user_management.controllers.UserController;
import com.ds.assignment_1_user_management.dtos.*;
import com.ds.assignment_1_user_management.entities.Role;
import com.ds.assignment_1_user_management.security.AuthenticationService;
import com.ds.assignment_1_user_management.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserControllerImpl(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity<AuthenticationResponse> register(UserRequestDto userRequestDto) {
        return new ResponseEntity<>(authenticationService.register(userRequestDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<>(authenticationService.login(authenticationRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> getById(UUID id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllByName(String name) {
        return new ResponseEntity<>(userService.getAllByName(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> getByUsername(String username) {
        return new ResponseEntity<>(userService.getByUsername(username), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllByRole(Role role) {
        return new ResponseEntity<>(userService.getAllByRole(role), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> save(UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.save(userRequestDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDto> update(UserUpdateDto userUpdateDto) {
        return new ResponseEntity<>(userService.update(userUpdateDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
