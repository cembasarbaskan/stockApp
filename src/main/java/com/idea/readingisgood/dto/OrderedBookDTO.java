package com.idea.readingisgood.dto;

import com.idea.readingisgood.entity.BaseEntity;
import com.idea.readingisgood.entity.Book;
import com.idea.readingisgood.entity.Order;

public class OrderedBookDTO extends BaseEntity {

    private Book book;
    private Order order;
    private Integer piece;

    public OrderedBookDTO() {
    }

    public OrderedBookDTO(Book book, Order order, Integer piece) {
        this.book = book;
        this.order = order;
        this.piece = piece;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }

}
