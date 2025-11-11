package dev.bhdn.jobly.auth.service.mapper;

import dev.bhdn.jobly.auth.service.config.MapperConfig;
import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileDto;
import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileResponseDto;
import dev.bhdn.jobly.auth.service.model.EmployeeProfile;
import dev.bhdn.jobly.auth.service.model.Language;
import dev.bhdn.jobly.auth.service.model.Skill;
import dev.bhdn.jobly.auth.service.model.User;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = {SkillMapper.class, LanguageMapper.class})
public interface EmployeeProfileMapper {
    @Mappings({
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "skillIds", ignore = true),
            @Mapping(target = "languageIds", ignore = true)
    })
    EmployeeProfileResponseDto toDto(EmployeeProfile employeeProfile);

    @AfterMapping
    default void setSkillAndLanguageIds(
            @MappingTarget EmployeeProfileResponseDto employeeProfileResponseDto,
            EmployeeProfile employeeProfile
    ) {
        Set<Long> skillIds = employeeProfile.getSkills().stream()
                .map(Skill::getId)
                .collect(Collectors.toSet());

        Set<Long> languageIds = employeeProfile.getLanguages().stream()
                .map(Language::getId)
                .collect(Collectors.toSet());

        employeeProfileResponseDto.setSkillIds(skillIds);
        employeeProfileResponseDto.setLanguageIds(languageIds);
    }

    @Mappings({
            @Mapping(
                    target = "user",
                    source = "userId",
                    qualifiedByName = "getUserFromId"
            ),
            @Mapping(target = "skills",
                    source = "skillIds",
                    qualifiedByName = "getSkillsFromIds"
            ),
            @Mapping(
                    target = "languages",
                    source = "languageIds",
                    qualifiedByName = "getLanguagesFromIds"
            )
    })
    EmployeeProfile toModel(EmployeeProfileDto employeeProfileDto);

    @Named("getUserFromId")
    default User getUserFromId(Long id) {
        return new User().setId(id);
    }

    @Named("getSkillsFromIds")
    default Set<Skill> getSkillsFromIds(Set<Long> skillIds) {
        return skillIds.stream()
                .map(i -> new Skill().setId(i))
                .collect(Collectors.toSet());
    }

    @Named("getLanguagesFromIds")
    default Set<Language> getLanguagesFromIds(Set<Long> languageIds) {
        return languageIds.stream()
                .map(i -> new Language().setId(i))
                .collect(Collectors.toSet());
    }
}
