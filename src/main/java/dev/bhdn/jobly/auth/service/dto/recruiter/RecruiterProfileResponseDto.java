package dev.bhdn.jobly.auth.service.dto.recruiter;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RecruiterProfileResponseDto {
    private Long id;
    private Long userId;
    private Long companyId;
}
