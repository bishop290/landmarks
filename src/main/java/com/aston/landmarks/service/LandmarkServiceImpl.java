package com.aston.landmarks.service;

import com.aston.landmarks.components.SortCreator;
import com.aston.landmarks.dtos.DefaultResponse;
import com.aston.landmarks.dtos.landmarks.*;
import com.aston.landmarks.exceptions.landmark.LandmarkNotFoundException;
import com.aston.landmarks.exceptions.locality.LocalityNotFoundException;
import com.aston.landmarks.mappers.landmarks.LandmarkCreateDtoMapper;
import com.aston.landmarks.mappers.landmarks.LandmarkResponseDtoMapper;
import com.aston.landmarks.model.Landmark;
import com.aston.landmarks.model.Locality;
import com.aston.landmarks.repository.LandmarkRepository;
import com.aston.landmarks.repository.LocalityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LandmarkServiceImpl implements LandmarkService {
    private static final String ADD_MESSAGE = "Landmark add successful";
    private static final String UPDATE_MESSAGE = "Landmark update successful";
    private static final String DELETE_MESSAGE = "Landmark delete";

    private final LandmarkRepository landmarkRepository;
    private final LocalityRepository localityRepository;
    private final LandmarkCreateDtoMapper landmarkCreateDtoMapper;
    private final LandmarkResponseDtoMapper landmarkResponseDtoMapper;
    private final SortCreator sortCreator;

    @Override
    public DefaultResponse add(LandmarkCreateDto landmarkDto) {
        Optional<Locality> maybeLocality = localityRepository.findById(landmarkDto.locality());
        if (maybeLocality.isEmpty()) {
            throw new LocalityNotFoundException(landmarkDto.locality());
        }
        Landmark landmark = landmarkCreateDtoMapper.dtoToEntity(landmarkDto);
        landmark.setLocality(maybeLocality.get());
        landmarkRepository.save(landmark);
        return new DefaultResponse(true, ADD_MESSAGE);
    }

    @Override
    public DefaultResponse update(Long id, LandmarkUpdateDto landmarkDto) {
        Optional<Landmark> maybeLandmark = landmarkRepository.findById(id);
        if (maybeLandmark.isEmpty()) {
            throw new LandmarkNotFoundException(id);
        }
        Landmark landmark = maybeLandmark.get();
        landmark.setDescription(landmarkDto.description());
        landmarkRepository.save(landmark);
        return new DefaultResponse(true, UPDATE_MESSAGE);
    }

    @Override
    public DefaultResponse delete(Long id) {
        landmarkRepository.deleteById(id);
        return new DefaultResponse(true, DELETE_MESSAGE);
    }

    @Override
    public List<LandmarkResponseDto> filterByLocality(FilterByLocalityDto filter) {
        return landmarkResponseDtoMapper.entitiesToDtoList(
                landmarkRepository.findByLocalityName(filter.name()));
    }

    @Override
    public List<LandmarkResponseDto> filterByTypeSortByName(FilterByTypeSortDto filter) {
        return landmarkResponseDtoMapper.entitiesToDtoList(
                landmarkRepository.findByAttraction(
                        filter.name(),
                        sortCreator.create(filter.sort())));
    }
}