package com.example.librarymanagement.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "Something went wrong", HttpStatus.URI_TOO_LONG.getReasonPhrase()),

    USER_EXISTED(HttpStatus.CONFLICT.value(), "User existed", HttpStatus.CONFLICT.getReasonPhrase()),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "User not found", HttpStatus.NOT_FOUND.getReasonPhrase()),

    CATEGORY_EXISTED(HttpStatus.CONFLICT.value(), "Category existed", HttpStatus.CONFLICT.getReasonPhrase()),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "This category is not found", HttpStatus.NOT_FOUND.getReasonPhrase()),

    BOOK_EXISTED(HttpStatus.CONFLICT.value(), "Book existed", HttpStatus.CONFLICT.getReasonPhrase()),
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "This book is not found", HttpStatus.NOT_FOUND.getReasonPhrase()),

    STATUS_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "This status is not supported", HttpStatus.NOT_FOUND.getReasonPhrase()),
    ;

    private int value;
    private String message;
    private String reasonPhrase;
}
