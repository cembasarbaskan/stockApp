package com.idea.readingisgood.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.idea.readingisgood.domain.enums.EnumGenre;

public class BookDTO extends BaseDTO {

    @NotEmpty
    @Size(min = 2)
    private String name;

    @NotEmpty
    @Size(min = 2)
    private String author;

    @NotEmpty
    @Size(min = 2)
    private String publisher;

    private Date publishDate;

    @NotEmpty
    private List<EnumGenre> genre;

    public BookDTO() {
    }

    private BookDTO(String name, String author, String publisher, Date publishDate, List<EnumGenre> genre) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.genre = genre;
    }

    private BookDTO(String id, @NotEmpty @Size(min = 2) String name, @NotEmpty @Size(min = 2) String author,
        @NotEmpty @Size(min = 2) String publisher, Date publishDate, @NotEmpty List<EnumGenre> genre) {
        super(id);
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.genre = genre;
    }

    public static BookDTO.BookDTOBuilder builder() {
        return new BookDTO.BookDTOBuilder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public List<EnumGenre> getGenre() {
        return genre;
    }

    public void setGenre(List<EnumGenre> genre) {
        this.genre = genre;
    }

    public static class BookDTOBuilder {
        private String id;
        private String name;
        private String author;
        private String publisher;
        private Date publishDate;
        private List<EnumGenre> genre;
        private Integer stockSize;

        public BookDTOBuilder() {
        }

        public BookDTO.BookDTOBuilder id(String id) {
            this.id = id;
            return this;
        }

        public BookDTO.BookDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BookDTO.BookDTOBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookDTO.BookDTOBuilder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public BookDTO.BookDTOBuilder publishDate(Date publishDate) {
            this.publishDate = publishDate;
            return this;
        }

        public BookDTO.BookDTOBuilder genre(List<EnumGenre> genre) {
            this.genre = genre;
            return this;
        }

        public BookDTO build() {
            return new BookDTO(this.id, this.name, this.author, this.publisher, this.publishDate, this.genre);
        }
    }
}
