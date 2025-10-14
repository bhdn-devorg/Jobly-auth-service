package dev.bhdn.jobly.auth.service.service;

import dev.bhdn.jobly.auth.service.dto.skill.SkillDto;
import dev.bhdn.jobly.auth.service.dto.skill.SkillResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface SkillService {
    SkillResponseDto addSkill(SkillDto skillDto);

    List<SkillResponseDto> getAllSkills(Pageable pageable);

    SkillResponseDto getSkillById(Long id);

    SkillResponseDto updateSkill(Long id, SkillDto skillDto);

    void deleteSkillById(Long id);
}
