package com.modsen.educationsystem.service.impl;

import com.modsen.educationsystem.model.Course;
import com.modsen.educationsystem.model.User;
import com.modsen.educationsystem.repository.UserRepository;
import com.modsen.educationsystem.service.UserService;
import com.modsen.educationsystem.web.dto.PageDto;
import com.modsen.educationsystem.web.dto.UserDto;
import com.modsen.educationsystem.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.modsen.educationsystem.common.exception.ResourceNotFoundException.resourceNotFoundException;
import static com.modsen.educationsystem.common.util.ExceptionMessage.RESOURCE_NOT_FOUND_BY_ATTRIBUTE;
import static com.modsen.educationsystem.common.util.ExceptionMessage.RESOURCE_NOT_FOUND_BY_ID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(resourceNotFoundException(RESOURCE_NOT_FOUND_BY_ATTRIBUTE, User.class.getSimpleName(), "username", username));
    }

    @Override
    public void create(User user) {
        repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getOrThrow(final UUID id) {
        return repository.findById(id)
                .orElseThrow(resourceNotFoundException(RESOURCE_NOT_FOUND_BY_ID, id));
    }

    @Override
    public PageDto<UserDto> get(int page, int size) {
        var userDtos = repository.findAll(PageRequest.of(page, size))
                .map(userMapper::toDto);
        return new PageDto<>(userDtos);
    }
}