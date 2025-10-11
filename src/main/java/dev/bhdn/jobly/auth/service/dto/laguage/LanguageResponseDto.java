package dev.bhdn.jobly.auth.service.dto.laguage;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LanguageResponseDto {
    private Long id;
    private String name;
}
