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
    public static final String SERVER_INTERNAL_ERROR = "error.server.internal";
    public static final String REQUEST_NOT_READABLE = "error.request.not_readable";

}