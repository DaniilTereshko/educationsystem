package com.modsen.educationsystem.security.service;

import com.modsen.educationsystem.security.jwt.JwtProperties;
import com.modsen.educationsystem.security.jwt.JwtType;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.startsWith;

@Component
public class JwtService {

    private static final String TOKEN_TYPE_KEY = "type";
    private final JwtProperties jwtProperties;
    private SecretKey secretKey;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;

    }

    @PostConstruct
    public void init() {
        this.secretKey = null;
    }

    public boolean isValid(final String jwt, final JwtType jwtType) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                    .build()
                    .parseSignedClaims(jwt);

            return !isExpired(jwt) && (jwtType.name().equals(getType(jwt)));
        } catch (JwtException ex) {
            return false;
        }
    }

    public String generateToken(final String username, final JwtType jwtType) {
        return switch (jwtType) {
            case ACCESS -> generateToken(username, jwtType, jwtProperties.getAccessDuration());
            case REFRESH -> generateToken(username, jwtType, jwtProperties.getRefreshDuration());
        };
    }

    public String getSubject(final String jwt) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getSubject();

    }

    public boolean isValidaAuthHeader(final String authHeader) {
        return !(isEmpty(authHeader) || !startsWith(authHeader, "Bearer "));
    }

    private String getType(final String jwt) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .get(TOKEN_TYPE_KEY, String.class);
    }

    private String generateToken(final String username, final JwtType jwtType, final Duration duration) {
        var issuedAt = new Date();
        return Jwts.builder()
                .subject(username)
                .claim(TOKEN_TYPE_KEY, jwtType.name())
                .issuedAt(issuedAt)
                .expiration(new Date(issuedAt.getTime() + duration.getSeconds() * 1000))
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                .compact();
    }

    private boolean isExpired(final String jwt) {
        try {
            var claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                    .build()
                    .parseSignedClaims(jwt);

            return claims.getPayload()
                    .getExpiration()
                    .before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

}