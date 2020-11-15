package com.idea.readingisgood.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.idea.readingisgood.entity.Book;

public class StockDTO extends BaseDTO {

    @NotNull
    private Book book;

    @NotEmpty
    private Integer piece;

    public StockDTO() {
    }

    public StockDTO(Book book, Integer piece) {
        this.book = book;
        this.piece = piece;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }
}
