package com.hr.homework.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ApiErrorResponse {

    private String errorDescription;
    private HttpStatus httpStatus;

}
