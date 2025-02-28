package com.example.librarymanagement.services;

import com.example.librarymanagement.dtos.request.BookRequest;
import com.example.librarymanagement.dtos.request.BookUpdateRequest;
import com.example.librarymanagement.dtos.response.BookResponse;
import com.example.librarymanagement.enums.BookStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface BookService {
    BookResponse createBook(BookRequest request, BookStatusEnum status);
    Page<BookResponse> getBooks(Pageable pageable);
    BookResponse updateBook(Long id, BookUpdateRequest request);
    void deleteBook(Long id);
    String uploadBookImage(Long bookId, MultipartFile file) throws Exception;
    List<BookResponse> getAllBookStatuses(String bookStatus);
    void updateOverdueBooks();
}
