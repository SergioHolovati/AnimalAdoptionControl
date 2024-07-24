package com.git.SergioHolovati.dto;

import com.git.SergioHolovati.enums.AnimalCategoryEnum;
import com.git.SergioHolovati.enums.AnimalStatusEnum;
import com.git.SergioHolovati.model.Animal;
import com.git.SergioHolovati.util.StringUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AnimalRequestDTO {
    private String name;
    private String description;
    private String imgUrl;
    private AnimalCategoryEnum category;
    private LocalDate dateOfBirth;
    private AnimalStatusEnum status;

    public static Animal mapper(AnimalRequestDTO animal){
        return Animal.builder()
                .imgUrl(animal.getImgUrl())
                .name(StringUtils.format(animal.getName()))
                .description(animal.getDescription())
                .category(animal.getCategory())
                .dateOfBirth(animal.getDateOfBirth())
                .status(animal.getStatus())
                .build();
    }
}
