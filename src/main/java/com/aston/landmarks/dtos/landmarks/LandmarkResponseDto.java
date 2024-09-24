package com.aston.landmarks.dtos.landmarks;

import com.aston.landmarks.dtos.localities.LocalityResponseDto;
import com.aston.landmarks.dtos.services.ServiceResponseDto;

import java.sql.Date;
import java.util.List;

public record LandmarkResponseDto(
        Long id,
        String name,
        String description,
        Date dateCreated,
        String attraction,
        LocalityResponseDto locality,
        List<ServiceResponseDto> services
) {
}
