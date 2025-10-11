package dev.bhdn.jobly.auth.service.dto.company;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CompanyDto {
    private String name;
    private String logoLink;
    private String websiteLink;
    private String linkedinLink;
}
