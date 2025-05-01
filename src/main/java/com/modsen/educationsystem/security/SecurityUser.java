package com.modsen.educationsystem.security;

import com.modsen.educationsystem.model.User;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Getter
public class SecurityUser implements UserDetails {

    private final UUID id;
    private final String username;
    private final String password;
    private final Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser(final User user) {
        this(user.getPassword(), user.getUsername(), user.getId());
        this.authorities.add(mapToGrantedAuthorities(user.getRole().getAuthority()));
    }

    public SecurityUser(String password, String username, UUID id) {

        this.password = password;
        this.username = username;
        this.id = id;
        this.authorities = new ArrayList<>();
    }

    private static SimpleGrantedAuthority mapToGrantedAuthorities(final String role) {
        return new SimpleGrantedAuthority(role);
    }
}