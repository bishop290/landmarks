package com.aston.landmarks.controller;

import com.aston.landmarks.dtos.DefaultResponse;
import com.aston.landmarks.dtos.localities.LocalityCreateDto;
import com.aston.landmarks.dtos.localities.LocalityUpdateDto;
import com.aston.landmarks.service.LocalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocalityControllerImpl implements LocalityController {
    private final LocalityService localityService;

    @Override
    public ResponseEntity<DefaultResponse> add(@Validated LocalityCreateDto locality) {
        return new ResponseEntity<>(localityService.add(locality), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DefaultResponse> update(Long id, @Validated LocalityUpdateDto locality) {
        return new ResponseEntity<>(localityService.update(id, locality), HttpStatus.OK);
    }
}
