package com.aston.landmarks.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "landmark")
@ToString(exclude = "services")
@EqualsAndHashCode(exclude = "services")
public class Landmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "attraction")
    @Enumerated(EnumType.STRING)
    private TypeOfAttraction attraction;

    @ManyToOne(optional = false)
    @JoinColumn(name = "locality_id")
    private Locality locality;

    @Builder.Default
    @OneToMany(mappedBy = "landmark")
    private List<LandmarkService> services = new ArrayList<>();
}
