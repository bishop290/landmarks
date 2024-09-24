package com.aston.landmarks.service;

import com.aston.landmarks.dtos.DefaultResponse;
import com.aston.landmarks.dtos.landmarks.LandmarkCreateDto;
import com.aston.landmarks.dtos.landmarks.LandmarkResponseDto;
import com.aston.landmarks.dtos.landmarks.LandmarkUpdateDto;

import java.util.List;

public interface LandmarkService {

    DefaultResponse add(LandmarkCreateDto landmark);

    DefaultResponse update(Long id, LandmarkUpdateDto landmark);

    List<LandmarkResponseDto> get();

    DefaultResponse delete(Long id);
}
