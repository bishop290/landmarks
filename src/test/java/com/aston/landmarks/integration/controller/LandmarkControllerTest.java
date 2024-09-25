package com.aston.landmarks.integration.controller;

import com.aston.landmarks.ObjectBuilder;
import com.aston.landmarks.aspect.ExceptionAdvice;
import com.aston.landmarks.controller.LandmarksController;
import com.aston.landmarks.dtos.landmarks.LandmarkCreateDto;
import com.aston.landmarks.dtos.landmarks.LandmarkUpdateDto;
import com.aston.landmarks.integration.IntegrationTest;
import com.aston.landmarks.integration.MockMvcHelper;
import com.aston.landmarks.integration.TestContainer;
import com.aston.landmarks.model.*;
import com.aston.landmarks.repository.LandmarkRepository;
import com.aston.landmarks.repository.LandmarkServiceRepository;
import com.aston.landmarks.repository.LocalityRepository;
import com.aston.landmarks.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@RequiredArgsConstructor
@DisplayName("Landmark controller integration tests")
class LandmarkControllerTest extends TestContainer {
    private final LandmarksController controller;
    private final LocalityRepository localityRepository;
    private final LandmarkRepository landmarkRepository;
    private final ServiceRepository serviceRepository;
    private final LandmarkServiceRepository landmarkServiceRepository;
    private final Validator validator;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new ExceptionAdvice())
                .setValidator(validator)
                .build();
    }

    @Test
    @DisplayName("Add landmark")
    void testAddLandmark() throws Exception {
        String localityName = "Test";
        int population = 666;
        Boolean metro = false;
        String landmarkName = "TestLandmark";
        String description = "TestLandmark - good landmark";
        Date dateCreated = new Date(System.currentTimeMillis());
        String attraction = "CASTLE";
        String message = "Landmark add successful";

        Locality locality = ObjectBuilder.create(localityName, population, metro);
        localityRepository.save(locality);
        Long localityId = locality.getId();

        LandmarkCreateDto landmarkDto = new LandmarkCreateDto(
                landmarkName, description, dateCreated, attraction, localityId);

        mockMvc.perform(MockMvcHelper.postJson("/landmarks", landmarkDto))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("true"))
                .andExpect(jsonPath("$.message").value(message));
    }

    @Test
    @DisplayName("Add landmark with incorrect locality")
    void testAddLandmarkWithIncorrectLocality() throws Exception {
        String landmarkName = "TestLandmark";
        String description = "TestLandmark - good landmark";
        Date dateCreated = new Date(System.currentTimeMillis());
        String attraction = "CASTLE";
        Long localityId = 6374737L;
        String message = "Locality with id:" + localityId + " not found in database";

        LandmarkCreateDto landmarkDto = new LandmarkCreateDto(
                landmarkName, description, dateCreated, attraction, localityId);

        mockMvc.perform(MockMvcHelper.postJson("/landmarks", landmarkDto))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("false"))
                .andExpect(jsonPath("$.message").value(message));
    }

    @Test
    @DisplayName("Update landmark")
    void testUpdateLandmark() throws Exception {
        String localityName = "Test";
        int population = 666;
        Boolean metro = false;
        String landmarkName = "TestLandmark";
        String description = "TestLandmark - good landmark";
        String newDescription = "Bad place";
        Date dateCreated = new Date(System.currentTimeMillis());
        String message = "Landmark update successful";

        Locality locality = ObjectBuilder.create(localityName, population, metro);
        localityRepository.save(locality);
        Landmark landmark = ObjectBuilder.create(landmarkName, description, dateCreated, TypeOfAttraction.CASTLE, locality);
        landmarkRepository.save(landmark);
        Long id = landmark.getId();

        LandmarkUpdateDto landmarkDto = new LandmarkUpdateDto(newDescription);

        mockMvc.perform(MockMvcHelper.putJson("/landmarks/" + id, landmarkDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("true"))
                .andExpect(jsonPath("$.message").value(message));
    }

    @Test
    @DisplayName("Update landmark, not valid id")
    void testUpdateLandmarkWithNotValidId() throws Exception {
        String newDescription = "Bad place";
        long id = 29873429L;
        String message = "Landmark with id:" + id + " not found in database";

        LandmarkUpdateDto landmarkDto = new LandmarkUpdateDto(newDescription);

        mockMvc.perform(MockMvcHelper.putJson("/landmarks/" + id, landmarkDto))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("false"))
                .andExpect(jsonPath("$.message").value(message));
    }

    @Test
    @DisplayName("Delete landmark")
    void testDeleteLandmark() throws Exception {
        long id = 1L;
        String message = "Landmark delete";

        mockMvc.perform(MockMvcHelper.delete("/landmarks/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("true"))
                .andExpect(jsonPath("$.message").value(message));
    }

    @Test
    @DisplayName("Find by locality name")
    void testFindByLocalityName() throws Exception {
        String localityName = "Test";

        createData(localityName);

        mockMvc.perform(get(String.format("/landmarks/filter/locality?name=%s", localityName)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Find by empty locality name")
    void testFindByEmptyLocalityName() throws Exception {
        String localityName = "";
        mockMvc.perform(get(String.format("/landmarks/filter/locality?name=%s", localityName)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Find by type of attraction")
    void testFindByTypeOfAttraction() throws Exception {
        String localityName = "Test";
        String attraction = "CASTLE";

        createData(localityName);

        mockMvc.perform(get(String.format(
                        "/landmarks/filter/type?name=%s&sort=name,asc", attraction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
        System.out.println();
    }

    private void createData(String localityName) {
        int population = 666;
        Boolean metro = false;
        String landmarkName = "TestLandmark";
        String description = "TestLandmark - good landmark";
        Date dateCreated = new Date(System.currentTimeMillis());
        String serviceName = "TestService";

        Service service = ObjectBuilder.create(serviceName, description);
        serviceRepository.save(service);
        Locality locality = ObjectBuilder.create(localityName, population, metro);
        localityRepository.save(locality);
        Landmark landmark = ObjectBuilder.create(landmarkName, description, dateCreated, TypeOfAttraction.CASTLE, locality);
        landmarkRepository.save(landmark);
        LandmarkService landmarkService = ObjectBuilder.create(landmark, service);
        landmarkServiceRepository.save(landmarkService);
    }
}
