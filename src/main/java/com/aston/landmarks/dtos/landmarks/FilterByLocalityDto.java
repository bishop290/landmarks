package com.aston.landmarks.dtos.landmarks;

import jakarta.validation.constraints.NotEmpty;

public record FilterByLocalityDto(
        @NotEmpty(message = "Filter must be filled in")
        String name) {
}
