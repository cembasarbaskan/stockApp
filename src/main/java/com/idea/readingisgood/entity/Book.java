package com.idea.readingisgood.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.idea.readingisgood.entity.enums.EnumGenre;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "author", "publisher" }) })
@Entity
public class Book extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publish_date")
    private Date publishDate;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = EnumGenre.class)
    @CollectionTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"))
    private List<EnumGenre> genre;

    public Book() {
    }

    private Book(String id, String name, String author, String publisher, Date publishDate, List<EnumGenre> genre) {
        super(id);
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.genre = genre;
    }

    public static Book.BookBuilder builder() {
        return new Book.BookBuilder();
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

    public static class BookBuilder {
        private String id;
        private String name;
        private String author;
        private String publisher;
        private Date publishDate;
        private List<EnumGenre> genre;

        public BookBuilder() {
        }

        public BookBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public BookBuilder publishDate(Date publishDate) {
            this.publishDate = publishDate;
            return this;
        }

        public BookBuilder genre(List<EnumGenre> genre) {
            this.genre = genre;
            return this;
        }

        public BookBuilder id(String id) {
            this.id = id;
            return this;
        }

        public Book build() {
            return new Book(this.id, this.name, this.author, this.publisher, this.publishDate, this.genre);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Book)) {
            return false;
        }

        Book other = (Book) object;

        if (id != null) {
            return id.equals(other.getId());
        } else {
            return this.name.equals(other.name) && this.author.equals(other.author) && this.publisher.equals(
                other.publisher);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
