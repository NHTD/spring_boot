package com.example.librarymanagement.dtos.request;

import com.example.librarymanagement.enums.UserStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;

    String email;

    @JsonProperty("avatar")
    String avatar;

    @JsonProperty("gender")
    String gender;

    @JsonProperty("status")
    UserStatusEnum status;

    @JsonProperty("age")
    int age;
}
