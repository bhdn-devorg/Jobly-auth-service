package dev.bhdn.jobly.auth.service.mapper;

import dev.bhdn.jobly.auth.service.config.MapperConfig;
import dev.bhdn.jobly.auth.service.dto.recruiter.RecruiterProfileDto;
import dev.bhdn.jobly.auth.service.dto.recruiter.RecruiterProfileResponseDto;
import dev.bhdn.jobly.auth.service.model.Company;
import dev.bhdn.jobly.auth.service.model.RecruiterProfile;
import dev.bhdn.jobly.auth.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface RecruiterProfileMapper {
    @Mappings({
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "companyId", source = "company.id")
    })
    RecruiterProfileResponseDto toDto(RecruiterProfile recruiterProfile);

    @Mappings({
            @Mapping(target = "user", source = "userId", qualifiedByName = "getUserFromId"),
            @Mapping(target = "company", source = "companyId", qualifiedByName = "getCompanyFromId")
    })
    RecruiterProfile toModel(RecruiterProfileDto recruiterProfileDto);

    @Named("getUserFromId")
    default User userFromId(Long userId) {
        return new User().setId(userId);
    }

    @Named("getCompanyFromId")
    default Company companyFromId(Long companyId) {
        return new Company().setId(companyId);
    }
}
