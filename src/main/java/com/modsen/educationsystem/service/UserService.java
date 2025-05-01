package com.modsen.educationsystem.service;

import com.modsen.educationsystem.model.User;

import java.util.UUID;

public interface UserService {

    boolean existsByUsername(String username);

    User getByUsername(String username);

    void create(User user);

    User getOrThrow(final UUID id);
}