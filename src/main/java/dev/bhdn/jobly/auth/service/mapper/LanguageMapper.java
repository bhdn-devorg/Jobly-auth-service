package dev.bhdn.jobly.auth.service.mapper;

import dev.bhdn.jobly.auth.service.config.MapperConfig;
import dev.bhdn.jobly.auth.service.dto.laguage.LanguageDto;
import dev.bhdn.jobly.auth.service.dto.laguage.LanguageResponseDto;
import dev.bhdn.jobly.auth.service.model.Language;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface LanguageMapper {
    Language toModel(LanguageDto languageDto);

    LanguageResponseDto toDto(Language language);
}
