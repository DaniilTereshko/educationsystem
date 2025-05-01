package com.modsen.educationsystem.common.exception;

import java.util.function.Supplier;

public class ResourceAlreadyExistsException extends BasicException {

    public ResourceAlreadyExistsException(String code) {
        super(code);
    }

    public ResourceAlreadyExistsException(String code, Object... args) {
        super(code, args);
    }

    public static Supplier<ResourceAlreadyExistsException> resourceAlreadyExistsException(String code) {
        return () -> new ResourceAlreadyExistsException(code);
    }

    public static Supplier<ResourceAlreadyExistsException> resourceAlreadyExistsException(String code, Object... args) {
        return () -> new ResourceAlreadyExistsException(code, args);
    }

}