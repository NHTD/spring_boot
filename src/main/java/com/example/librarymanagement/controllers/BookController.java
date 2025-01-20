package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dtos.request.BookRequest;
import com.example.librarymanagement.dtos.request.BookUpdateRequest;
import com.example.librarymanagement.dtos.response.BookResponse;
import com.example.librarymanagement.enums.BookStatusEnum;
import com.example.librarymanagement.services.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookController {

    BookService bookService;

    @PostMapping
    ResponseEntity<BookResponse> createBook(@RequestBody BookRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.createBook(request, BookStatusEnum.InStock));
    }

    @GetMapping
    ResponseEntity<List<BookResponse>> getBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBooks());
    }

    @PutMapping("/bookId")
    ResponseEntity<BookResponse> updateBook(@PathVariable("bookId") Long id, @RequestBody BookUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id, request));
    }

    @DeleteMapping("/bookId")
    ResponseEntity<String> deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).body("Delete successfully");
    }

}
