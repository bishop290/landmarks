package com.aston.landmarks.controller;

import com.aston.landmarks.dtos.DefaultResponse;
import com.aston.landmarks.dtos.landmarks.LandmarkCreateDto;
import com.aston.landmarks.dtos.landmarks.LandmarkResponseDto;
import com.aston.landmarks.dtos.landmarks.LandmarkUpdateDto;
import com.aston.landmarks.service.LandmarkService;
import lombok.RequiredArgsConstructor;
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
        return null;
    }

    @Override
    public ResponseEntity<List<LandmarkResponseDto>> landmarks() {
        return null;
    }

    @Override
    public ResponseEntity<DefaultResponse> update(Long id, @Validated LandmarkUpdateDto landmark) {
        return null;
    }

    @Override
    public ResponseEntity<DefaultResponse> delete(Long id) {
        return null;
    }
}
