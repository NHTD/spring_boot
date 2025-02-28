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

    ROLE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Role not found", HttpStatus.NOT_FOUND.getReasonPhrase()),

    CATEGORY_EXISTED(HttpStatus.CONFLICT.value(), "Category existed", HttpStatus.CONFLICT.getReasonPhrase()),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "This category is not found", HttpStatus.NOT_FOUND.getReasonPhrase()),

    BOOK_EXISTED(HttpStatus.CONFLICT.value(), "Book existed", HttpStatus.CONFLICT.getReasonPhrase()),
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "This book is not found", HttpStatus.NOT_FOUND.getReasonPhrase()),
    BOOK_NOT_BORROWED(HttpStatus.NOT_FOUND.value(), "This book status is not borrowed", HttpStatus.NOT_FOUND.getReasonPhrase()),
    BOOK_STATUS_NOT_VALID(HttpStatus.NOT_FOUND.value(), "This status is not valid", HttpStatus.NOT_FOUND.getReasonPhrase()),

    TRANSACTION_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "This transaction is not found", HttpStatus.NOT_FOUND.getReasonPhrase()),

    STATUS_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "This status is not supported", HttpStatus.NOT_FOUND.getReasonPhrase()),

    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "Invalid token", HttpStatus.FORBIDDEN.getReasonPhrase()),
    PASSWORD_NOT_VALID(HttpStatus.BAD_REQUEST.value(), "Password is not valid", HttpStatus.BAD_REQUEST.getReasonPhrase()),
    ;

    private int value;
    private String message;
    private String reasonPhrase;
}
