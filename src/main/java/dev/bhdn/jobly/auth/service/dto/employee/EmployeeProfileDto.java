package dev.bhdn.jobly.auth.service.dto.employee;

import java.util.Set;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EmployeeProfileDto {
    private Long userId;
    private Set<Long> skillIds;
    private Set<Long> languageIds;
    private int salaryExpectations;
    private String description;
    private String phoneNumber;
    private String linkedinLink;
    private String githubLink;
}
