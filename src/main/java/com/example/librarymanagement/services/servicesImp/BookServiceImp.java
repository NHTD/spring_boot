package com.example.librarymanagement.services.servicesImp;

import com.example.librarymanagement.dtos.request.BookRequest;
import com.example.librarymanagement.dtos.request.BookUpdateRequest;
import com.example.librarymanagement.dtos.response.BookResponse;
import com.example.librarymanagement.enums.BookStatusEnum;
import com.example.librarymanagement.exceptions.AppException;
import com.example.librarymanagement.exceptions.ErrorCode;
import com.example.librarymanagement.mapper.BookMapper;
import com.example.librarymanagement.models.Book;
import com.example.librarymanagement.models.Category;
import com.example.librarymanagement.models.User;
import com.example.librarymanagement.repositories.BookRepository;
import com.example.librarymanagement.repositories.CategoryRepository;
import com.example.librarymanagement.repositories.UserRepository;
import com.example.librarymanagement.services.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImp implements BookService {

    BookRepository bookRepository;
    CategoryRepository categoryRepository;
    UserRepository userRepository;

    BookMapper bookMapper;

    @Override
    public BookResponse createBook(BookRequest request, BookStatusEnum status) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        User user = userRepository.findById(request.getUserId())
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Book book = bookMapper.toBook(request);

        book.setCategory(category);
        book.setUser(user);

        if(BookStatusEnum.InStock.equals(status)) {
            book.setStatus(BookStatusEnum.InStock);
        }

        return bookMapper.toBookResponse(bookRepository.save(book));
    }

    @Override
    public List<BookResponse> getBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toBookResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse updateBook(Long id, BookUpdateRequest request) {
        Book book = bookRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_EXISTED));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        bookMapper.toUpdateBook(book, request);

        book.setCategory(category);
        book.setUser(user);

        return bookMapper.toBookResponse(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
