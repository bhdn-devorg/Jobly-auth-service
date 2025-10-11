package dev.bhdn.jobly.auth.service.service;

import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileDto;
import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileResponseDto;
import java.util.List;
import java.util.Optional;

public interface EmployeeProfileService {
    EmployeeProfileResponseDto createEmployeeProfile(EmployeeProfileDto profileDto);

    List<EmployeeProfileResponseDto> getAllEmployeeProfiles();

    Optional<EmployeeProfileResponseDto> getEmployeeProfileById(Long id);

    EmployeeProfileResponseDto updateEmployeeProfile(EmployeeProfileDto profileDto);

    void deleteEmployeeProfile(Long id);
}
