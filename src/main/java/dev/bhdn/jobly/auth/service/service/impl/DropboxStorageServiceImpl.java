package dev.bhdn.jobly.auth.service.service.impl;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import dev.bhdn.jobly.auth.service.dto.storage.PhotoResultDto;
import dev.bhdn.jobly.auth.service.exception.UnacceptableContentTypeException;
import dev.bhdn.jobly.auth.service.service.DropboxStorageService;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DropboxStorageServiceImpl implements DropboxStorageService {
    private static final String IMAGE_JPEG = "image/jpeg";
    private static final String IMAGE_PNG = "image/png";
    private final DbxClientV2 client;

    @Override
    public PhotoResultDto uploadPhoto(MultipartFile photo, String path) {
        validateContentType(Objects.requireNonNull(photo.getContentType()));
        return sendPhotoToDropbox(photo, path);
    }

    @Override
    public PhotoResultDto updatePhoto(
            MultipartFile photo, String existingPath, String uniquePath
    ) {
        if (existingPath != null && !existingPath.isEmpty()) {
            deletePhoto(existingPath);
            return sendPhotoToDropbox(photo, existingPath);
        }

        return uploadPhoto(photo, uniquePath);
    }

    @Override
    public String generateUniquePath(String folderPath) {
        return String.format("%s%s", folderPath, UUID.randomUUID());
    }

    private void validateContentType(String contentType) {
        if (!(contentType.equalsIgnoreCase(IMAGE_JPEG)
                || contentType.equalsIgnoreCase(IMAGE_PNG))) {
            throw new UnacceptableContentTypeException("Supports only JPG/PNG files");
        }
    }

    private PhotoResultDto sendPhotoToDropbox(MultipartFile photo, String path) {
        try (InputStream in = photo.getInputStream()) {
            FileMetadata fileMetadata = client.files()
                    .uploadBuilder(generateUniquePath(path))
                    .withMode(WriteMode.ADD)
                    .withClientModified(new Date(System.currentTimeMillis()))
                    .uploadAndFinish(in);

            String logoPath = fileMetadata.getPathDisplay();
            String logoLink = client.sharing()
                    .createSharedLinkWithSettings(logoPath)
                    .getUrl() + "&raw=1";

            return new PhotoResultDto(logoPath, logoLink);
        } catch (IOException | DbxException e) {
            throw new RuntimeException(e);
        }
    }

    private void deletePhoto(String path) {
        try {
            client.files().deleteV2(path);
        } catch (DbxException e) {
            throw new RuntimeException(e);
        }
    }
}
