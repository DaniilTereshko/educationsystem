package com.modsen.educationsystem.security;

import com.modsen.educationsystem.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AnonymousConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

    public static final String AUTH_API_URI = "/api/v*/auth/**";
    public static final String COURSE_API_URI = "/api/v*/courses/**";
    public static final String OPEN_API_URI = "/swagger-ui/**";
    public static final String DOC_PATH_URI = "/v3/api-docs/**";
    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(configure -> configure.authenticationEntryPoint(
                                (request, response, ex) -> {
                                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                                })
                        .accessDeniedHandler(
                                (request, response, ex) -> {
                                    response.setStatus(HttpStatus.FORBIDDEN.value());
                                }
                        )
                )
                .authorizeHttpRequests(configure -> configure.requestMatchers(AUTH_API_URI, OPEN_API_URI, DOC_PATH_URI)
                        .permitAll()
                        .requestMatchers(COURSE_API_URI).hasAuthority("MANAGER")
                        .anyRequest()
                        .authenticated())
                .anonymous(AnonymousConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}