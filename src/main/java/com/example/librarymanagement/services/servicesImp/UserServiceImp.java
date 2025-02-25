package com.example.librarymanagement.services.servicesImp;

import com.example.librarymanagement.dtos.request.UserRequest;
import com.example.librarymanagement.dtos.request.UserUpdateRequest;
import com.example.librarymanagement.dtos.response.UserResponse;
import com.example.librarymanagement.enums.UserStatusEnum;
import com.example.librarymanagement.exceptions.AppException;
import com.example.librarymanagement.exceptions.ErrorCode;
import com.example.librarymanagement.mapper.UserMapper;
import com.example.librarymanagement.models.Role;
import com.example.librarymanagement.models.User;
import com.example.librarymanagement.repositories.RoleRepository;
import com.example.librarymanagement.repositories.UserRepository;
import com.example.librarymanagement.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImp implements UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;

    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserRequest request, UserStatusEnum status) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        User user = userMapper.toUser(request);
        if(status.equals(UserStatusEnum.VALID)){
            user.setStatus(UserStatusEnum.VALID);
        }

        if(!request.getPassword().equals(request.getConfirmPassword())) {
            throw new AppException(ErrorCode.PASSWORD_NOT_VALID);
        }

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(request.getPassword()));

        Role role = roleRepository.findRoleByName("USER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        user.setRoles(Collections.singleton(role));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.toUpdateUser(user, request);

        if(request.getStatus().equals(UserStatusEnum.VALID)){
            user.setStatus(UserStatusEnum.VALID);
        } else if(request.getStatus().equals(UserStatusEnum.INVALID)) {
            user.setStatus(UserStatusEnum.INVALID);
        }

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
