package com.idea.readingisgood.mapper;

import org.springframework.stereotype.Component;

import com.idea.readingisgood.dto.BookDTO;
import com.idea.readingisgood.entity.Book;

@Component
public class BookMapper implements BaseMapper<BookDTO, Book> {
    @Override
    public BookDTO entityToDTO(Book entity) {
        return BookDTO.builder()
            .id(entity.getId())
            .name(entity.getName())
            .author(entity.getAuthor())
            .publisher(entity.getPublisher())
            .publishDate(entity.getPublishDate())
            .genre(entity.getGenre())
            .build();
    }

    @Override
    public Book dtoToEntity(BookDTO dto) {
        return Book.builder()
            .id(dto.getId())
            .name(dto.getName())
            .author(dto.getAuthor())
            .publisher(dto.getPublisher())
            .publishDate(dto.getPublishDate())
            .genre(dto.getGenre())
            .build();
    }
}
