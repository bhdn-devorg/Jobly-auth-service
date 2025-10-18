package dev.bhdn.jobly.auth.service.mapper;

import dev.bhdn.jobly.auth.service.config.MapperConfig;
import dev.bhdn.jobly.auth.service.dto.company.CompanyDto;
import dev.bhdn.jobly.auth.service.dto.company.CompanyResponseDto;
import dev.bhdn.jobly.auth.service.model.Company;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CompanyMapper {
    Company toModel(CompanyDto companyDto);

    CompanyResponseDto toDto(Company company);
}
