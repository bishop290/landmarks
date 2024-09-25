package com.aston.landmarks.components;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface SortCreator {
    Sort create(List<String> data);
}
