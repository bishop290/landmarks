package com.aston.landmarks.dtos.landmarks;

import com.aston.landmarks.model.TypeOfAttraction;

import java.util.List;

public record FilterByTypeSortDto(
        TypeOfAttraction name,
        List<String> sort) {
}