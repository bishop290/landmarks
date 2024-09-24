package com.aston.landmarks.service;

import com.aston.landmarks.dtos.DefaultResponse;
import com.aston.landmarks.dtos.landmarks.LandmarkCreateDto;
import com.aston.landmarks.dtos.landmarks.LandmarkResponseDto;
import com.aston.landmarks.dtos.landmarks.LandmarkUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LandmarkServiceImpl implements LandmarkService {
    @Override
    public DefaultResponse add(LandmarkCreateDto landmark) {
        return null;
    }

    @Override
    public DefaultResponse update(Long id, LandmarkUpdateDto landmark) {
        return null;
    }

    @Override
    public List<LandmarkResponseDto> get() {
        return List.of();
    }

    @Override
    public DefaultResponse delete(Long id) {
        return null;
    }
}
