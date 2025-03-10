package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dtos.request.UserRequest;
import com.example.librarymanagement.dtos.request.UserUpdateRequest;
import com.example.librarymanagement.dtos.response.UserResponse;
import com.example.librarymanagement.enums.UserStatusEnum;
import com.example.librarymanagement.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(request, UserStatusEnum.VALID));
    }

    @GetMapping
    ResponseEntity<List<UserResponse>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    @PutMapping("/{userId}")
    ResponseEntity<UserResponse> updateUser(
            @PathVariable("userId") String userId,
            @ModelAttribute UserUpdateRequest request,
            @RequestPart(value = "avatar", required = false)MultipartFile file
            ) throws Exception {
        UserResponse userResponse = userService.updateUser(userId, request);

        if (file != null && !file.isEmpty()) {
            String avatar = userService.uploadAvatar(userId, file);

            userResponse.setAvatar(avatar);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
    @DeleteMapping("/{userId}")
    ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("Delete successfully");
    }

    @PostMapping("/user-details")
    ResponseEntity<UserResponse> getUser(@RequestHeader("Authorization") String token) {
        String extractedToken = token.substring(7);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserDetail(extractedToken));
    }
}
