package com.aston.landmarks.controller;

import com.aston.landmarks.dtos.DefaultResponse;
import com.aston.landmarks.dtos.landmarks.LandmarkCreateDto;
import com.aston.landmarks.dtos.landmarks.LandmarkResponseDto;
import com.aston.landmarks.dtos.landmarks.LandmarkUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/landmarks")
public interface LandmarksController {

    @PostMapping()
    ResponseEntity<DefaultResponse> add(@RequestBody LandmarkCreateDto landmark);

    @GetMapping()
    ResponseEntity<List<LandmarkResponseDto>> landmarks();

    @PutMapping("/{id}")
    ResponseEntity<DefaultResponse> update(@PathVariable("id") Long id, @RequestBody LandmarkUpdateDto landmark);

    @DeleteMapping("/{id}")
    ResponseEntity<DefaultResponse> delete(@PathVariable Long id);
}
