package com.example.librarymanagement.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionRequest {
    @JsonProperty("user_id")
    String userId;

    @JsonProperty("book_id")
    Long bookId;

    @JsonProperty("borrow_date")
    LocalDateTime borrowDate;

    @JsonProperty("return_date")
    LocalDateTime returnDate;
}
