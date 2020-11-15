package com.idea.readingisgood.exception;

public class IdCreationNotPermittedException extends RuntimeException {
    public IdCreationNotPermittedException(String message) {
        super(message);
    }

    public IdCreationNotPermittedException(String message, Throwable cause) {
        super(message, cause);
    }
}
