package com.git.SergioHolovati.service;

import com.git.SergioHolovati.dto.AnimalDTO;
import com.git.SergioHolovati.dto.AnimalRequestDTO;
import com.git.SergioHolovati.dto.AnimalStatusDTO;
import com.git.SergioHolovati.dto.FilterDTO;
import com.git.SergioHolovati.enums.AnimalStatusEnum;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface AnimalService {

    Page<AnimalDTO> findAllAvaiable(FilterDTO filter);
    Page<AnimalDTO> findAllAdopted(FilterDTO filter);
    AnimalDTO findById(String id) throws ChangeSetPersister.NotFoundException;
    Page<AnimalDTO> findAll(FilterDTO filter);
    AnimalDTO save(AnimalRequestDTO requestDTO);
    AnimalDTO updateStatus(String id, AnimalStatusDTO status) throws ChangeSetPersister.NotFoundException;
    AnimalDTO updateAnimal(String id, AnimalRequestDTO requestDTO) throws ChangeSetPersister.NotFoundException;
    List<Map<String, String>> status();
    List<Map<String, String>> category();
    void delete(String id);
}
