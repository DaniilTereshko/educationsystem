package com.modsen.educationsystem.web.dto;

import com.modsen.educationsystem.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private UUID id;
    private String username;
    private String email;
    private User.Role role;

}