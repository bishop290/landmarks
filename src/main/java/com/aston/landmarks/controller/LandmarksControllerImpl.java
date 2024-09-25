package com.aston.landmarks.controller;

import com.aston.landmarks.dtos.DefaultResponse;
import com.aston.landmarks.dtos.landmarks.*;
import com.aston.landmarks.service.LandmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LandmarksControllerImpl implements LandmarksController {
    private final LandmarkService landmarkService;

    @Override
    public ResponseEntity<DefaultResponse> add(@Validated LandmarkCreateDto landmark) {
        return new ResponseEntity<>(landmarkService.add(landmark), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DefaultResponse> update(Long id, @Validated LandmarkUpdateDto landmark) {
        return new ResponseEntity<>(landmarkService.update(id, landmark), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DefaultResponse> delete(Long id) {
        return new ResponseEntity<>(landmarkService.delete(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LandmarkResponseDto>> filterByLocality(@Validated FilterByLocalityDto filter) {
        return new ResponseEntity<>(landmarkService.filterByLocality(filter), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LandmarkResponseDto>> filterByTypeSortByName(@Validated FilterByTypeSortDto filter) {
        return new ResponseEntity<>(landmarkService.filterByTypeSortByName(filter), HttpStatus.OK);
    }
}
