package com.example.librarymanagement.services.servicesImp;

import com.example.librarymanagement.dtos.request.AuthenticationRequest;
import com.example.librarymanagement.dtos.response.AuthenticationResponse;
import com.example.librarymanagement.enums.UserStatusEnum;
import com.example.librarymanagement.exceptions.AppException;
import com.example.librarymanagement.exceptions.ErrorCode;
import com.example.librarymanagement.models.User;
import com.example.librarymanagement.repositories.UserRepository;
import com.example.librarymanagement.security.JwtProvider;
import com.example.librarymanagement.security.UserDetailsServiceImp;
import com.example.librarymanagement.services.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationServiceImp implements AuthenticationService {

    final UserDetailsServiceImp userDetailsService;
    final PasswordEncoder passwordEncoder;
    final UserRepository userRepository;

    @Override
    public AuthenticationResponse signIn(AuthenticationRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if(user.getStatus().equals(UserStatusEnum.INVALID)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        Authentication authentication = authenticate(request.getEmail(), request.getPassword());

        String jwt = JwtProvider.generateToken(authentication);

        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if(!userDetails.getUsername().equals(email)){
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        if(password != null){
            if(!passwordEncoder.matches(password, userDetails.getPassword())){
                throw new AppException(ErrorCode.PASSWORD_NOT_VALID);
            }
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
