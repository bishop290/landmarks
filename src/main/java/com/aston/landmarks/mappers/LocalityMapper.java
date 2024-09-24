package com.aston.landmarks.mappers;

import com.aston.landmarks.dtos.localities.LocalityDto;
import com.aston.landmarks.model.Locality;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface LocalityMapper {

    @Mapping(target = "id", ignore = true)
    Locality dtoToEntity(LocalityDto localityDto);
}
