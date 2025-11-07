package dev.bhdn.jobly.auth.service.service;

import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileDto;
import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileResponseDto;
import java.util.List;
import java.util.Optional;

public interface EmployeeProfileService {
    EmployeeProfileResponseDto createEmployeeProfile(EmployeeProfileDto profileDto);

    List<EmployeeProfileResponseDto> getAllEmployeeProfiles();

    EmployeeProfileResponseDto getEmployeeProfileById(Long id);

    EmployeeProfileResponseDto updateEmployeeProfile(Long id, EmployeeProfileDto profileDto);

    void deleteEmployeeProfile(Long id);
}
