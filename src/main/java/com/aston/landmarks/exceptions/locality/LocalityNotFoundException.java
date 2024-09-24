package com.aston.landmarks.exceptions.locality;

public class LocalityNotFoundException extends RuntimeException {
    public LocalityNotFoundException(Long id) {
        super("Locality with id:" + id + " not found in database");
    }
}
