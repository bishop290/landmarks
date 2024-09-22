package com.aston.landmarks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import java.sql.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "landmark")
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
    @ColumnTransformer(write="?::type_of_attraction")
    private TypeOfAttraction attraction;

    @ManyToOne(optional = false)
    @JoinColumn(name = "locality_id")
    private Locality locality;
}
