package com.git.SergioHolovati.model;

import com.git.SergioHolovati.enums.AnimalCategoryEnum;
import com.git.SergioHolovati.enums.AnimalStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ANIMAL")
public class Animal {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "IMG_URL")
    private String imgUrl;
    @Column(name="NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private AnimalCategoryEnum category;
    @Column(name="DT_BIRTH")
    private LocalDate dateOfBirth;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private AnimalStatusEnum status;
    @Column(name="DT_ADOPTION")
    private LocalDate dateOfAdoption;

}
