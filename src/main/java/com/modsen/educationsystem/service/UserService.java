package com.modsen.educationsystem.service;

import com.modsen.educationsystem.model.User;

public interface UserService {

    boolean existsByUsername(String username);

    User getByUsername(String username);

    void create(User user);

}