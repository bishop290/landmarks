package com.aston.landmarks.integration;

import com.aston.landmarks.model.*;
import com.aston.landmarks.repository.LocalityRepository;
import jakarta.persistence.Table;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.HashMap;

public class DbHelper {
    public static <T> T get(Class<T> entity, DataSource dataSource) {
        NamedParameterJdbcTemplate namedJdbc = new NamedParameterJdbcTemplate(dataSource);
        final String tableName = entity.getAnnotation(Table.class).name();
        final String query = String.format("select * from %s order by id desc limit 1", tableName);
        return namedJdbc.queryForObject(query, new HashMap<>(),
                new BeanPropertyRowMapper<>(entity, false));
    }

    public static Landmark create(
            String name, String description, Date date, TypeOfAttraction type, Locality locality) {
        return Landmark.builder()
                .name(name)
                .description(description)
                .dateCreated(date)
                .attraction(type)
                .locality(locality)
                .build();
    }

    public static Locality create(String name, Integer population, Boolean metro) {
        return Locality.builder()
                .name(name)
                .populationSize(population)
                .metro(metro)
                .build();
    }

    public static Service create(String name, String description) {
        return Service.builder()
                .name(name)
                .description(description)
                .build();
    }

    public static LandmarkService create(Landmark landmark, Service service) {
        return LandmarkService.builder()
                .landmark(landmark)
                .service(service)
                .build();
    }

    public static void saveLocalities(LocalityRepository localityRepository) {
        int counter = 0;
        int max = 3;
        String name = "Тест-";
        int population = 0;

        while (max > counter) {
            Locality locality = create(
                    name + String.valueOf(counter),
                    population + counter,
                    true);
            localityRepository.save(locality);
            counter++;
        }
    }
}
