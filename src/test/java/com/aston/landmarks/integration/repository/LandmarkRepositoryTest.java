package com.aston.landmarks.integration.repository;

import com.aston.landmarks.ObjectBuilder;
import com.aston.landmarks.integration.DbHelper;
import com.aston.landmarks.integration.IntegrationTest;
import com.aston.landmarks.integration.TestContainer;
import com.aston.landmarks.model.*;
import com.aston.landmarks.repository.LandmarkRepository;
import com.aston.landmarks.repository.LandmarkServiceRepository;
import com.aston.landmarks.repository.LocalityRepository;
import com.aston.landmarks.repository.ServiceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
@RequiredArgsConstructor
@DisplayName("Landmark repository integration test")
class LandmarkRepositoryTest extends TestContainer {
    private final LandmarkRepository landmarkRepository;
    private final LocalityRepository localityRepository;
    private final ServiceRepository serviceRepository;
    private final LandmarkServiceRepository landmarkServiceRepository;
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;
    private final DataSource dataSource;

    @Test
    @DisplayName("Save landmark to db")
    void testSaveToDb() {
        String nameLandmark = "Castle";
        String nameLocality = "Москва";
        int population = 20000;
        String description = "Simple castle";
        long date = 1727014029841L;

        Locality locality = ObjectBuilder.create(nameLocality, population, true);
        localityRepository.save(locality);
        entityManager.flush();

        Landmark landmark = ObjectBuilder.create(
                nameLandmark, description, new Date(date), TypeOfAttraction.CASTLE, locality);
        landmarkRepository.save(landmark);
        entityManager.flush();
        entityManager.detach(locality);
        entityManager.detach(landmark);

        Landmark dbLandmark = DbHelper.get(Landmark.class, dataSource);
        assertEquals(nameLandmark, dbLandmark.getName());
        assertEquals(description, dbLandmark.getDescription());
    }

    @Test
    @DisplayName("Delete landmark")
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    void testDelete() {
        String nameLandmark = "Castle";
        String nameLocality = "Москва";
        String nameService = "Cafe";
        String descriptionService = "Simple cafe";
        int population = 20000;
        String descriptionLandmark = "Simple castle";
        long date = 1727014029841L;

        EntityManager manager = entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();

        Locality locality = ObjectBuilder.create(nameLocality, population, true);
        localityRepository.saveAndFlush(locality);

        Landmark landmark = ObjectBuilder.create(
                nameLandmark, descriptionLandmark, new Date(date), TypeOfAttraction.CASTLE, locality);
        landmarkRepository.saveAndFlush(landmark);
        Long landmarkId = landmark.getId();

        Service service = ObjectBuilder.create(nameService, descriptionService);
        serviceRepository.saveAndFlush(service);
        Long serviceId = service.getId();

        LandmarkService landmarkService = ObjectBuilder.create(landmark, service);
        landmarkServiceRepository.saveAndFlush(landmarkService);
        Long landmarkServiceId = landmarkService.getId();
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        Optional<Landmark> maybeLandmark = landmarkRepository.findById(landmarkId);
        landmarkRepository.delete(maybeLandmark.get());
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        Optional<Landmark> maybeLandmark2 = landmarkRepository.findById(landmarkId);
        Optional<Service> maybeService = serviceRepository.findById(serviceId);
        Optional<LandmarkService> maybeLandmarkService = landmarkServiceRepository.findById(landmarkServiceId);

        assertTrue(maybeService.isPresent());
        assertFalse(maybeLandmark2.isPresent());
        assertFalse(maybeLandmarkService.isPresent());
        manager.getTransaction().commit();
    }
}