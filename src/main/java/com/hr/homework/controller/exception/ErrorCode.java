package com.hr.homework.controller.exception;

/**
 * Contains constants used for exception handling.
 */
public enum ErrorCode {

    EMPTY_REQUEST_BODY("Required request body is missing."),
    ENTRY_NOT_FOUND("Entry you tried to modify is not found.");

    private final String description;

    ErrorCode(String description){
        this.description=description;
    }

    public String getDescription() {
        return description;
    }
}
