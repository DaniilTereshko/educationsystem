package com.modsen.educationsystem.service.impl;

import com.modsen.educationsystem.model.Test;
import com.modsen.educationsystem.model.User;
import com.modsen.educationsystem.model.id.UserCourseId;
import com.modsen.educationsystem.repository.CourseRepository;
import com.modsen.educationsystem.repository.TestRepository;
import com.modsen.educationsystem.repository.UserCourseRepository;
import com.modsen.educationsystem.repository.UserRepository;
import com.modsen.educationsystem.security.service.UserHolder;
import com.modsen.educationsystem.service.AuthValidationService;
import com.modsen.educationsystem.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("authValidationService")
@RequiredArgsConstructor
public class AuthValidationServiceImpl implements AuthValidationService {
    private final UserHolder userHolder;
    private final UserCourseRepository userCourseRepository;
    private final TestService testService;

    public boolean isManager() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority()
                        .equals("MANAGER"));
    }

    public boolean isSubscribedToCourse(Long id) {
        if (isManager()) return true;

        var userId = getCurrentUserId();
        return userCourseRepository.existsById(UserCourseId.of(userId, id));
    }

    public boolean isSubscribedToTest(Long id) {
        if (isManager()) return true;
        var test = testService.getOrThrow(id);

        return isSubscribedToCourse(test.getCourse().getId());
    }

    private UUID getCurrentUserId() {
        return userHolder.getUser().getId();
    }
}
