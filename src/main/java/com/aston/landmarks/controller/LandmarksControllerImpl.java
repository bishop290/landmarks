package com.aston.landmarks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LandmarksControllerImpl implements LandmarksController {

    @Override
    public ResponseEntity<String> landmarks() {
        return new ResponseEntity<String>("Hello world", HttpStatus.OK);
    }
}
