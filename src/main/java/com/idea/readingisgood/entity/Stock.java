package com.idea.readingisgood.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "STOCK")
public class Stock extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "book_id", unique = true, updatable = false, nullable = false)
    private Book book;

    @NotNull
    @Column(name = "piece")
    private Integer piece;

    public Stock() {
    }

    public Stock(Book book, Integer piece) {
        this.book = book;
        this.piece = piece;
    }

    public Stock(String id, Book book, @NotNull Integer piece) {
        super(id);
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
        if (piece == null) {
            piece = 0;
        }
        this.piece = piece;
    }
}
