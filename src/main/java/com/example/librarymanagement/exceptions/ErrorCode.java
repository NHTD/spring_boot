package com.example.librarymanagement.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    RUNTIME_EXCEPTION(9999, "Runtime exception", "RuntimeException"),
    USER_EXISTED(HttpStatus.CONFLICT.value(), "User existed", HttpStatus.CONFLICT.getReasonPhrase()),
    ;

    private int value;
    private String message;
    private String reasonPhrase;
}
