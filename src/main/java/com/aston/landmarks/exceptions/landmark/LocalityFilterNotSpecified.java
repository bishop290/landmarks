package com.aston.landmarks.exceptions.landmark;

public class LocalityFilterNotSpecified extends RuntimeException {
    public LocalityFilterNotSpecified() {
        super("Locality filter not specified");
    }
}
