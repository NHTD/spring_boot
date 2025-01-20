package com.example.librarymanagement.dtos.request;

import com.example.librarymanagement.enums.BookStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequest {

    @JsonProperty("title")
    String title;

    @JsonProperty("description")
    String description;

    @JsonProperty("image")
    String image;

    @JsonProperty("category_id")
    Long categoryId;

    @JsonProperty("user_id")
    String userId;

    @JsonProperty("status")
    BookStatusEnum status;
}
