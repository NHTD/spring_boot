package com.example.librarymanagement.services.servicesImp;

import com.example.librarymanagement.dtos.request.UserRequest;
import com.example.librarymanagement.dtos.request.UserUpdateRequest;
import com.example.librarymanagement.dtos.response.CloudinaryResponse;
import com.example.librarymanagement.dtos.response.UserResponse;
import com.example.librarymanagement.enums.UserStatusEnum;
import com.example.librarymanagement.exceptions.AppException;
import com.example.librarymanagement.exceptions.ErrorCode;
import com.example.librarymanagement.mapper.UserMapper;
import com.example.librarymanagement.models.Role;
import com.example.librarymanagement.models.User;
import com.example.librarymanagement.repositories.RoleRepository;
import com.example.librarymanagement.repositories.UserRepository;
import com.example.librarymanagement.security.JwtConstant;
import com.example.librarymanagement.services.CloudinaryService;
import com.example.librarymanagement.services.UserService;
import com.example.librarymanagement.utils.FileUploadUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
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

    CloudinaryService cloudinaryService;

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
    public String uploadAvatar(String userId, MultipartFile file) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        FileUploadUtils.assertAllowed(file, FileUploadUtils.IMAGE_PATTERN);
        String fileName = FileUploadUtils.getFilename(file.getOriginalFilename());
        CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName);
        user.setAvatar(response.getUrl());
        userRepository.save(user);

        return response.getUrl();
    }

    @Override
    public UserResponse getUserDetail(String token) {
        SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        boolean isTokenValid = claims.getExpiration().before(new Date());

        if(isTokenValid){
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        String email = String.valueOf(claims.get("email"));
        if(email.isEmpty()){
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
