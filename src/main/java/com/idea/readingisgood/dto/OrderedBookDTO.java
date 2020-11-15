package com.idea.readingisgood.dto;

import com.idea.readingisgood.entity.BaseEntity;

public class OrderedBookDTO extends BaseEntity {
    private String bookId;
    private String orderCode;
    private Integer piece;

    public OrderedBookDTO() {
    }

    public OrderedBookDTO(String bookId, String orderCode, Integer piece) {
        this.bookId = bookId;
        this.orderCode = orderCode;
        this.piece = piece;
    }

    public OrderedBookDTO(String id, String bookId, String orderCode, Integer piece) {
        super(id);
        this.bookId = bookId;
        this.orderCode = orderCode;
        this.piece = piece;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }
}
