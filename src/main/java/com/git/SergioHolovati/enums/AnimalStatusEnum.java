package com.git.SergioHolovati.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum AnimalStatusEnum {
    AVAIABLE("Avaiable"),
    ADOPTED("Adopted");

    private String description;

    public static List<Map<String, String>> list(){
        return  Arrays.stream(AnimalStatusEnum.class.getEnumConstants())
                .map(e -> Map.of("id", e.name(), "description", e.getDescription()))
                .collect(Collectors.toList());
    }
}
