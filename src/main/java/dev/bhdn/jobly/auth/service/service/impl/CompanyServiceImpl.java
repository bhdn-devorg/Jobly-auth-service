package dev.bhdn.jobly.auth.service.service.impl;

import dev.bhdn.jobly.auth.service.dto.company.CompanyDto;
import dev.bhdn.jobly.auth.service.dto.company.CompanyResponseDto;
import dev.bhdn.jobly.auth.service.mapper.CompanyMapper;
import dev.bhdn.jobly.auth.service.model.Company;
import dev.bhdn.jobly.auth.service.repository.CompanyRepository;
import dev.bhdn.jobly.auth.service.service.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponseDto createCompany(CompanyDto companyDto) {
        Company company = companyMapper.toModel(companyDto);
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
    public CompanyResponseDto updateCompany(Long id, CompanyDto companyDto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        Company updatedCompany = companyMapper.toModel(companyDto);
        updatedCompany.setId(company.getId());

        return companyMapper.toDto(companyRepository.save(updatedCompany));
    }

    @Override
    public void deleteCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        companyRepository.delete(company);
    }
}
