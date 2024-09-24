package com.aston.landmarks.service;

import com.aston.landmarks.dtos.DefaultResponse;
import com.aston.landmarks.dtos.localities.LocalityCreateDto;
import com.aston.landmarks.dtos.localities.LocalityUpdateDto;
import com.aston.landmarks.exceptions.locality.LocalityNotFoundException;
import com.aston.landmarks.mappers.LocalityCreateDtoMapper;
import com.aston.landmarks.model.Locality;
import com.aston.landmarks.repository.LocalityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocalityServiceImpl implements LocalityService {
    private static final String ADD_MESSAGE = "Locality add successful";
    private static final String UPDATE_MESSAGE = "Locality update successful";

    private final LocalityRepository localityRepository;
    private final LocalityCreateDtoMapper localityCreateDtoMapper;

    @Override
    public DefaultResponse add(LocalityCreateDto localityDto) {
        Locality locality = localityCreateDtoMapper.dtoToEntity(localityDto);
        localityRepository.save(locality);
        return new DefaultResponse(true, ADD_MESSAGE);
    }

    @Override
    public DefaultResponse update(Long id, LocalityUpdateDto localityDto) {
        Optional<Locality> maybeLocality = localityRepository.findById(id);
        if (maybeLocality.isEmpty()) {
            throw new LocalityNotFoundException(id);
        }
        Locality dbLocality = maybeLocality.get();
        dbLocality.setPopulationSize(localityDto.populationSize());
        dbLocality.setMetro(localityDto.metro());
        localityRepository.save(dbLocality);
        return new DefaultResponse(true, UPDATE_MESSAGE);
    }
}
