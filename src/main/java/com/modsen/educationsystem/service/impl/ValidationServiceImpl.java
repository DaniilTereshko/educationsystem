package com.modsen.educationsystem.service.impl;

import com.modsen.educationsystem.common.exception.InvalidRequestException;
import com.modsen.educationsystem.service.ValidationService;
import org.springframework.stereotype.Service;

import static com.modsen.educationsystem.common.util.ExceptionMessage.INVALID_REQUEST_PAGE_EMPTY_EXCEPTION;
import static com.modsen.educationsystem.common.util.ExceptionMessage.INVALID_REQUEST_SIZE_VALUE_EXCEPTION;

@Service
public class ValidationServiceImpl implements ValidationService {

    public void validatePaginationParams(int page, int size) {
        if (page < 0) {
            throw new InvalidRequestException(INVALID_REQUEST_PAGE_EMPTY_EXCEPTION);
        }
        if (size < 1 || size > 100) {
            throw new InvalidRequestException(INVALID_REQUEST_SIZE_VALUE_EXCEPTION);
        }
    }
}
