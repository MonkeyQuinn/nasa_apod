package com.yaha.nasa_apod.mappers;

import com.yaha.nasa_apod.model.dto.ApodDto;
import com.yaha.nasa_apod.model.entities.Apod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApodMapper {

    ApodDto toDto(Apod apod);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Apod toEntity(ApodDto apodDto);

}
