package dev.bhdn.jobly.auth.service.service;

import org.springframework.web.multipart.MultipartFile;

public interface DropboxStorageService {
    String uploadPhoto(MultipartFile photo, String path);

    void deletePhoto(String path);

    void renamePhoto(String oldPath, String newPath);
}
