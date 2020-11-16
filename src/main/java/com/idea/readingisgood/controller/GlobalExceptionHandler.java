package com.idea.readingisgood.controller;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.idea.readingisgood.domain.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> noSuchElementException(Exception exception, WebRequest request) {
        logger.info("[noSuchElementException] ",exception);
        return ResponseEntity.badRequest()
            .body(ErrorResponse.builder()
                .error(exception.getLocalizedMessage())
                .message(exception.getStackTrace()[0].toString())
                .build());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception exception, WebRequest request) {
        logger.info("[exception] ",exception);
        return ResponseEntity.badRequest()
            .body(ErrorResponse.builder()
                .error(exception.getLocalizedMessage())
                .message(exception.getStackTrace()[0].toString())
                .build());

    }

}
