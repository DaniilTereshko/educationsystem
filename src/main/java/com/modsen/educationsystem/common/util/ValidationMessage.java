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

    /* TestRequest */
    public static final String TEST_TITLE_EMPTY = "validation.test.title.empty";
    public static final String TEST_TITLE_INCORRECT_LENGTH = "validation.test.title.incorrect_length";
    public static final String TEST_TIME_LIMIT_NEGATIVE = "validation.test.time_limit.negative";
    public static final String TEST_PASSING_SCORE_NEGATIVE = "validation.test.passing_score.negative";

    /* QuestionRequest */
    public static final String QUESTION_TEXT_EMPTY = "validation.question.text.empty";
    public static final String QUESTION_TYPE_EMPTY = "validation.question.type.empty";
    public static final String QUESTION_POINTS_INVALID = "validation.question.points.invalid";
    public static final String QUESTION_ANSWERS_EMPTY = "validation.question.answers.empty";
    public static final String QUESTION_ANSWERS_LIMIT = "validation.question.answers.max_size";

    /* AnswerRequest */
    public static final String ANSWER_TEXT_EMPTY = "validation.answer.text.empty";
    public static final String ANSWER_CORRECTNESS_EMPTY = "validation.answer.correctness.empty";

}