package dev.bhdn.jobly.auth.service.service.impl;

import dev.bhdn.jobly.auth.service.dto.recruiter.RecruiterProfileDto;
import dev.bhdn.jobly.auth.service.dto.recruiter.RecruiterProfileResponseDto;
import dev.bhdn.jobly.auth.service.mapper.RecruiterProfileMapper;
import dev.bhdn.jobly.auth.service.model.RecruiterProfile;
import dev.bhdn.jobly.auth.service.repository.RecruiterProfileRepository;
import dev.bhdn.jobly.auth.service.service.RecruiterProfileService;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruiterProfileImpl implements RecruiterProfileService {
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final RecruiterProfileMapper recruiterProfileMapper;

    @Override
    public RecruiterProfileResponseDto createRecruiterProfile(
            RecruiterProfileDto profileDto
    ) {
        RecruiterProfile recruiterProfile = recruiterProfileMapper.toModel(profileDto);
        return recruiterProfileMapper.toDto(recruiterProfileRepository.save(recruiterProfile));
    }

    @Override
    public List<RecruiterProfileResponseDto> getAllRecruiterProfiles() {
        return recruiterProfileRepository.findAll().stream()
                .map(recruiterProfileMapper::toDto)
                .toList();
    }

    @Override
    public RecruiterProfileResponseDto getRecruiterProfileById(Long id) {
        RecruiterProfile recruiterProfile = recruiterProfileRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return recruiterProfileMapper.toDto(recruiterProfile);
    }

    @Override
    public RecruiterProfileResponseDto updateRecruiterProfile(
            Long id, RecruiterProfileDto profileDto
    ) {
        RecruiterProfile recruiterProfile = recruiterProfileRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        RecruiterProfile updatedRecruiterProfile = recruiterProfileMapper.toModel(profileDto);
        updatedRecruiterProfile.setId(recruiterProfile.getId());

        return recruiterProfileMapper.toDto(
                recruiterProfileRepository.save(updatedRecruiterProfile)
        );
    }

    @Override
    public void deleteRecruiterProfile(Long id) {
        RecruiterProfile recruiterProfile = recruiterProfileRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        recruiterProfileRepository.delete(recruiterProfile);
    }
}
