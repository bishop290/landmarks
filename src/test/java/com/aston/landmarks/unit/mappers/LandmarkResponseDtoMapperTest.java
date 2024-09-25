package com.aston.landmarks.unit.mappers;

import com.aston.landmarks.ObjectBuilder;
import com.aston.landmarks.dtos.landmarks.LandmarkResponseDto;
import com.aston.landmarks.mappers.landmarks.LandmarkResponseDtoMapper;
import com.aston.landmarks.model.Landmark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LandmarkResponseDto mapper test")
class LandmarkResponseDtoMapperTest {

    @Test
    @DisplayName("Create list of landmarksResponseDto")
    void testUpdateLandmarkWithNotValidId() {
        int size = 5;
        int servicesSize = 1;
        String firstServiceName = "test-0";
        List<Landmark> entities = ObjectBuilder.create(size);

        List<LandmarkResponseDto> landmarks = LandmarkResponseDtoMapper.INSTANCE.entitiesToDtoList(entities);

        assertEquals(size, landmarks.size());
        assertEquals(servicesSize, landmarks.get(0).getServices().size());
        assertEquals(firstServiceName, landmarks.get(0).getServices().get(0).name());
    }
}
