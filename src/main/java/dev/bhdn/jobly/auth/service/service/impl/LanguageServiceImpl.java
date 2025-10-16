package dev.bhdn.jobly.auth.service.service.impl;

import dev.bhdn.jobly.auth.service.dto.laguage.LanguageDto;
import dev.bhdn.jobly.auth.service.dto.laguage.LanguageResponseDto;
import dev.bhdn.jobly.auth.service.mapper.LanguageMapper;
import dev.bhdn.jobly.auth.service.model.Language;
import dev.bhdn.jobly.auth.service.repository.LanguageRepository;
import dev.bhdn.jobly.auth.service.service.LanguageService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    @Override
    public LanguageResponseDto addLanguage(LanguageDto languageDto) {
        Language language = languageMapper.toModel(languageDto);
        return languageMapper.toDto(languageRepository.save(language));
    }

    @Override
    public List<LanguageResponseDto> getAllLanguages() {
        return languageRepository.findAll().stream()
                .map(languageMapper::toDto)
                .toList();
    }

    @Override
    public LanguageResponseDto getLanguageById(Long id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return languageMapper.toDto(language);
    }

    @Override
    public LanguageResponseDto updateLanguage(Long id, LanguageDto languageDto) {
        Language language = languageRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        Language updatedLanguage = languageMapper.toModel(languageDto);
        updatedLanguage.setId(language.getId());

        return languageMapper.toDto(languageRepository.save(updatedLanguage));
    }

    @Override
    public void deleteLanguageById(Long id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        languageRepository.delete(language);
    }
}
