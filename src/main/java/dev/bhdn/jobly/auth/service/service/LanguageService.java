package dev.bhdn.jobly.auth.service.service;

import dev.bhdn.jobly.auth.service.dto.laguage.LanguageDto;
import dev.bhdn.jobly.auth.service.dto.laguage.LanguageResponseDto;
import java.util.List;

public interface LanguageService {
    LanguageResponseDto addLanguage(LanguageDto languageDto);

    List<LanguageResponseDto> getAllLanguages();

    LanguageResponseDto getLanguageById(Long id);

    LanguageResponseDto updateLanguage(Long id, LanguageDto languageDto);

    void deleteLanguageById(Long id);
}
