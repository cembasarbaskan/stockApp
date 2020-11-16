package com.idea.readingisgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.idea.readingisgood.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    @Query("select b from Book b where b.name = ?1 and b.author = ?2 and b.publisher = ?3")
    Book findByDetail(String name, String author, String publisher);
}
