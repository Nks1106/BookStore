package com.book.store.controller;

import com.book.store.mapper.BookMapper;
import com.book.store.mapper.BookRequestDTO;
import com.book.store.mapper.BookResponseDTO;
import com.book.store.model.Book;
import com.book.store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@GetMapping("/api/books")
public class BookController {
    public ResponseEntity<BookResponseDTO> update
    @Autowired
    private BookService bookService;
    private BookMapper bookMapper;

    @PostMapping("/create")
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        try {
            Book book = bookMapper.toEntity(bookRequestDTO);
            Book savedBook = bookService.createBook(book);
            BookResponseDTO bookResponseDTO = bookMapper.toDTO(savedBook);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDTO bookRequestDTO) {
        try {
            Book book = bookMapper.toEntity(bookRequestDTO);
            Book updateBook = bookService.updateBook(id, book);
            BookResponseDTO bookResponseDTO = bookMapper.toDTO(updateBook);
            return ResponseEntity.ok(bookResponseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{genre}")
    public ResponseEntity<list<BookResponseDTO> getBooksByGenre(@PathVariable String genre) {
        try {
            List<Book> books = bookService.getBooksByGenre(genre);
            List<BookResponseDTO> bookResponseDTOS = books.stream().map(bookMapper::toDTO).collect(Collectors.toList());
            return ResponseEntity.ok(bookResponseDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        try {
            List<Book> books=bookService.getAllBooks();

        }
    }
}
