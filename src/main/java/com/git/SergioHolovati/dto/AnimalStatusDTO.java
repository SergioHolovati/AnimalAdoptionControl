package com.git.SergioHolovati.dto;

import com.git.SergioHolovati.enums.AnimalStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalStatusDTO {

    AnimalStatusEnum status;
}
