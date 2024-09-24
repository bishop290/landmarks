package com.aston.landmarks.dtos.localities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LocalityUpdateDto(

        @Min(value = 0, message = "Locality field \"populationSize\" must be correct size")
        @Max(value = Integer.MAX_VALUE, message = "Locality field \"populationSize\" must be correct size")
        Integer populationSize,

        @NotNull(message = "Locality field \"metro\" must be not null")
        Boolean metro) {
}
