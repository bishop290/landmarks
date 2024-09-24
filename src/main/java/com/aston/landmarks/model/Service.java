package com.aston.landmarks.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service")
@ToString(exclude = "landmarks")
@EqualsAndHashCode(exclude = "landmarks")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "service")
    private List<LandmarkService> landmarks = new ArrayList<>();
}