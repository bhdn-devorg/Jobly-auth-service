package dev.bhdn.jobly.auth.service.mapper;

import dev.bhdn.jobly.auth.service.config.MapperConfig;
import dev.bhdn.jobly.auth.service.dto.skill.SkillDto;
import dev.bhdn.jobly.auth.service.dto.skill.SkillResponseDto;
import dev.bhdn.jobly.auth.service.model.Skill;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface SkillMapper {
    Skill toModel(SkillDto skillDto);

    SkillResponseDto toDto(Skill skill);
}
