package dev.bhdn.jobly.auth.service.service.impl;

import dev.bhdn.jobly.auth.service.dto.company.CompanyDto;
import dev.bhdn.jobly.auth.service.dto.company.CompanyResponseDto;
import dev.bhdn.jobly.auth.service.mapper.CompanyMapper;
import dev.bhdn.jobly.auth.service.model.Company;
import dev.bhdn.jobly.auth.service.repository.CompanyRepository;
import dev.bhdn.jobly.auth.service.service.CompanyService;
import dev.bhdn.jobly.auth.service.service.DropboxStorageService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private static final String FOLDER_PATH = "/Apps/Jobly/companies/";
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final DropboxStorageService dropboxStorageService;

    @Override
    public CompanyResponseDto createCompany(CompanyDto companyDto, MultipartFile photo) {
        Company company = companyMapper.toModel(companyDto);
        String path = FOLDER_PATH + companyDto.getName();
        company.setLogoLink(dropboxStorageService.uploadPhoto(photo, path));
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

        if (!company.getName().equals(companyDto.getName()) && photo.isEmpty()) {
            String oldPath = FOLDER_PATH + company.getName();
            String newPath = FOLDER_PATH + companyDto.getName();
            dropboxStorageService.renamePhoto(oldPath, newPath);
        }

        if (company.getLogoLink() != null && !photo.isEmpty()) {
            String pathToDelete = FOLDER_PATH + company.getName();
            dropboxStorageService.deletePhoto(pathToDelete);

            String pathToUpload = FOLDER_PATH + companyDto.getName();
            updatedCompany.setLogoLink(dropboxStorageService.uploadPhoto(photo, pathToUpload));
        }

        updatedCompany.setId(company.getId());

        return companyMapper.toDto(companyRepository.save(updatedCompany));
    }

    @Override
    public void deleteCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        String path = FOLDER_PATH + company.getName();
        dropboxStorageService.deletePhoto(path);

        companyRepository.delete(company);
    }
}
