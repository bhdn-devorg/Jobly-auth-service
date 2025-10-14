package dev.bhdn.jobly.auth.service.service.impl;

import dev.bhdn.jobly.auth.service.dto.skill.SkillDto;
import dev.bhdn.jobly.auth.service.dto.skill.SkillResponseDto;
import dev.bhdn.jobly.auth.service.mapper.SkillMapper;
import dev.bhdn.jobly.auth.service.model.Skill;
import dev.bhdn.jobly.auth.service.repository.SkillRepository;
import dev.bhdn.jobly.auth.service.service.SkillService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    @Override
    public SkillResponseDto addSkill(SkillDto skillDto) {
        Skill skill = skillMapper.toModel(skillDto);
        return skillMapper.toDto(skillRepository.save(skill));
    }

    @Override
    public List<SkillResponseDto> getAllSkills(Pageable pageable) {
        return skillRepository.findAll(pageable).stream()
                .map(skillMapper::toDto)
                .toList();
    }

    @Override
    public SkillResponseDto getSkillById(Long id) {
        Skill skill = skillRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return skillMapper.toDto(skill);
    }

    @Override
    public SkillResponseDto updateSkill(Long id, SkillDto skillDto) {
        Skill skill = skillRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Skill updatedSkill = skillMapper.toModel(skillDto);
        updatedSkill.setId(skill.getId());

        return skillMapper.toDto(skillRepository.save(updatedSkill));
    }

    @Override
    public void deleteSkillById(Long id) {
        Skill skill = skillRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        skillRepository.delete(skill);
    }
}
