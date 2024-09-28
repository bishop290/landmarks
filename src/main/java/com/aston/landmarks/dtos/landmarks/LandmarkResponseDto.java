package com.aston.landmarks.dtos.landmarks;

import com.aston.landmarks.dtos.localities.LocalityResponseDto;
import com.aston.landmarks.dtos.services.ServiceResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class LandmarkResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private Date date;
    private final LocalDate dateCreated;
    private final String attraction;
    private final LocalityResponseDto locality;
    @Setter
    private List<ServiceResponseDto> services;
}
