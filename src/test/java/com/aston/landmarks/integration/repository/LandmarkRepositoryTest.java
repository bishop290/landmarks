package com.aston.landmarks.integration.repository;

import com.aston.landmarks.integration.DbHelper;
import com.aston.landmarks.integration.IntegrationTest;
import com.aston.landmarks.model.Landmark;
import com.aston.landmarks.model.Locality;
import com.aston.landmarks.model.TypeOfAttraction;
import com.aston.landmarks.repository.LandmarkRepository;
import com.aston.landmarks.repository.LocalityRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
@RequiredArgsConstructor
@DisplayName("Landmark repository integration test")
class LandmarkRepositoryTest {
    private final LandmarkRepository landmarkRepository;
    private final LocalityRepository localityRepository;
    private final EntityManager entityManager;
    private final DataSource dataSource;

    @Test
    @DisplayName("Save landmark to db")
    void testSaveToDb() {
        Long id = 1L;
        String name = "Castle";
        String description = "Simple castle";
        Long date = 1727014029841L;

        Locality locality = Locality.builder()
                .name("Москва")
                .populationSize(20000)
                .metro(true)
                .build();
        localityRepository.save(locality);
        entityManager.flush();

        Landmark landmark = Landmark.builder()
                .name(name)
                .description(description)
                .dateCreated(new Date(date))
                .attraction(TypeOfAttraction.CASTLE)
                .locality(locality)
                .build();
        landmarkRepository.save(landmark);
        entityManager.flush();
        entityManager.detach(locality);
        entityManager.detach(landmark);

        Landmark dbLandmark = DbHelper.get(Landmark.class, dataSource);
        assertEquals(id, dbLandmark.getId());
        assertEquals(name, dbLandmark.getName());
        assertEquals(description, dbLandmark.getDescription());
    }
}
