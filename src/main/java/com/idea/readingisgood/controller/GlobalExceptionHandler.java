package com.idea.readingisgood.controller;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.idea.readingisgood.entity.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> noSuchElementException(Exception exception, WebRequest request) {
        return ResponseEntity.badRequest()
            .body(ErrorResponse.builder()
                .error(exception.getLocalizedMessage())
                .message(exception.getStackTrace()[0].toString())
                .build());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception exception, WebRequest request) {
        return ResponseEntity.badRequest()
            .body(ErrorResponse.builder()
                .error(exception.getLocalizedMessage())
                .message(exception.getStackTrace()[0].toString())
                .build());

    }

}
