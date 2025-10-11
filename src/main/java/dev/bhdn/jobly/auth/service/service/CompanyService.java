package dev.bhdn.jobly.auth.service.service;

import dev.bhdn.jobly.auth.service.dto.company.CompanyDto;
import dev.bhdn.jobly.auth.service.dto.company.CompanyResponseDto;
import java.util.List;
import java.util.Optional;

public interface CompanyService {
    CompanyResponseDto createCompany(CompanyDto companyDto);

    List<CompanyResponseDto> getAllCompanies();

    Optional<CompanyResponseDto> getCompanyById(Long id);

    CompanyResponseDto updateCompany(Long id, CompanyDto companyDto);

    void deleteCompanyById(Long id);
}
