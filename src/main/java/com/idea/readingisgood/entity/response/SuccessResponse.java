package com.idea.readingisgood.entity.response;

import java.util.Calendar;

import org.springframework.http.HttpStatus;

public class SuccessResponse<T> extends BaseResponse {
    private T data;
    private String message;

    private SuccessResponse(HttpStatus status, String path, T data, String message) {
        super(Calendar.getInstance().getTime(), status, path);
        this.data = data;
        this.message = message;
    }

    public static <T> SuccessResponse.SuccessResponseBuilder<T> builder() {
        return new SuccessResponse.SuccessResponseBuilder();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class SuccessResponseBuilder<T> {
        private T data;
        private String message;
        private HttpStatus status;
        private String path;

        public SuccessResponseBuilder() {
        }

        public SuccessResponse.SuccessResponseBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public SuccessResponse.SuccessResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public SuccessResponse.SuccessResponseBuilder<T> status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public SuccessResponse.SuccessResponseBuilder<T> path(String path) {
            this.path = path;
            return this;
        }

        public SuccessResponse<T> build() {
            return new SuccessResponse(this.status, this.path, this.data, this.message);
        }
    }
}
