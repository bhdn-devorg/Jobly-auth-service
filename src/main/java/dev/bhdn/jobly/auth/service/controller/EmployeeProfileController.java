package dev.bhdn.jobly.auth.service.controller;

import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileDto;
import dev.bhdn.jobly.auth.service.dto.employee.EmployeeProfileResponseDto;
import dev.bhdn.jobly.auth.service.service.EmployeeProfileService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles/employee")
@RequiredArgsConstructor
public class EmployeeProfileController {
    private final EmployeeProfileService employeeProfileService;

    @PostMapping
    public EmployeeProfileResponseDto createEmployeeProfile(
            @RequestBody EmployeeProfileDto employeeProfileDto
    ) {
        return employeeProfileService.createEmployeeProfile(employeeProfileDto);
    }

    @GetMapping
    public List<EmployeeProfileResponseDto> getAllEmployeeProfiles() {
        return employeeProfileService.getAllEmployeeProfiles();
    }

    @GetMapping("/{id}")
    public EmployeeProfileResponseDto getEmployeeProfileById(@PathVariable Long id) {
        return employeeProfileService.getEmployeeProfileById(id);
    }

    @PutMapping("/{id}")
    public EmployeeProfileResponseDto updateEmployeeProfile(
            @PathVariable Long id,
            @RequestBody EmployeeProfileDto employeeProfileDto
    ) {
        return employeeProfileService.updateEmployeeProfile(id, employeeProfileDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeProfile(@PathVariable Long id) {
        employeeProfileService.deleteEmployeeProfile(id);
    }
}
