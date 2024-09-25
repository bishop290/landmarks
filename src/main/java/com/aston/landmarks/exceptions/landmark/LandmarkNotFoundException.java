package com.aston.landmarks.exceptions.landmark;

public class LandmarkNotFoundException extends RuntimeException {
    public LandmarkNotFoundException(Long id) {
        super("Landmark with id:" + id + " not found in database");
    }
}
