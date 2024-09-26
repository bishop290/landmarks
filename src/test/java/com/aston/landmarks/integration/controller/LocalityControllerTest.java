package com.aston.landmarks.integration.controller;

import com.aston.landmarks.ObjectBuilder;
import com.aston.landmarks.dtos.localities.LocalityCreateDto;
import com.aston.landmarks.dtos.localities.LocalityUpdateDto;
import com.aston.landmarks.integration.IntegrationTest;
import com.aston.landmarks.integration.MockMvcHelper;
import com.aston.landmarks.integration.TestContainer;
import com.aston.landmarks.model.Locality;
import com.aston.landmarks.repository.LocalityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@RequiredArgsConstructor
@DisplayName("Locality controller integration tests")
class LocalityControllerTest extends TestContainer {
    private static final String ADD_MESSAGE = "Locality add successful";
    private static final String UPDATE_MESSAGE = "Locality update successful";

    private final LocalityRepository localityRepository;
    private final EntityManagerFactory entityManagerFactory;
    private final WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Add locality")
    void testAddLocality() throws Exception {
        String name = "Москва";
        int population = 200;
        Boolean metro = true;

        LocalityCreateDto localityDto = new LocalityCreateDto(name, population, metro);
        mockMvc.perform(MockMvcHelper.postJson("/localities", localityDto))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("true"))
                .andExpect(jsonPath("$.message").value(ADD_MESSAGE));
    }

    @Disabled
    @Test
    @DisplayName("Add locality without name")
    void testAddLocalityWithoutName() throws Exception {
        String name = "";
        int population = 200;
        Boolean metro = true;

        LocalityCreateDto localityDto = new LocalityCreateDto(name, population, metro);
        mockMvc.perform(MockMvcHelper.postJson("/localities", localityDto))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("false"));
    }

    @Test
    @DisplayName("Update locality")
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    void testUpdateLocality() throws Exception {
        String name = "Test";
        int population = 666;
        Boolean metro = false;

        EntityManager manager = entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();
        Locality locality = ObjectBuilder.create(name, population, metro);
        localityRepository.save(locality);
        Long id = locality.getId();
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        LocalityUpdateDto localityDto = new LocalityUpdateDto(population, metro);
        mockMvc.perform(MockMvcHelper.putJson("/localities/" + id, localityDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("true"))
                .andExpect(jsonPath("$.message").value(UPDATE_MESSAGE));
        manager.getTransaction().commit();
    }

    @Test
    @DisplayName("Update locality, not valid id")
    void testUpdateLocalityNotValidId() throws Exception {
        int population = 666;
        Boolean metro = false;
        String id = "103332";
        final String message = "Locality with id:" + id + " not found in database";

        LocalityUpdateDto localityDto = new LocalityUpdateDto(population, metro);
        mockMvc.perform(MockMvcHelper.putJson("/localities/" + id, localityDto))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("false"))
                .andExpect(jsonPath("$.message").value(message));
    }
}
