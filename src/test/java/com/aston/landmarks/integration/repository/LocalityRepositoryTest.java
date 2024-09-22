package com.aston.landmarks.integration.repository;

import com.aston.landmarks.integration.DbHelper;
import com.aston.landmarks.integration.IntegrationTest;
import com.aston.landmarks.integration.TestContainer;
import com.aston.landmarks.model.Locality;
import com.aston.landmarks.repository.LocalityRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
@RequiredArgsConstructor
@DisplayName("Locality repository integration test")
class LocalityRepositoryTest extends TestContainer {
    private final LocalityRepository localityRepository;
    private final EntityManager entityManager;
    private final DataSource dataSource;

    @Test
    @DisplayName("Save locality to db")
    void testSaveToDb() {
        Long id = 1L;
        String city = "Москва";
        Integer population = 200000;
        Boolean metro = true;

        Locality locality = Locality.builder()
                .name(city)
                .populationSize(population)
                .metro(metro)
                .build();
        localityRepository.save(locality);
        entityManager.flush();
        entityManager.detach(locality);

        Locality dbLocality = DbHelper.get(Locality.class, dataSource);
        assertEquals(id, dbLocality.getId());
        assertEquals(city, dbLocality.getName());
        assertEquals(population, dbLocality.getPopulationSize());
        assertEquals(metro, dbLocality.getMetro());
    }
}