package com.idea.readingisgood.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class StockDTO extends BaseDTO {

    @NotNull
    private String bookId;

    @NotEmpty
    private Integer piece;

    public StockDTO() {
    }

    public StockDTO(@NotNull String bookId, @NotEmpty Integer piece) {
        this.bookId = bookId;
        this.piece = piece;
    }

    public StockDTO(String id, @NotNull String bookId, @NotEmpty Integer piece) {
        super(id);
        this.bookId = bookId;
        this.piece = piece;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }
}
