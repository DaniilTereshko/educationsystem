package com.modsen.educationsystem.service;

import com.modsen.educationsystem.model.User;
import com.modsen.educationsystem.web.dto.UserDto;
import com.modsen.educationsystem.web.request.LoginRequest;
import com.modsen.educationsystem.web.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    void register(User user);

    LoginResponse login(LoginRequest request);

    LoginResponse refreshToken(HttpServletRequest req, HttpServletResponse resp);

}