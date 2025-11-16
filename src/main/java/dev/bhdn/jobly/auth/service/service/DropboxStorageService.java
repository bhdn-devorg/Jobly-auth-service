package dev.bhdn.jobly.auth.service.service;

import dev.bhdn.jobly.auth.service.dto.storage.PhotoResultDto;
import org.springframework.web.multipart.MultipartFile;

public interface DropboxStorageService {
    PhotoResultDto uploadPhoto(MultipartFile photo, String path);

    PhotoResultDto updatePhoto(MultipartFile photo, String existingPath, String uniquePath);

    String generateUniquePath(String folderPath);
}
