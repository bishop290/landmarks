package com.aston.landmarks.dtos.localities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record LocalityCreateDto(
        @NotEmpty(message = "Locality field \"name\" must be filled in")
        @Length(min = 1, max = 100, message = "Locality field \"name\" must be correct length")
        String name,

        @Min(value = 0, message = "Locality field \"populationSize\" must be correct size")
        @Max(value = Integer.MAX_VALUE, message = "Locality field \"populationSize\" must be correct size")
        Integer populationSize,

        @NotNull(message = "Locality field \"metro\" must be not null")
        Boolean metro) {
}
