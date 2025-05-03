package com.modsen.educationsystem.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionMessage {

    public static final String RESOURCE_ALREADY_EXISTS = "resource.already.exists";
    public static final String INVALID_CREDENTIALS = "invalid.credentials";
    public static final String INVALID_AUTH_HEADER = "invalid.auth.header";
    public static final String INVALID_REFRESH_TOKEN = "invalid.refresh.token";
    public static final String RESOURCE_NOT_FOUND_BY_ATTRIBUTE = "resource.not_found_by_attribute";
    public static final String RESOURCE_NOT_FOUND_BY_ID = "resource.not_found_by_id";
    public static final String INVALID_REQUEST_PAGE_EMPTY_EXCEPTION = "invalid.request.page.empty";
    public static final String INVALID_REQUEST_SIZE_VALUE_EXCEPTION = "invalid.request.size.size_value";

    public static final String QUESTION_TEXT_ANSWER_REQUIREMENTS = "question.text.answer_requirements";
    public static final String QUESTION_TEXT_ANSWER_MIN_ELEMENTS_REQUIREMENTS = "question.text.answer_requirements.min_elements";
    public static final String QUESTION_SINGLE_CHOICE_REQUIREMENTS = "question.single.choice.requirements";
    public static final String QUESTION_SINGLE_CHOICE_MIN_ELEMENTS_REQUIREMENTS = "question.single.choice.requirements.min_elements";
    public static final String QUESTION_MULTIPLE_CHOICE_REQUIREMENTS = "question.multiple.choice.requirements";
    public static final String QUESTION_MULTIPLE_CHOICE_MIN_ELEMENTS_REQUIREMENTS = "question.multiple.choice.requirements.min_elements";

    public static final String SERVER_INTERNAL_ERROR = "error.server.internal";
    public static final String REQUEST_NOT_READABLE = "error.request.not_readable";

    public static final String NO_MORE_ATTEMPTS = "attempts.exhausted";
    public static final String ACTIVE_ATTEMPT_EXISTS = "attempt.active.exists";
    public static final String ACTIVE_ATTEMPT_NOT_EXISTS = "attempt.active.not_exists";
    public static final String TIME_LIMIT_EXCEEDED = "attempt.time.limit.exceeded";

}