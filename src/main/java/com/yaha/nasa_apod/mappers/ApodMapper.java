package com.yaha.nasa_apod.mappers;

import com.yaha.nasa_apod.model.dto.ApodDto;
import com.yaha.nasa_apod.model.entities.Apod;
import com.yaha.nasa_apod.model.entities.ApodTranslation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Locale;

@Mapper(componentModel = "spring")
public interface ApodMapper {

    ApodDto toDto(Apod apod);

    @Mapping(target = "title", source = "apodTranslation.title")
    @Mapping(target = "explanation", source = "apodTranslation.explanation")
    ApodDto toLocalizedDto(Apod apod, ApodTranslation apodTranslation);

    @Mapping(target = "translations", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Apod toApod(ApodDto apodDto);

    @Mapping(target = "apod", source = "apod")
    @Mapping(target = "locale", source = "locale.language")
    @Mapping(target = "explanation", source = "apodDto.explanation")
    @Mapping(target = "title", source = "apodDto.title")

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ApodTranslation toTranslation(ApodDto apodDto, Locale locale, Apod apod);

}
