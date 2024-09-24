package com.aston.landmarks.integration.repository;

import com.aston.landmarks.integration.DbHelper;
import com.aston.landmarks.integration.IntegrationTest;
import com.aston.landmarks.integration.TestContainer;
import com.aston.landmarks.model.Service;
import com.aston.landmarks.repository.ServiceRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
@RequiredArgsConstructor
@DisplayName("Service repository integration test")
class ServiceRepositoryTest extends TestContainer {
    private final ServiceRepository serviceRepository;
    private final EntityManager entityManager;
    private final DataSource dataSource;

    @Test
    @DisplayName("Save service to db")
    void testSaveToDb() {
        String name = "Cafe";
        String description = "Simple cafe";

        Service service = DbHelper.create(name, description);
        serviceRepository.save(service);
        entityManager.flush();
        entityManager.detach(service);

        Service dbService = DbHelper.get(Service.class, dataSource);
        assertEquals(name, dbService.getName());
        assertEquals(description, dbService.getDescription());
    }
}
