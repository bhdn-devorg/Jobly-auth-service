package dev.bhdn.jobly.auth.service.dto.company;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CompanyResponseDto {
    private Long id;
    private String name;
    private String logoLink;
    private String websiteLink;
    private String linkedinLink;
}
