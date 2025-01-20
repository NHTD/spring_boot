package com.example.librarymanagement.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppException extends RuntimeException{

    private ErrorCode errorCode;

}
