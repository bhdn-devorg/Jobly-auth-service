package dev.bhdn.jobly.auth.service.controller;

import dev.bhdn.jobly.auth.service.dto.recruiter.RecruiterProfileDto;
import dev.bhdn.jobly.auth.service.dto.recruiter.RecruiterProfileResponseDto;
import dev.bhdn.jobly.auth.service.service.RecruiterProfileService;
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
@RequestMapping("/profiles/recruiter")
@RequiredArgsConstructor
public class RecruiterProfileController {
    private final RecruiterProfileService recruiterProfileService;

    @PostMapping
    public RecruiterProfileResponseDto createRecruiterProfile(
            @RequestBody RecruiterProfileDto recruiterProfileDto
    ) {
        return recruiterProfileService.createRecruiterProfile(recruiterProfileDto);
    }

    @GetMapping
    public List<RecruiterProfileResponseDto> getAllRecruiterProfiles() {
        return recruiterProfileService.getAllRecruiterProfiles();
    }

    @GetMapping("/{id}")
    public RecruiterProfileResponseDto getRecruiterProfileById(@PathVariable Long id) {
        return recruiterProfileService.getRecruiterProfileById(id);
    }

    @PutMapping("/{id}")
    public RecruiterProfileResponseDto updateRecruiterProfile(
            @PathVariable Long id,
            @RequestBody RecruiterProfileDto recruiterProfileDto
    ) {
        return recruiterProfileService.updateRecruiterProfile(id, recruiterProfileDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecruiterProfile(@PathVariable Long id) {
        recruiterProfileService.deleteRecruiterProfile(id);
    }
}
