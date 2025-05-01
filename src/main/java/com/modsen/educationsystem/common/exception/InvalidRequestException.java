package com.modsen.educationsystem.common.exception;

import java.util.function.Supplier;

public class InvalidRequestException extends BasicException {

    public InvalidRequestException(String code) {
        super(code);
    }

    public InvalidRequestException(String code, Object... args) {
        super(code, args);
    }

    public static Supplier<InvalidRequestException> invalidRequestException(String code) {
        return () -> new InvalidRequestException(code);
    }

    public static Supplier<InvalidRequestException> invalidRequestException(String code, Object... args) {
        return () -> new InvalidRequestException(code, args);
    }

}