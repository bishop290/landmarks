package com.aston.landmarks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "landmark_service")
public class LandmarkService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Landmark landmark;

    @ManyToOne(optional = false)
    private Service service;

    public LandmarkService(Long id, Landmark landmark, Service service) {
        this.id = id;
        this.landmark = landmark;
        this.service = service;
        this.landmark.getServices().add(this);
        this.service.getLandmarks().add(this);
    }
}
