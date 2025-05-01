package com.modsen.educationsystem.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationMessage {

    /* RegistrationRequest  */
    public static final String EMAIL_INCORRECT_LENGTH = "validation.registration.email.incorrect_length";
    public static final String EMAIL_FIELD_IS_EMPTY = "validation.registration.email.empty";
    public static final String USERNAME_INCORRECT_LENGTH = "validation.registration.username.incorrect_length";
    public static final String USERNAME_FIELD_IS_EMPTY = "validation.registration.username.empty";
    public static final String PASSWORD_INCORRECT_LENGTH = "validation.registration.password.incorrect_length";
    public static final String PASSWORD_FIELD_IS_EMPTY = "validation.registration.password.empty";

    /* LoginRequest */
    public static final String LOGIN_USERNAME_EMPTY = "validation.login.username.empty";
    public static final String LOGIN_PASSWORD_EMPTY = "validation.login.password.empty"; /* LoginRequest */

    /* CourseRequest */
    public static final String COURSE_TITLE_EMPTY = "validation.course.title.empty";
    public static final String COURSE_DESCRIPTION_EMPTY = "validation.course.description.empty";
    public static final String COURSE_DESCRIPTION_INCORRECT_LENGTH = "validation.course.description.incorrect_length";
    public static final String COURSE_TITLE_INCORRECT_LENGTH = "validation.course.title.incorrect_length";

}