package com.hr.homework.controller.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handles exceptions in project scope - when an exception is thrown in controller classes, this class recognizes the exception type and
 * does some logic to handle it. In this case, it returns the appropriate error message and HTTP status code.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse requestBodyIsMissing() {

        return new ApiErrorResponse(ErrorCode.EMPTY_REQUEST_BODY.getDescription(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse noRowsAffectedAfterUpdateOrDelete() {

        return new ApiErrorResponse(ErrorCode.ENTRY_NOT_FOUND.getDescription(), HttpStatus.BAD_REQUEST);

    }

}
