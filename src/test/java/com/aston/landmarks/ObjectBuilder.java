package com.aston.landmarks;

import com.aston.landmarks.model.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ObjectBuilder {

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

    public static List<Landmark> create(int size) {
        String template = "test-";
        List<Landmark> result = new ArrayList<>();
        int i = 0;

        while (i < size) {
            String text = template + i;
            Service service = create(text, text);
            Locality locality = create(text, 0, false);
            Landmark landmark = create(text, text, null, TypeOfAttraction.CASTLE, locality);
            create(landmark, service);
            result.add(landmark);
            i++;
        }
        return result;
    }
}
