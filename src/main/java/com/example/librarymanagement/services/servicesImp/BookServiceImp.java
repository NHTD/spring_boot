package com.example.librarymanagement.services.servicesImp;

import com.example.librarymanagement.dtos.request.BookRequest;
import com.example.librarymanagement.dtos.request.BookUpdateRequest;
import com.example.librarymanagement.dtos.response.BookResponse;
import com.example.librarymanagement.dtos.response.CloudinaryResponse;
import com.example.librarymanagement.enums.BookStatusEnum;
import com.example.librarymanagement.exceptions.AppException;
import com.example.librarymanagement.exceptions.ErrorCode;
import com.example.librarymanagement.mapper.BookMapper;
import com.example.librarymanagement.models.Book;
import com.example.librarymanagement.models.Category;
import com.example.librarymanagement.models.Transaction;
import com.example.librarymanagement.models.User;
import com.example.librarymanagement.repositories.BookRepository;
import com.example.librarymanagement.repositories.CategoryRepository;
import com.example.librarymanagement.repositories.TransactionRepository;
import com.example.librarymanagement.repositories.UserRepository;
import com.example.librarymanagement.services.BookService;
import com.example.librarymanagement.services.CloudinaryService;
import com.example.librarymanagement.utils.FileUploadUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImp implements BookService {

    BookRepository bookRepository;
    CategoryRepository categoryRepository;
    UserRepository userRepository;
    TransactionRepository transactionRepository;

    CloudinaryService cloudinaryService;

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
    public Page<BookResponse> getBooks(Pageable pageable, String bookStatus) {
        if(bookStatus.equals("")){
            Page<Book> bookPage = bookRepository.getAllBook(pageable);
            return bookPage.map(bookMapper::toBookResponse);
        }

        BookStatusEnum bookStatusEnum;
        try {
            bookStatusEnum = BookStatusEnum.fromString(bookStatus);

        } catch (AppException e) {
            throw new AppException(ErrorCode.BOOK_STATUS_NOT_VALID);
        }

        if (!(bookStatusEnum == BookStatusEnum.InStock || bookStatusEnum == BookStatusEnum.Borrowed
                || bookStatusEnum == BookStatusEnum.Overdue)) {
            throw new AppException(ErrorCode.BOOK_STATUS_NOT_VALID);
        }

        Page<Book> bookPage = bookRepository.getAllByStatus(pageable, bookStatusEnum);
        return bookPage.map(bookMapper::toBookResponse);
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

    @Override
    public String uploadBookImage(Long bookId, MultipartFile file) throws Exception {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

        FileUploadUtils.assertAllowed(file, FileUploadUtils.IMAGE_PATTERN);
        String fileName = FileUploadUtils.getFilename(file.getOriginalFilename());
        CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName);
        book.setImage(response.getUrl());
        bookRepository.save(book);

        return response.getUrl();
    }


    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateOverdueBooks() {
        LocalDateTime overdueDate = LocalDateTime.now().minusDays(14);

        List<Transaction> transactions = transactionRepository.findOverdueTransactions(overdueDate);

        for (Transaction transaction : transactions) {
            Book book = transaction.getBook();
            book.setStatus(BookStatusEnum.Overdue);
            bookRepository.save(book);
        }
    }
}
