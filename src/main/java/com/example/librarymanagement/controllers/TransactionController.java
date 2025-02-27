package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dtos.response.TransactionResponse;
import com.example.librarymanagement.services.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionController {

    TransactionService transactionService;

    @PostMapping("/borrow")
    public ResponseEntity<TransactionResponse> borrowBook(
            @RequestParam String userId,
            @RequestParam Long bookId
    ) {
        TransactionResponse response = transactionService.borrowBook(userId, bookId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam Long transactionId) {
        transactionService.returnBook(transactionId);
        return ResponseEntity.status(HttpStatus.OK).body("Return book successfully");
    }
}
