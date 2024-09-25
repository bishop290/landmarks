package com.aston.landmarks.mappers.landmarks;

import com.aston.landmarks.dtos.landmarks.LandmarkResponseDto;
import com.aston.landmarks.dtos.services.ServiceResponseDto;
import com.aston.landmarks.model.Landmark;
import com.aston.landmarks.model.LandmarkService;
import com.aston.landmarks.model.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LandmarkResponseDtoMapper {

    LandmarkResponseDtoMapper INSTANCE = Mappers.getMapper(LandmarkResponseDtoMapper.class);

    @Mapping(target = "services", ignore = true)
    LandmarkResponseDto entityToDto(Landmark landmark);

    ServiceResponseDto entityToDto(Service service);

    default List<LandmarkResponseDto> entitiesToDtoList(List<Landmark> landmarks) {
        List<LandmarkResponseDto> result = new ArrayList<>();
        for (Landmark landmark : landmarks) {
            List<ServiceResponseDto> services = new ArrayList<>();
            for (LandmarkService landmarkService : landmark.getServices()) {
                services.add(entityToDto(landmarkService.getService()));
            }
            LandmarkResponseDto dto = entityToDto(landmark);
            dto.setServices(services);
            result.add(dto);
        }
        return result;
    }
}
