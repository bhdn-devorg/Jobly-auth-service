package dev.bhdn.jobly.auth.service.service;

import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileDto;
import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileResponseDto;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeProfileService {
    EmployeeProfileResponseDto createEmployeeProfile(
            EmployeeProfileDto profileDto, MultipartFile photo
    );

    List<EmployeeProfileResponseDto> getAllEmployeeProfiles();

    EmployeeProfileResponseDto getEmployeeProfileById(Long id);

    EmployeeProfileResponseDto updateEmployeeProfile(
            Long id, EmployeeProfileDto profileDto, MultipartFile photo
    );

    void deleteEmployeeProfile(Long id);
}
