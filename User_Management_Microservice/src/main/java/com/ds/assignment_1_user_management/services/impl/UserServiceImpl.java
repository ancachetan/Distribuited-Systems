package com.ds.assignment_1_user_management.services.impl;

import com.ds.assignment_1_user_management.dtos.UserRequestDto;
import com.ds.assignment_1_user_management.dtos.UserDto;
import com.ds.assignment_1_user_management.dtos.UserUpdateDto;
import com.ds.assignment_1_user_management.entities.Role;
import com.ds.assignment_1_user_management.entities.User;
import com.ds.assignment_1_user_management.exceptions.AlreadyExistingUsernameException;
import com.ds.assignment_1_user_management.exceptions.UserNotFoundException;
import com.ds.assignment_1_user_management.repositories.UserRepository;
import com.ds.assignment_1_user_management.services.UserService;
import com.ds.assignment_1_user_management.utils.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto getById(UUID id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE.formatted(id)));
    }

    @Override
    public List<UserDto> getAllByName(String name) {
        return userRepository.findAllByNameContaining(name)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public UserDto getByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND_BY_USERNAME_EXCEPTION_MESSAGE.formatted(username)));
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public List<UserDto> getAllByRole(Role role) {
        return userRepository.findAllByRole(role)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public UserDto save(UserRequestDto userRequestDto) {
        userRepository.findByUsername(userRequestDto.getUsername())
                .ifPresent(s -> {
                    throw new AlreadyExistingUsernameException(ErrorMessages.ALREADY_EXISING_USERNAME_EXCEPTION_MESSAGE.formatted(userRequestDto.getUsername()));
                });
        User user = modelMapper.map(userRequestDto, User.class);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto update(UserUpdateDto userUpdateDto) {
        User foundUser = userRepository.findById(userUpdateDto.getId())
                .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE.formatted(userUpdateDto.getId())));
        if (userUpdateDto.getName() != null && !userUpdateDto.getName().isBlank()) {
            foundUser.setName(userUpdateDto.getName());
        }
        if (userUpdateDto.getUsername() != null && !userUpdateDto.getUsername().isBlank()) {
            userRepository.findByUsername(userUpdateDto.getUsername())
                    .ifPresent(s -> {
                        throw new UserNotFoundException(ErrorMessages.ALREADY_EXISING_USERNAME_EXCEPTION_MESSAGE.formatted(userUpdateDto.getUsername()));
                    });
            foundUser.setUsername(userUpdateDto.getUsername());
        }
        if (userUpdateDto.getPassword() != null  && !userUpdateDto.getPassword().isBlank()) {
            String encodedPassword = passwordEncoder.encode(userUpdateDto.getPassword());
            foundUser.setPassword(encodedPassword);
        }
        return modelMapper.map(userRepository.save(foundUser), UserDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE.formatted(id)));
        userRepository.deleteById(id);
    }
}
