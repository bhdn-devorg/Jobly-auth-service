package dev.bhdn.jobly.auth.service.service.impl;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.oauth.DbxCredential;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;
import dev.bhdn.jobly.auth.service.dto.company.CompanyDto;
import dev.bhdn.jobly.auth.service.dto.company.CompanyResponseDto;
import dev.bhdn.jobly.auth.service.exception.UnacceptableContentTypeException;
import dev.bhdn.jobly.auth.service.mapper.CompanyMapper;
import dev.bhdn.jobly.auth.service.model.Company;
import dev.bhdn.jobly.auth.service.repository.CompanyRepository;
import dev.bhdn.jobly.auth.service.service.CompanyService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private static final String FOLDER_PATH = "/Apps/Jobly/companies/";
    private static final long CREDENTIAL_EXPIRES_AT = 0L;
    private static final String IMAGE_JPEG = "image/jpeg";
    private static final String IMAGE_PNG = "image/png";
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private DbxClientV2 client;

    @Value("${dropbox.access.token}")
    private String dropboxAccessToken;

    @Value("${dropbox.app.key}")
    private String dropBoxAppKey;

    @Value("${dropbox.app.secret}")
    private String dropboxAppSecret;

    @Value("${dropbox.refresh.token}")
    private String dropboxRefreshToken;

    @Value("${dropbox.client.id}")
    private String dropboxClientId;

    @Override
    public CompanyResponseDto createCompany(CompanyDto companyDto, MultipartFile photo) {
        Company company = companyMapper.toModel(companyDto);
        company.setLogoLink(savePhoto(photo, companyDto.getName()));
        return companyMapper.toDto(companyRepository.save(company));
    }

    @Override
    public List<CompanyResponseDto> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(companyMapper::toDto)
                .toList();
    }

    @Override
    public CompanyResponseDto getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return companyMapper.toDto(company);
    }

    @Override
    public CompanyResponseDto updateCompany(
            Long id,
            CompanyDto companyDto,
            MultipartFile photo
    ) {
        Company company = companyRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        Company updatedCompany = companyMapper.toModel(companyDto);
        updatedCompany.setLogoLink(company.getLogoLink());

        if (!company.getName().equals(companyDto.getName())) {
            updatePhotoName(company.getName(), companyDto.getName());
        }

        if (company.getLogoLink() != null && !photo.isEmpty()) {
            String path = FOLDER_PATH + company.getName();
            deletePhoto(path);
            updatedCompany.setLogoLink(savePhoto(photo, companyDto.getName()));
        }

        updatedCompany.setId(company.getId());

        return companyMapper.toDto(companyRepository.save(updatedCompany));
    }

    @Override
    public void deleteCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        String path = FOLDER_PATH + company.getName();
        deletePhoto(path);

        companyRepository.delete(company);
    }

    @PostConstruct
    private void initDropboxClient() {
        DbxCredential credential = new DbxCredential(
                dropboxAccessToken,
                CREDENTIAL_EXPIRES_AT,
                dropboxRefreshToken,
                dropBoxAppKey,
                dropboxAppSecret
        );
        DbxRequestConfig config = new DbxRequestConfig(dropboxClientId);
        client = new DbxClientV2(config, credential);
    }

    private String savePhoto(MultipartFile photo, String companyName) {
        String path = FOLDER_PATH + companyName;

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

    private void validateContentType(String contentType) {
        if (!(contentType.equalsIgnoreCase(IMAGE_JPEG)
                || contentType.equalsIgnoreCase(IMAGE_PNG))) {
            throw new UnacceptableContentTypeException("Supports only JPG/PNG files");
        }
    }

    private void deletePhoto(String path) {
        try {
            client.files().deleteV2(path);
        } catch (DbxException e) {
            throw new RuntimeException(e);
        }
    }

    private void updatePhotoName(String oldName, String newName) {
        String oldPath = FOLDER_PATH + oldName;
        String newPath = FOLDER_PATH + newName;

        try {
            client.files().moveV2(oldPath, newPath);
        } catch (DbxException e) {
            throw new RuntimeException(e);
        }
    }
}
