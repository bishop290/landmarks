package com.aston.landmarks.repository;

import com.aston.landmarks.model.LandmarkService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandmarkServiceRepository extends JpaRepository<LandmarkService, Long> {
}
