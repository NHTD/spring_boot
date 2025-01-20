package com.example.librarymanagement.services;

import com.example.librarymanagement.dtos.request.BookRequest;
import com.example.librarymanagement.dtos.request.BookUpdateRequest;
import com.example.librarymanagement.dtos.response.BookResponse;
import com.example.librarymanagement.enums.BookStatusEnum;

import java.util.List;

public interface BookService {
    BookResponse createBook(BookRequest request, BookStatusEnum status);
    List<BookResponse> getBooks();
    BookResponse updateBook(Long id, BookUpdateRequest request);
    void deleteBook(Long id);
}
