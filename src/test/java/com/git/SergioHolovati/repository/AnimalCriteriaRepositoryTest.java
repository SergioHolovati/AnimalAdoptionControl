package com.git.SergioHolovati.repository;

import com.git.SergioHolovati.dto.FilterDTO;
import com.git.SergioHolovati.enums.AnimalCategoryEnum;
import com.git.SergioHolovati.enums.AnimalStatusEnum;
import com.git.SergioHolovati.model.Animal;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AnimalCriteriaRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    private AnimalCriteriaRepository animalCriteriaRepository;

    @BeforeEach
    void setUp() {
        animalCriteriaRepository = new AnimalCriteriaRepository();
        animalCriteriaRepository.entityManager = entityManager;

        Animal animal1 = new Animal();
        animal1.setId("1");
        animal1.setName("Animal 1");
        animal1.setCategory(AnimalCategoryEnum.MAMMALS);
        animal1.setStatus(AnimalStatusEnum.AVAIABLE);
        animal1.setDateOfAdoption(LocalDate.now());

        Animal animal2 = new Animal();
        animal2.setId("2");
        animal2.setName("Animal 2");
        animal2.setCategory(AnimalCategoryEnum.BIRDS);
        animal2.setStatus(AnimalStatusEnum.ADOPTED);
        animal2.setDateOfAdoption(LocalDate.now().minusDays(5));

        entityManager.merge(animal1);
        entityManager.merge(animal2);
        entityManager.flush();
    }

    @Test
    void findAnimalsByFilters_WithCategoryAndStatus() {
        FilterDTO filter = new FilterDTO();
        filter.setCategoryEnum(AnimalCategoryEnum.MAMMALS);
        filter.setStatus(AnimalStatusEnum.AVAIABLE);
        filter.setPage(0);
        filter.setSize(10);

        List<Animal> animals = animalCriteriaRepository.findAnimalsByFilters(filter);

        assertThat(animals).hasSize(1);
        assertThat(animals.get(0).getName()).isEqualTo("Animal 1");
    }

    @Test
    void findAnimalsByFilters_WithDateRange() {
        FilterDTO filter = new FilterDTO();
        filter.setStartAdoptionDate(LocalDate.now().minusDays(10));
        filter.setFinalAdoptionDate(LocalDate.now());
        filter.setPage(0);
        filter.setSize(10);

        List<Animal> animals = animalCriteriaRepository.findAnimalsByFilters(filter);

        assertThat(animals).hasSize(2);
        assertThat(animals).extracting(Animal::getName).containsExactlyInAnyOrder("Animal 1", "Animal 2");
    }

    @Test
    void findAnimalsByFilters_WithPagination() {
        FilterDTO filter = new FilterDTO();
        filter.setPage(0);
        filter.setSize(1);

        List<Animal> animals = animalCriteriaRepository.findAnimalsByFilters(filter);

        assertThat(animals).hasSize(1);
    }
}