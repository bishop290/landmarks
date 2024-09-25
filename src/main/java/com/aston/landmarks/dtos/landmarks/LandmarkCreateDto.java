package com.aston.landmarks.dtos.landmarks;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;

public record LandmarkCreateDto(

        @NotEmpty(message = "Landmark field \"name\" must be filled in")
        @Length(min = 1, max = 100, message = "Landmark field \"name\" must be correct length")
        String name,

        @Length(min = 0, max = 200, message = "Landmark field \"description\" must be correct length")
        String description,

        Date dateCreated,

        @NotEmpty(message = "Landmark field \"attraction\" must be filled in")
        String attraction,

        @NotNull(message = "Landmark field \"locality\" must be filled in")
        Long locality) {
}
