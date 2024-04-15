package com.ds.assignment_1_user_management.security;

import com.ds.assignment_1_user_management.dtos.AuthenticationRequest;
import com.ds.assignment_1_user_management.dtos.AuthenticationResponse;
import com.ds.assignment_1_user_management.dtos.UserRequestDto;
import com.ds.assignment_1_user_management.entities.User;
import com.ds.assignment_1_user_management.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public AuthenticationResponse register(UserRequestDto userRequestDto) {
        var user = User.builder()
                .name(userRequestDto.getName())
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .role(userRequestDto.getRole())
                .build();
        userService.save(modelMapper.map(user, UserRequestDto.class));
        var token = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword())
        );
        var userDto = userService.getByUsername(request.getUsername());
        var user = modelMapper.map(userDto, User.class);
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }
}
