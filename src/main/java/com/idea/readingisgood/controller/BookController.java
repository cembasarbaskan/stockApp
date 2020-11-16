package com.idea.readingisgood.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idea.readingisgood.domain.response.BaseResponse;
import com.idea.readingisgood.dto.BookDTO;
import com.idea.readingisgood.service.BookService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "book")
@Validated
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getBook(@RequestParam String bookId) {
        return bookService.fetchOneById(bookId);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<BaseResponse> getListBook(
        @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
        @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        return bookService.fetchAll(startIndex, size);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createBook(@RequestBody @Valid BookDTO bookDTO) {
        return bookService.save(bookDTO);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateBook(@RequestBody @Valid BookDTO bookDTO) {
        return bookService.update(bookDTO);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteOrder(@PathVariable String bookId) {
        return bookService.deleteOneById(bookId);
    }
}
