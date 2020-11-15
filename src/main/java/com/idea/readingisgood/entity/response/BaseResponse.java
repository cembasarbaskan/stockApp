package com.idea.readingisgood.entity.response;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class BaseResponse {
    private Date timestamp;
    private HttpStatus status;
    private String path;

    public BaseResponse(Date timestamp, HttpStatus status, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
