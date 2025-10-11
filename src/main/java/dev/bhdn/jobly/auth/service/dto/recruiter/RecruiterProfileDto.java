package dev.bhdn.jobly.auth.service.dto.recruiter;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RecruiterProfileDto {
    private Long userId;
    private Long companyId;
}
