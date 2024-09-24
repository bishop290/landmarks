package com.aston.landmarks.mappers;

import com.aston.landmarks.dtos.localities.LocalityCreateDto;
import com.aston.landmarks.model.Locality;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocalityCreateDtoMapper {
    @Mapping(target = "id", ignore = true)
    Locality dtoToEntity(LocalityCreateDto localityDto);
}
