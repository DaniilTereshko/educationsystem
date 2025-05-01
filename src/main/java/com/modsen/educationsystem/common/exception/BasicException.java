package com.modsen.educationsystem.common.exception;

import lombok.Getter;

@Getter
public abstract class BasicException extends RuntimeException {

    private final Object[] args;

    public BasicException(String code, Object... args) {
        super(code);
        this.args = args;
    }

}