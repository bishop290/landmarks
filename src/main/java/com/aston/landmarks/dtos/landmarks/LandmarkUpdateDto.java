package com.aston.landmarks.dtos.landmarks;

import org.hibernate.validator.constraints.Length;

public record LandmarkUpdateDto(
        @Length(min = 0, max = 200, message = "Landmark field \"description\" must be correct length")
        String description) {
}
