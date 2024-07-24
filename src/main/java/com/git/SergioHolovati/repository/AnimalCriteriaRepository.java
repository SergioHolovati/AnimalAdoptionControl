package com.git.SergioHolovati.repository;

import com.git.SergioHolovati.dto.FilterDTO;
import com.git.SergioHolovati.model.Animal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalCriteriaRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Animal> findAnimalsByFilters(FilterDTO filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Animal> query = cb.createQuery(Animal.class);
        Root<Animal> animal = query.from(Animal.class);

        List<Predicate> predicates = new ArrayList<>();

        if (ObjectUtils.isNotEmpty(filter.getCategoryEnum())) {
            predicates.add(cb.equal(animal.get("category"), filter.getCategoryEnum()));
        }

        if (ObjectUtils.isNotEmpty(filter.getStatus())) {
            predicates.add(cb.equal(animal.get("status"), filter.getStatus()));
        }

        if (ObjectUtils.isNotEmpty(filter.getStartAdoptionDate()) && ObjectUtils.isNotEmpty(filter.getFinalAdoptionDate())) {
            predicates.add(cb.between(animal.get("dateOfAdoption"), filter.getStartAdoptionDate(), filter.getFinalAdoptionDate()));
        }
        query.where(predicates.toArray(new Predicate[0]));
        query.orderBy(cb.asc(animal.get("name")));

        return entityManager.createQuery(query)
                .setFirstResult(filter.getPage() * filter.getSize())
                .setMaxResults(filter.getSize()).getResultList();
    }
}
