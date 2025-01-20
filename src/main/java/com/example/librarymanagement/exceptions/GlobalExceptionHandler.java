package com.example.librarymanagement.exceptions;

import com.example.librarymanagement.dtos.response.ApiExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiExceptionResponse> handleRunTimeException(RuntimeException exception) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse();

        apiExceptionResponse.setValue(ErrorCode.BAD_REQUEST.getValue());
        apiExceptionResponse.setMessage(ErrorCode.BAD_REQUEST.getMessage());
        apiExceptionResponse.setReasonPhrase(ErrorCode.BAD_REQUEST.getMessage());

        return ResponseEntity.badRequest().body(apiExceptionResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiExceptionResponse> handleAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse();

        apiExceptionResponse.setValue(errorCode.getValue());
        apiExceptionResponse.setMessage(errorCode.getMessage());
        apiExceptionResponse.setReasonPhrase(errorCode.getReasonPhrase());

        return ResponseEntity.badRequest().body(apiExceptionResponse);
    }

}
