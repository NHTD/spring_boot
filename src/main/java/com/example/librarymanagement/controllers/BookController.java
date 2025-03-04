package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dtos.request.BookRequest;
import com.example.librarymanagement.dtos.request.BookUpdateRequest;
import com.example.librarymanagement.dtos.response.BookListResponse;
import com.example.librarymanagement.dtos.response.BookResponse;
import com.example.librarymanagement.enums.BookStatusEnum;
import com.example.librarymanagement.services.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookController {

    BookService bookService;

    @PostMapping
    ResponseEntity<BookResponse> createBook(
            @ModelAttribute BookRequest request,
            @RequestPart(value = "avatar", required = false) MultipartFile file
    ) throws Exception {
        BookResponse bookResponse = bookService.createBook(request, BookStatusEnum.InStock);

        if(file!=null && !file.isEmpty()){
            String image = bookService.uploadBookImage(bookResponse.getId(), file);
            bookResponse.setImage(image);
        }

        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @GetMapping
    ResponseEntity<BookListResponse> getBooks(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "", required = false) String bookStatus
    ) {
        PageRequest pageRequest = PageRequest.of(
                offset, limit,
                Sort.by("id").ascending()
        );

        Page<BookResponse> bookPage = bookService.getBooks(pageRequest, bookStatus);

        List<BookResponse> bookResponses = bookPage.getContent();
        long totalPage = bookPage.getTotalElements();

        return ResponseEntity.status(HttpStatus.OK).body(BookListResponse.builder().bookResponses(bookResponses).totalPages(totalPage).build());
    }

    @PutMapping("/{bookId}")
    ResponseEntity<BookResponse> updateBook(@PathVariable("bookId") Long id, @RequestBody BookUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id, request));
    }

    @DeleteMapping("/{bookId}")
    ResponseEntity<String> deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).body("Delete successfully");
    }

    @PutMapping("/overdue")
    public ResponseEntity<String> updateOverdueBooks() {
        bookService.updateOverdueBooks();
        return ResponseEntity.status(HttpStatus.OK).body("Update overdue successful");
    }
}
