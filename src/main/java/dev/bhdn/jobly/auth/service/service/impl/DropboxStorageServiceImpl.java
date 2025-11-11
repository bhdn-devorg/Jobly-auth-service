package dev.bhdn.jobly.auth.service.service.impl;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;
import dev.bhdn.jobly.auth.service.exception.UnacceptableContentTypeException;
import dev.bhdn.jobly.auth.service.service.DropboxStorageService;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;
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
    public String uploadPhoto(MultipartFile photo, String path) {
        validateContentType(Objects.requireNonNull(photo.getContentType()));

        try (InputStream in = photo.getInputStream()) {
            client.files().uploadBuilder(path)
                    .withMode(WriteMode.ADD)
                    .withClientModified(new Date(System.currentTimeMillis()))
                    .uploadAndFinish(in);

            return client.sharing().createSharedLinkWithSettings(path).getUrl() + "&raw=1";
        } catch (IOException | DbxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePhoto(String path) {
        try {
            client.files().deleteV2(path);
        } catch (DbxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void renamePhoto(String oldPath, String newPath) {
        try {
            client.files().moveV2(oldPath, newPath);
        } catch (DbxException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateContentType(String contentType) {
        if (!(contentType.equalsIgnoreCase(IMAGE_JPEG)
                || contentType.equalsIgnoreCase(IMAGE_PNG))) {
            throw new UnacceptableContentTypeException("Supports only JPG/PNG files");
        }
    }
}
