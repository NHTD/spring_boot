package com.example.librarymanagement.dtos.response;

import com.example.librarymanagement.enums.BookStatusEnum;
import com.example.librarymanagement.models.Category;
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
public class BookResponse {
    Long id;

    String title;

    String image;

    String description;

    int price;

    int star;

    Category category;

    User user;

    @JsonProperty("status")
    BookStatusEnum status;

    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @JsonProperty("updated_at")
    LocalDateTime updatedAt;
}
