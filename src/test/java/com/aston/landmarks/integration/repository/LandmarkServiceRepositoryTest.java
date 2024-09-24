package com.aston.landmarks.integration.repository;

import com.aston.landmarks.integration.DbHelper;
import com.aston.landmarks.integration.IntegrationTest;
import com.aston.landmarks.integration.TestContainer;
import com.aston.landmarks.model.*;
import com.aston.landmarks.repository.LandmarkRepository;
import com.aston.landmarks.repository.LandmarkServiceRepository;
import com.aston.landmarks.repository.LocalityRepository;
import com.aston.landmarks.repository.ServiceRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@IntegrationTest
@RequiredArgsConstructor
@DisplayName("LandmarkService repository integration test")
class LandmarkServiceRepositoryTest extends TestContainer {
    private final LandmarkRepository landmarkRepository;
    private final LocalityRepository localityRepository;
    private final ServiceRepository serviceRepository;
    private final LandmarkServiceRepository landmarkServiceRepository;
    private final EntityManager entityManager;

    @Test
    @DisplayName("Save LandmarkService to db")
    void testSaveToDb() {
        String nameLandmark = "Castle";
        String nameLocality = "Москва";
        String nameService = "Cafe";
        String descriptionService = "Simple cafe";
        int population = 20000;
        String descriptionLandmark = "Simple castle";
        long date = 1727014029841L;

        Locality locality = DbHelper.create(nameLocality, population, true);
        localityRepository.save(locality);
        entityManager.flush();

        Landmark landmark = DbHelper.create(
                nameLandmark, descriptionLandmark, new Date(date), TypeOfAttraction.CASTLE, locality);
        landmarkRepository.save(landmark);
        entityManager.flush();

        Service service = DbHelper.create(nameService, descriptionService);
        serviceRepository.save(service);
        entityManager.flush();

        LandmarkService landmarkService = DbHelper.create(landmark, service);
        landmarkServiceRepository.save(landmarkService);
        entityManager.flush();
        long id = landmarkService.getId();

        entityManager.detach(locality);
        entityManager.detach(landmark);
        entityManager.detach(service);
        entityManager.detach(landmarkService);

        Optional<LandmarkService> maybeLandmarkService = landmarkServiceRepository.findById(id);
        assertTrue(maybeLandmarkService.isPresent());
    }
}
