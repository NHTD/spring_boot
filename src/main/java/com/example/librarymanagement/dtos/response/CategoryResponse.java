package com.example.librarymanagement.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    String code;
    String value;

    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @JsonProperty("updated_at")
    LocalDateTime updatedAt;
}
