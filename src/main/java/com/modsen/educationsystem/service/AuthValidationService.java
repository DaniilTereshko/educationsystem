package com.modsen.educationsystem.service;

public interface AuthValidationService {
    boolean isManager();

    boolean isSubscribedToCourse(Long courseId);

    boolean isSubscribedToTest(Long testId);
}