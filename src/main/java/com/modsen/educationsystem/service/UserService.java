package com.modsen.educationsystem.service;

import com.modsen.educationsystem.model.User;
import com.modsen.educationsystem.web.dto.PageDto;
import com.modsen.educationsystem.web.dto.UserDto;

import java.util.UUID;

public interface UserService {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    User getByUsername(String username);

    void create(User user);

    User getOrThrow(final UUID id);

    PageDto<UserDto> get(int page, int size);
}