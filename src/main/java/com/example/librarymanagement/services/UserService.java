package com.example.librarymanagement.services;

import com.example.librarymanagement.dtos.request.UserRequest;
import com.example.librarymanagement.dtos.response.UserResponse;
import com.example.librarymanagement.enums.UserStatusEnum;

public interface UserService {
    UserResponse createUser(UserRequest request, UserStatusEnum status);
}
