package com.example.librarymanagement.services;

import com.example.librarymanagement.dtos.request.UserRequest;
import com.example.librarymanagement.dtos.request.UserUpdateRequest;
import com.example.librarymanagement.dtos.response.UserResponse;
import com.example.librarymanagement.enums.UserStatusEnum;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request, UserStatusEnum status);

    List<UserResponse> getAllUser();

    void deleteUser(String userId);

    UserResponse updateUser(String userId, UserUpdateRequest request);
}
