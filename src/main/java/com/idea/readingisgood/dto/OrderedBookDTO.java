package com.idea.readingisgood.dto;

import com.idea.readingisgood.entity.BaseEntity;
import com.idea.readingisgood.entity.Book;
import com.idea.readingisgood.entity.Order;

public class OrderedBookDTO extends BaseEntity {

    private BookDTO book;
    private OrderDTO order;
    private Integer piece;

    public OrderedBookDTO() {
    }

    public OrderedBookDTO(BookDTO book, OrderDTO order, Integer piece) {
        this.book = book;
        this.order = order;
        this.piece = piece;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }
}
