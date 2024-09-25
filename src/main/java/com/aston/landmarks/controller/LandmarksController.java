package com.aston.landmarks.controller;

import com.aston.landmarks.dtos.DefaultResponse;
import com.aston.landmarks.dtos.landmarks.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/landmarks")
public interface LandmarksController {

    @PostMapping()
    ResponseEntity<DefaultResponse> add(@RequestBody LandmarkCreateDto landmark);

    @PutMapping("/{id}")
    ResponseEntity<DefaultResponse> update(@PathVariable("id") Long id, @RequestBody LandmarkUpdateDto landmark);

    @DeleteMapping("/{id}")
    ResponseEntity<DefaultResponse> delete(@PathVariable("id") Long id);

    @GetMapping("/filter/locality")
    ResponseEntity<List<LandmarkResponseDto>> filterByLocality(FilterByLocalityDto filter);

    @GetMapping("/filter/type")
    ResponseEntity<List<LandmarkResponseDto>> filterByTypeSortByName(FilterByTypeSortDto filter);
}
