package com.idea.readingisgood.dto;

public class BaseDTO {
    private String id;

    public BaseDTO() {
    }

    public BaseDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
