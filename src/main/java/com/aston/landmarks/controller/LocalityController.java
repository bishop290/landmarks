package com.aston.landmarks.controller;

import com.aston.landmarks.dtos.DefaultResponse;
import com.aston.landmarks.dtos.localities.LocalityCreateDto;
import com.aston.landmarks.dtos.localities.LocalityUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/localities")
public interface LocalityController {

    @PostMapping()
    ResponseEntity<DefaultResponse> add(@RequestBody LocalityCreateDto locality);

    @PutMapping("/{id}")
    ResponseEntity<DefaultResponse> update(@PathVariable("id") Long id, @RequestBody LocalityUpdateDto locality);
}
