package com.modsen.educationsystem.service.impl;

import com.modsen.educationsystem.common.exception.ResourceAlreadyExistsException;
import com.modsen.educationsystem.common.exception.UnauthorizedAccessException;
import com.modsen.educationsystem.model.User;
import com.modsen.educationsystem.security.jwt.JwtType;
import com.modsen.educationsystem.security.service.JwtService;
import com.modsen.educationsystem.service.AuthService;
import com.modsen.educationsystem.service.UserService;
import com.modsen.educationsystem.web.request.LoginRequest;
import com.modsen.educationsystem.web.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.modsen.educationsystem.common.util.ExceptionMessage.INVALID_AUTH_HEADER;
import static com.modsen.educationsystem.common.util.ExceptionMessage.INVALID_CREDENTIALS;
import static com.modsen.educationsystem.common.util.ExceptionMessage.INVALID_REFRESH_TOKEN;
import static com.modsen.educationsystem.common.util.ExceptionMessage.RESOURCE_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(User user) {
        if (userService.existsByUsername(user.getUsername())) {
            throw new ResourceAlreadyExistsException(RESOURCE_ALREADY_EXISTS);
        }
        if (userService.existsByEmail(user.getEmail())) {
            throw new ResourceAlreadyExistsException(RESOURCE_ALREADY_EXISTS);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.USER);

        userService.create(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        final var username = request.getUsername();
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    username,
                    request.getPassword()
            ));
        } catch (AuthenticationException ex) {
            throw new UnauthorizedAccessException(INVALID_CREDENTIALS);
        }
        var user = userService.getByUsername(username);

        return LoginResponse.builder()
                .access(jwtService.generateToken(username, JwtType.ACCESS, user.getEmail(), user.getRole()))
                .refresh(jwtService.generateToken(username, JwtType.REFRESH, user.getEmail(), user.getRole()))
                .build();
    }

    @Override
    public LoginResponse refreshToken(HttpServletRequest req, HttpServletResponse resp) {
        final var authHeader = req.getHeader(HttpHeaders.AUTHORIZATION);

        if (!jwtService.isValidaAuthHeader(authHeader)) {
            throw new UnauthorizedAccessException(INVALID_AUTH_HEADER);
        }

        final var jwt = authHeader.substring(7);

        final var username = jwtService.getSubject(jwt);
        userService.existsByUsername(username);
        var user = userService.getByUsername(username);

        if (!jwtService.isValid(jwt, JwtType.REFRESH)) {
            throw new UnauthorizedAccessException(INVALID_REFRESH_TOKEN);
        }

        return LoginResponse.builder()
                .access(jwtService.generateToken(username, JwtType.ACCESS, user.getEmail(), user.getRole()))
                .refresh(jwtService.generateToken(username, JwtType.REFRESH, user.getEmail(), user.getRole()))
                .build();
    }

}