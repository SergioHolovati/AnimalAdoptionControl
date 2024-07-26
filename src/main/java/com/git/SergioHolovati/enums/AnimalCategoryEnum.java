package com.git.SergioHolovati.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum AnimalCategoryEnum {
    MAMMALS("Mammals"),
    BIRDS("Birds"),
    REPTILES("Reptiles"),
    AMPHIBIANS("Amphibians"),
    FISH("Fish"),
    INSECTS("Insects"),
    ARACHNIDS("Arachnids"),
    CRUSTACEANS("Crustaceans"),
    MOLLUSKS("Mollusks"),
    ECHINODERMS("Echinoderms");

    private final String description;

    public static List<Map<String, String>> list(){
        return  Arrays.stream(AnimalCategoryEnum.class.getEnumConstants())
                .map(e -> Map.of("id", e.name(), "description", e.getDescription()))
                .collect(Collectors.toList());
    }

}