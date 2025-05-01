package com.modsen.educationsystem.common.exception;

import java.util.function.Supplier;

public class UnauthorizedAccessException extends BasicException {

    public UnauthorizedAccessException(String code) {
        super(code);
    }

    public UnauthorizedAccessException(String code, Object... args) {
        super(code, args);
    }

    public static Supplier<UnauthorizedAccessException> unauthorizedAccessException(String code) {
        return () -> new UnauthorizedAccessException(code);
    }

    public static Supplier<UnauthorizedAccessException> unauthorizedAccessException(String code, Object... args) {
        return () -> new UnauthorizedAccessException(code, args);
    }

}