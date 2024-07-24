package com.git.SergioHolovati.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.git.SergioHolovati.enums.AnimalCategoryEnum;
import com.git.SergioHolovati.enums.AnimalStatusEnum;
import com.git.SergioHolovati.model.Animal;
import com.git.SergioHolovati.util.DateUtils;
import com.git.SergioHolovati.util.StringUtils;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimalDTO {
    private String id;
    private String imgUrl;
    private String name;
    private String description;
    private String category;
    private LocalDate dateOfBirth;
    private Integer age;
    private String status;
    private LocalDate dateOfAdoption;

    public static AnimalDTO mapper(Animal animal){
        return AnimalDTO.builder()
                .id(animal.getId())
                .imgUrl(animal.getImgUrl())
                .name(StringUtils.format(animal.getName()))
                .description(animal.getDescription())
                .category(animal.getCategory().getDescription())
                .dateOfBirth(animal.getDateOfBirth())
                .age(DateUtils.dateToAge(animal.getDateOfBirth()))
                .status(animal.getStatus().getDescription())
                .dateOfAdoption(animal.getDateOfAdoption())
                .build();
    }


}
