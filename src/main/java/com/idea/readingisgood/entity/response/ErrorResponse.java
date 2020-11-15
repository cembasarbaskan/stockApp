package com.idea.readingisgood.entity.response;

import java.util.Calendar;

import org.springframework.http.HttpStatus;

public class ErrorResponse extends BaseResponse {
    private final String error;
    private final String message;

    private ErrorResponse(BaseResponseBuilder builder) {
        super(Calendar.getInstance().getTime(), builder.status, builder.path);
        this.error = builder.error;
        this.message = builder.message;
    }

    public static ErrorResponse.BaseResponseBuilder builder() {
        return new ErrorResponse.BaseResponseBuilder();
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public static class BaseResponseBuilder {
        private String error;
        private String message;
        private HttpStatus status;
        private String path;

        public BaseResponseBuilder error(String error) {
            this.error = error;
            return this;
        }

        public BaseResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public BaseResponseBuilder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public BaseResponseBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}
