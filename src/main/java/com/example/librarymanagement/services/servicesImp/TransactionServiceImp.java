package com.example.librarymanagement.services.servicesImp;

import com.example.librarymanagement.dtos.response.TransactionResponse;
import com.example.librarymanagement.enums.BookStatusEnum;
import com.example.librarymanagement.exceptions.AppException;
import com.example.librarymanagement.exceptions.ErrorCode;
import com.example.librarymanagement.mapper.TransactionMapper;
import com.example.librarymanagement.models.Book;
import com.example.librarymanagement.models.Transaction;
import com.example.librarymanagement.models.User;
import com.example.librarymanagement.repositories.BookRepository;
import com.example.librarymanagement.repositories.TransactionRepository;
import com.example.librarymanagement.repositories.UserRepository;
import com.example.librarymanagement.services.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionServiceImp implements TransactionService {

    TransactionRepository transactionRepository;
    BookRepository bookRepository;
    UserRepository userRepository;

    TransactionMapper transactionMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public TransactionResponse borrowBook(String userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

        if (!BookStatusEnum.InStock.equals(book.getStatus())) {
            throw new AppException(ErrorCode.BOOK_NOT_FOUND);
        }

        Transaction transaction = Transaction.builder()
                .user(user)
                .book(book)
                .borrowDate(LocalDateTime.now())
                .build();

        book.setStatus(BookStatusEnum.Borrowed);
        bookRepository.save(book);

        return transactionMapper.toTransactionResponse(transactionRepository.save(transaction));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void returnBook(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new AppException(ErrorCode.TRANSACTION_NOT_FOUND));

        Book book = transaction.getBook();
        if (!BookStatusEnum.Borrowed.equals(book.getStatus())) {
            throw new AppException(ErrorCode.BOOK_NOT_BORROWED);
        }

        book.setStatus(BookStatusEnum.InStock);
        transaction.setReturnDate(LocalDateTime.now());

        bookRepository.save(book);

        transactionRepository.delete(transaction);
    }
}
