package com.git.SergioHolovati.repository;

import com.git.SergioHolovati.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,String> {

}
