package com.aston.landmarks.service;

import com.aston.landmarks.dtos.DefaultResponse;
import com.aston.landmarks.dtos.localities.LocalityCreateDto;
import com.aston.landmarks.dtos.localities.LocalityUpdateDto;

public interface LocalityService {

    DefaultResponse add(LocalityCreateDto locality);

    DefaultResponse update(Long id, LocalityUpdateDto locality);
}
