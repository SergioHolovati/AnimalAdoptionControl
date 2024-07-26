package com.git.SergioHolovati.service.impl;

import com.git.SergioHolovati.dto.AnimalDTO;
import com.git.SergioHolovati.dto.AnimalRequestDTO;
import com.git.SergioHolovati.dto.AnimalStatusDTO;
import com.git.SergioHolovati.dto.FilterDTO;
import com.git.SergioHolovati.enums.AnimalCategoryEnum;
import com.git.SergioHolovati.enums.AnimalStatusEnum;
import com.git.SergioHolovati.model.Animal;
import com.git.SergioHolovati.repository.AnimalCriteriaRepository;
import com.git.SergioHolovati.repository.AnimalRepository;
import com.git.SergioHolovati.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository repository;
    @Autowired
    private AnimalCriteriaRepository criteriaRepository;

    @Override
    public AnimalDTO findById(String id) throws ChangeSetPersister.NotFoundException {
        return AnimalDTO.mapper(repository.findById(id)
                                .orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    public Page<AnimalDTO> findAllAvaiable(FilterDTO filter) {
        List<AnimalDTO> dtos = criteriaRepository.findAnimalsByFilters(filter)
                .stream()
                .map(AnimalDTO::mapper).collect(Collectors.toList());
        return new PageImpl<>(dtos);
    }

    @Override
    public Page<AnimalDTO> findAllAdopted(FilterDTO filter) {
        List<AnimalDTO> dtos = criteriaRepository.findAnimalsByFilters(filter)
                .stream()
                .map(AnimalDTO::mapper).collect(Collectors.toList());
        return new PageImpl<>(dtos);
    }

    @Override
    public Page<AnimalDTO> findAll(FilterDTO filter) {
        List<AnimalDTO> dtos = criteriaRepository.findAnimalsByFilters(filter)
                                                 .stream()
                                                 .map(AnimalDTO::mapper).collect(Collectors.toList());
        return new PageImpl<>(dtos);
    }

    @Override
    public AnimalDTO save(AnimalRequestDTO requestDTO) {
        Animal animal = AnimalRequestDTO.mapper(requestDTO);
        return AnimalDTO.mapper(repository.save(animal));
    }

    @Override
    public AnimalDTO updateStatus(String id, AnimalStatusDTO status) throws ChangeSetPersister.NotFoundException {
        Animal animal = repository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        animal.setStatus(status.getStatus());
        animal.setDateOfAdoption(LocalDate.now());
        return AnimalDTO.mapper(repository.save(animal));
    }

    @Override
    public AnimalDTO updateAnimal(String id, AnimalRequestDTO requestDTO) throws ChangeSetPersister.NotFoundException {
        String animalId = repository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new).getId();
        Animal animal = AnimalRequestDTO.mapper(requestDTO);
        if(requestDTO.getStatus() != animal.getStatus() && requestDTO.getStatus() == AnimalStatusEnum.ADOPTED){
            animal.setDateOfAdoption(LocalDate.now());
        }
        animal.setId(animalId);
        return AnimalDTO.mapper(repository.save(animal));
    }

    @Override
    public List<Map<String, String>> status() {
        return AnimalStatusEnum.list();
    }

    @Override
    public List<Map<String, String>> category() {
        return AnimalCategoryEnum.list();
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
