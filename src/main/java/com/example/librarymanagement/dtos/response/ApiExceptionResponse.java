package com.example.librarymanagement.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiExceptionResponse {
    private int value;
    private String message;
    private String reasonPhrase;
}
