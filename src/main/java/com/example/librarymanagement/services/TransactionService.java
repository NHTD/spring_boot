package com.example.librarymanagement.services;

import com.example.librarymanagement.dtos.response.TransactionResponse;

public interface TransactionService {
    TransactionResponse borrowBook(String userId, Long bookId);
    void returnBook(Long transactionId);
}
