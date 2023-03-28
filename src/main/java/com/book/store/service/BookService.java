package com.book.store.service;

import com.book.store.model.Book;

import java.util.List;

public interface BookService {
    Book createBook(Book book);

    Book updateBook(Long id, Book book);

    List<Book> getBooksByGenre(String genre);

    List<Book> getAllBooks();
}
