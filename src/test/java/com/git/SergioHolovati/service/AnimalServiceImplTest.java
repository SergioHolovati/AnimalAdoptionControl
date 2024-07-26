package com.git.SergioHolovati.service;

import com.git.SergioHolovati.dto.AnimalDTO;
import com.git.SergioHolovati.dto.AnimalRequestDTO;
import com.git.SergioHolovati.dto.AnimalStatusDTO;
import com.git.SergioHolovati.dto.FilterDTO;
import com.git.SergioHolovati.enums.AnimalCategoryEnum;
import com.git.SergioHolovati.enums.AnimalStatusEnum;
import com.git.SergioHolovati.model.Animal;
import com.git.SergioHolovati.repository.AnimalCriteriaRepository;
import com.git.SergioHolovati.repository.AnimalRepository;
import com.git.SergioHolovati.service.impl.AnimalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceImplTest {

    @Mock
    private AnimalRepository repository;

    @Mock
    private AnimalCriteriaRepository criteriaRepository;

    @InjectMocks
    private AnimalServiceImpl service;

    private Animal animal;
    private AnimalDTO animalDTO;
    private AnimalRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        animal = Animal.builder()
                .id("1")
                .imgUrl("teste.img")
                .name("chewie")
                .description("Any dog")
                .category(AnimalCategoryEnum.MAMMALS)
                .dateOfBirth(LocalDate.now().minusYears(5))
                .status(AnimalStatusEnum.AVAIABLE)
                .build();

        animalDTO = AnimalDTO.mapper(animal);
        requestDTO = AnimalRequestDTO.builder().build();
    }

    @Test
    void findById_Success() throws ChangeSetPersister.NotFoundException {
        when(repository.findById("1")).thenReturn(Optional.of(animal));

        AnimalDTO foundAnimal = service.findById("1");

        assertNotNull(foundAnimal);
        assertEquals(animalDTO, foundAnimal);
        verify(repository, times(1)).findById("1");
    }

    @Test
    void findById_NotFound() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ChangeSetPersister.NotFoundException.class, () -> service.findById("1"));
    }

    @Test
    void findAllAvaiable() {
        FilterDTO filter = new FilterDTO();
        when(criteriaRepository.findAnimalsByFilters(filter)).thenReturn(Collections.singletonList(animal));

        Page<AnimalDTO> animals = service.findAllAvaiable(filter);

        assertNotNull(animals);
        assertEquals(1, animals.getTotalElements());
        assertEquals(animalDTO, animals.getContent().get(0));
    }

    @Test
    void save() {
        when(repository.save(any(Animal.class))).thenReturn(animal);

        AnimalDTO savedAnimal = service.save(requestDTO);

        assertNotNull(savedAnimal);
        assertEquals(animalDTO, savedAnimal);
        verify(repository, times(1)).save(any(Animal.class));
    }

    @Test
    void updateStatus_Success() throws ChangeSetPersister.NotFoundException {
        when(repository.findById("1")).thenReturn(Optional.of(animal));
        when(repository.save(animal)).thenReturn(animal);
        AnimalStatusDTO animalStatusDTO = new AnimalStatusDTO(AnimalStatusEnum.ADOPTED);

        AnimalDTO updatedAnimal = service.updateStatus("1", animalStatusDTO);

        assertNotNull(updatedAnimal);
        assertEquals(AnimalStatusEnum.ADOPTED.getDescription(), updatedAnimal.getStatus());
        assertEquals(LocalDate.now(), updatedAnimal.getDateOfAdoption());
    }

    @Test
    void updateStatus_NotFound() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        AnimalStatusDTO animalStatusDTO = new AnimalStatusDTO(AnimalStatusEnum.ADOPTED);
        assertThrows(ChangeSetPersister.NotFoundException.class, () -> service.updateStatus("1", animalStatusDTO));
    }

}
