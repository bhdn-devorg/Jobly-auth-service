package dev.bhdn.jobly.auth.service.service;

import dev.bhdn.jobly.auth.service.dto.recruiter.RecruiterProfileDto;
import dev.bhdn.jobly.auth.service.dto.recruiter.RecruiterProfileResponseDto;
import java.util.List;

public interface RecruiterProfileService {
    RecruiterProfileResponseDto createRecruiterProfile(RecruiterProfileDto profileDto);

    List<RecruiterProfileResponseDto> getAllRecruiterProfiles();

    RecruiterProfileResponseDto getRecruiterProfileById(Long id);

    RecruiterProfileResponseDto updateRecruiterProfile(Long id, RecruiterProfileDto profileDto);

    void deleteRecruiterProfile(Long id);
}
