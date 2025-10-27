package dev.bhdn.jobly.auth.service.service;

import dev.bhdn.jobly.auth.service.dto.company.CompanyDto;
import dev.bhdn.jobly.auth.service.dto.company.CompanyResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompanyService {
    CompanyResponseDto createCompany(CompanyDto companyDto, MultipartFile photo);

    List<CompanyResponseDto> getAllCompanies();

    CompanyResponseDto getCompanyById(Long id);

    CompanyResponseDto updateCompany(Long id, CompanyDto companyDto, MultipartFile photo);

    void deleteCompanyById(Long id);
}
