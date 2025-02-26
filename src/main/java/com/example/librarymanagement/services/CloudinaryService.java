package com.example.librarymanagement.services;

import com.example.librarymanagement.dtos.response.CloudinaryResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    CloudinaryResponse uploadFile(MultipartFile file, String fileName) throws Exception;
}
