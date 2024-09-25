package com.aston.landmarks.unit.components;

import com.aston.landmarks.components.SortCreator;
import com.aston.landmarks.components.SortCreatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.data.domain.Sort.*;

@DisplayName("SortCreatorImplTest component test")
class SortCreatorImplTest {
    private SortCreator sortCreator;

    @BeforeEach
    void init() {
        sortCreator = new SortCreatorImpl();
    }

    public static Stream<Arguments> generateArguments() {
        return Stream.of(
                arguments(List.of("name,asc"), by(List.of(Order.asc("name")))),
                arguments(List.of("name", "desc"), by(List.of(Order.desc("name")))),
                arguments(List.of("name,asc", "type,desc"),
                        by(List.of(Order.asc("name"), Order.desc("type")))),
                arguments(List.of("name"), by(List.of(Order.desc("name")))),
                arguments(List.of("name,"), by(List.of(Order.desc("name")))),
                arguments(List.of("name", "type,desc"), by(List.of(Order.desc("name")))),
                arguments(List.of("desc"), by(List.of(Order.desc("desc")))),
                arguments(List.of(""), unsorted()),
                arguments(List.of(), unsorted()),
                arguments(List.of("name,asc", ""), by(List.of(Order.asc("name"))))
        );
    }

    @ParameterizedTest
    @MethodSource("generateArguments")
    @DisplayName("Create Sort object")
    void test(List<String> sortParams, Sort expected) {
        assertEquals(expected, sortCreator.create(sortParams));
    }
}