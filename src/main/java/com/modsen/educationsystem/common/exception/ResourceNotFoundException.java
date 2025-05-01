package com.modsen.educationsystem.common.exception;


import java.util.function.Supplier;

public class ResourceNotFoundException extends BasicException {

    public ResourceNotFoundException(String code) {
        super(code);
    }

    public ResourceNotFoundException(String code, Object... args) {
        super(code, args);
    }

    public static Supplier<ResourceNotFoundException> resourceNotFoundException(String code) {
        return () -> new ResourceNotFoundException(code);
    }

    public static Supplier<ResourceNotFoundException> resourceNotFoundException(String code, Object... args) {
        return () -> new ResourceNotFoundException(code, args);
    }

}