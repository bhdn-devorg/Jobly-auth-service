package dev.bhdn.jobly.auth.service.controller;

import dev.bhdn.jobly.auth.service.dto.laguage.LanguageDto;
import dev.bhdn.jobly.auth.service.dto.laguage.LanguageResponseDto;
import dev.bhdn.jobly.auth.service.service.LanguageService;
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
@RequiredArgsConstructor
@RequestMapping("/languages")
public class LanguageController {
    private final LanguageService languageService;

    @PostMapping
    public LanguageResponseDto addLanguage(@RequestBody LanguageDto languageDto) {
        return languageService.addLanguage(languageDto);
    }

    @GetMapping
    public List<LanguageResponseDto> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    @GetMapping("/{id}")
    public LanguageResponseDto getLanguageById(@PathVariable Long id) {
        return languageService.getLanguageById(id);
    }

    @PutMapping("/{id}")
    public LanguageResponseDto updateLanguage(
            @PathVariable Long id, @RequestBody LanguageDto languageDto
    ) {
        return languageService.updateLanguage(id, languageDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLanguage(@PathVariable Long id) {
        languageService.deleteLanguageById(id);
    }
}
