package com.aston.landmarks.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/landmarks")
public interface LandmarksController {

    @GetMapping()
    ResponseEntity<String> landmarks();
}
