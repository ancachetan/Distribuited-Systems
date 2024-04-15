package com.ds.assignment_1_user_management.config;

import com.ds.assignment_1_user_management.entities.Role;
import com.ds.assignment_1_user_management.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_URL = {"/users/register", "/users/login"};
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/users/getById/**").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers("/users/getByName/**").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers("/users/getByUsername/**").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers("/users/getAll/**").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers("/users/getAllByRole/CLIENT").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers("/users/getAllByRole/ADMIN").hasAnyAuthority(Role.ADMIN.name(), Role.CLIENT.name())
                                .requestMatchers("/users/save").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers("/users/update").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers("/users/delete/**").hasAnyAuthority(Role.ADMIN.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(httpSecurityCorsConfigurer ->
                        httpSecurityCorsConfigurer.configure(http)
                );
        return http.build();
    }
}
