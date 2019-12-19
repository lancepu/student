package com.company.studentcrud.controller;

import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<VndErrors> illegalArgumentException(IllegalArgumentException e, WebRequest webRequest) {
        VndErrors vndErrors = new VndErrors(webRequest.toString(), e.getMessage());
        return new ResponseEntity<>(vndErrors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
