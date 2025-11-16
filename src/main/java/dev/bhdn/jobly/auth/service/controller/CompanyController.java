package dev.bhdn.jobly.auth.service.controller;

import dev.bhdn.jobly.auth.service.dto.company.CompanyDto;
import dev.bhdn.jobly.auth.service.dto.company.CompanyResponseDto;
import dev.bhdn.jobly.auth.service.service.CompanyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public CompanyResponseDto createCompany(
            @RequestPart(value = "company") CompanyDto companyDto,
            @RequestPart(value = "photo", required = false) MultipartFile photo
    ) {
        return companyService.createCompany(companyDto, photo);
    }

    @GetMapping
    public List<CompanyResponseDto> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyResponseDto getCompanyByID(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PutMapping("/{id}")
    public CompanyResponseDto updateCompany(
            @PathVariable Long id,
            @RequestPart(value = "company") CompanyDto companyDto,
            @RequestPart(value = "photo", required = false) MultipartFile photo
    ) {
        return companyService.updateCompany(id, companyDto, photo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompanyById(id);
    }
}
