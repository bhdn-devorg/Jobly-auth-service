package dev.bhdn.jobly.auth.service.service.impl;

import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileDto;
import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileResponseDto;
import dev.bhdn.jobly.auth.service.dto.storage.PhotoResultDto;
import dev.bhdn.jobly.auth.service.mapper.EmployeeProfileMapper;
import dev.bhdn.jobly.auth.service.model.EmployeeProfile;
import dev.bhdn.jobly.auth.service.repository.EmployeeProfileRepository;
import dev.bhdn.jobly.auth.service.service.DropboxStorageService;
import dev.bhdn.jobly.auth.service.service.EmployeeProfileService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class EmployeeProfileServiceImpl implements EmployeeProfileService {
    private static final String FOLDER_PATH = "/Apps/Jobly/employees/";
    private final EmployeeProfileRepository employeeProfileRepository;
    private final EmployeeProfileMapper employeeProfileMapper;
    private final DropboxStorageService dropboxStorageService;

    @Override
    public EmployeeProfileResponseDto createEmployeeProfile(
            EmployeeProfileDto profileDto, MultipartFile photo
    ) {
        EmployeeProfile employeeProfile = employeeProfileMapper.toModel(profileDto);
        String uniquePath = dropboxStorageService.generateUniquePath(FOLDER_PATH);

        if (!photo.isEmpty()) {
            PhotoResultDto photoResultDto = dropboxStorageService.uploadPhoto(
                    photo, uniquePath
            );

            employeeProfile.setLogoLink(photoResultDto.getLogoLink());
            employeeProfile.setLogoPath(photoResultDto.getLogoPath());
        }

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
            Long id, EmployeeProfileDto profileDto, MultipartFile photo
    ) {
        EmployeeProfile employeeProfile = employeeProfileRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        EmployeeProfile updatedEmployeeProfile = employeeProfileMapper.toModel(profileDto);
        updatedEmployeeProfile.setId(employeeProfile.getId());

        if (!photo.isEmpty()) {
            String uniquePath = dropboxStorageService.generateUniquePath(FOLDER_PATH);
            PhotoResultDto photoResultDto = dropboxStorageService.updatePhoto(
                    photo, employeeProfile.getLogoPath(), uniquePath
            );

            updatedEmployeeProfile.setLogoPath(photoResultDto.getLogoPath());
            updatedEmployeeProfile.setLogoLink(photoResultDto.getLogoLink());
        } else {
            updatedEmployeeProfile.setLogoPath(employeeProfile.getLogoPath());
            updatedEmployeeProfile.setLogoLink(employeeProfile.getLogoLink());
        }

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
