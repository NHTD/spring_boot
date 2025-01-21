package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dtos.request.AuthenticationRequest;
import com.example.librarymanagement.dtos.response.AuthenticationResponse;
import com.example.librarymanagement.services.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationController {

    final AuthenticationService authenticationService;

    @PostMapping
    ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.signIn(request);
        return ResponseEntity.status(HttpStatus.OK).body(AuthenticationResponse.builder().token(response.getToken()).build());
    }
}
