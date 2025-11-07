package dev.bhdn.jobly.auth.service.service.impl;

import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileDto;
import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileResponseDto;
import dev.bhdn.jobly.auth.service.mapper.EmployeeProfileMapper;
import dev.bhdn.jobly.auth.service.model.EmployeeProfile;
import dev.bhdn.jobly.auth.service.repository.EmployeeProfileRepository;
import dev.bhdn.jobly.auth.service.service.EmployeeProfileService;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeProfileServiceImpl implements EmployeeProfileService {
    private final EmployeeProfileRepository employeeProfileRepository;
    private final EmployeeProfileMapper employeeProfileMapper;

    @Override
    public EmployeeProfileResponseDto createEmployeeProfile(EmployeeProfileDto profileDto) {
        //TODO: Implement Dropbox API for storing profile pictures
        EmployeeProfile employeeProfile = employeeProfileMapper.toModel(profileDto);
        return employeeProfileMapper.toDto(employeeProfileRepository.save(employeeProfile));
    }

    @Override
    public List<EmployeeProfileResponseDto> getAllEmployeeProfiles() {
        return employeeProfileRepository.findAll().stream()
                .map(employeeProfileMapper::toDto)
                .toList();
    }

    @Override
    public EmployeeProfileResponseDto getEmployeeProfileById(Long id) {
        EmployeeProfile employeeProfile = employeeProfileRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return employeeProfileMapper.toDto(employeeProfile);
    }

    @Override
    public EmployeeProfileResponseDto updateEmployeeProfile(
            Long id, EmployeeProfileDto profileDto
    ) {
        //TODO: Implement Dropbox API for storing profile pictures
        EmployeeProfile employeeProfile = employeeProfileRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        EmployeeProfile updatedEmployeeProfile = employeeProfileMapper.toModel(profileDto);
        updatedEmployeeProfile.setId(employeeProfile.getId());

        return employeeProfileMapper.toDto(
                employeeProfileRepository.save(updatedEmployeeProfile)
        );
    }

    @Override
    public void deleteEmployeeProfile(Long id) {
        EmployeeProfile employeeProfile = employeeProfileRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        employeeProfileRepository.delete(employeeProfile);
    }
}
