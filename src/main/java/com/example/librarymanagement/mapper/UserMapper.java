package com.example.librarymanagement.mapper;

import com.example.librarymanagement.dtos.request.UserRequest;
import com.example.librarymanagement.dtos.response.UserResponse;
import com.example.librarymanagement.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);
    UserResponse toUserResponse(User user);

    void toUpdateUser(@MappingTarget User user, UserRequest request);
}
