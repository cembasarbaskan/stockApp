package com.idea.readingisgood.dto;

import com.idea.readingisgood.entity.BaseEntity;

public class OrderedBookDTO extends BaseEntity {
    private String bookId;
    private String orderId;
    private Integer piece;

    public OrderedBookDTO() {
    }

    public OrderedBookDTO(String bookId, String orderId, Integer piece) {
        this.bookId = bookId;
        this.orderId = orderId;
        this.piece = piece;
    }

    public OrderedBookDTO(String id, String bookId, String orderId, Integer piece) {
        super(id);
        this.bookId = bookId;
        this.orderId = orderId;
        this.piece = piece;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }
}
