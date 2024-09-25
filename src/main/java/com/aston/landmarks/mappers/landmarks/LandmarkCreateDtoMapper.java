package com.aston.landmarks.mappers.landmarks;

import com.aston.landmarks.dtos.landmarks.LandmarkCreateDto;
import com.aston.landmarks.model.Landmark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LandmarkCreateDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "locality", ignore = true)
    @Mapping(target = "services", ignore = true)
    Landmark dtoToEntity(LandmarkCreateDto landmarkDto);
}
