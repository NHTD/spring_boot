package com.example.librarymanagement.services;

import com.example.librarymanagement.dtos.request.AuthenticationRequest;
import com.example.librarymanagement.dtos.response.AuthenticationResponse;


public interface AuthenticationService {
    AuthenticationResponse signIn(AuthenticationRequest request);
}
