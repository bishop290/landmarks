package com.aston.landmarks.repository;

import com.aston.landmarks.model.Landmark;
import com.aston.landmarks.model.TypeOfAttraction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Long> {

    @EntityGraph(attributePaths = {"locality", "services.service"})
    List<Landmark> findByLocalityName(String name);

    @EntityGraph(attributePaths = {"locality", "services.service"})
    List<Landmark> findByAttraction(TypeOfAttraction attraction, Sort sort);
}
