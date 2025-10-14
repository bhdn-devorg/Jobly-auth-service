package dev.bhdn.jobly.auth.service.controller;

import dev.bhdn.jobly.auth.service.dto.skill.SkillDto;
import dev.bhdn.jobly.auth.service.dto.skill.SkillResponseDto;
import dev.bhdn.jobly.auth.service.service.SkillService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/skills")
public class SkillController {
    private final SkillService skillService;

    @GetMapping
    public List<SkillResponseDto> getSkills(Pageable pageable) {
        return skillService.getAllSkills(pageable);
    }

    @GetMapping("/{id}")
    public SkillResponseDto getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    @PostMapping
    public SkillResponseDto addSkill(@RequestBody SkillDto skillDto) {
        return skillService.addSkill(skillDto);
    }

    @PutMapping("/{id}")
    public SkillResponseDto updateSkill(
            @PathVariable Long id, @RequestBody SkillDto skillDto
    ) {
        return skillService.updateSkill(id, skillDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSkill(@PathVariable Long id) {
        skillService.deleteSkillById(id);
    }
}
