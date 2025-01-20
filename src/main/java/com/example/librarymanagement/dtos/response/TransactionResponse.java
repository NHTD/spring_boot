package com.example.librarymanagement.dtos.response;

import com.example.librarymanagement.models.Book;
import com.example.librarymanagement.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionResponse {
    User user;

    Book book;

    @JsonProperty("borrow_date")
    LocalDateTime borrowDate;

    @JsonProperty("return_date")
    LocalDateTime returnDate;

    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @JsonProperty("updated_at")
    LocalDateTime updatedAt;
}
