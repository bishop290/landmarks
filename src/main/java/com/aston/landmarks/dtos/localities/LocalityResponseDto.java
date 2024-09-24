package com.aston.landmarks.dtos.localities;

public record LocalityResponseDto(
        Long id,
        String name,
        Integer populationSize,
        Boolean metro) {
}