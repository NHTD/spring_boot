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
public class UserResponse {
    String id;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;

    String email;

    String password;

    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @JsonProperty("updated_at")
    LocalDateTime updatedAt;
}
