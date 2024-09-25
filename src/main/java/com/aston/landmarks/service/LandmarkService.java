package com.aston.landmarks.service;

import com.aston.landmarks.dtos.DefaultResponse;
import com.aston.landmarks.dtos.landmarks.*;

import java.util.List;

public interface LandmarkService {

    DefaultResponse add(LandmarkCreateDto landmark);

    DefaultResponse update(Long id, LandmarkUpdateDto landmark);

    DefaultResponse delete(Long id);

    List<LandmarkResponseDto> filterByLocality(FilterByLocalityDto filter);

    List<LandmarkResponseDto> filterByTypeSortByName(FilterByTypeSortDto filter);
}
