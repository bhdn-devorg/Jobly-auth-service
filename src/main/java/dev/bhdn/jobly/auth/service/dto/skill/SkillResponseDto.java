package dev.bhdn.jobly.auth.service.dto.skill;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SkillResponseDto {
    private Long id;
    private String name;
}
