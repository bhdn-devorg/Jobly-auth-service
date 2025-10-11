package dev.bhdn.jobly.auth.service.dto.laguage;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LanguageDto {
    private String name;
}
