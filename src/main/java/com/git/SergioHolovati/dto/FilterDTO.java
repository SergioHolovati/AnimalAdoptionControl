package com.git.SergioHolovati.dto;


import com.git.SergioHolovati.enums.AnimalCategoryEnum;
import com.git.SergioHolovati.enums.AnimalStatusEnum;
import com.git.SergioHolovati.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.coyote.BadRequestException;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDTO {
    private AnimalStatusEnum status;
    private AnimalCategoryEnum categoryEnum;
    private LocalDate startAdoptionDate;
    private LocalDate finalAdoptionDate;
    private int page;
    private int size;

    public static FilterDTO createFilter(AnimalStatusEnum statusEnum, AnimalCategoryEnum categoryEnum, LocalDate startAdoptionDate,LocalDate finalAdoptionDate, int page, int size) throws BadRequestException {
        FilterDTO filter = new FilterDTO();

        if (ObjectUtils.isNotEmpty(statusEnum)) {
            filter.setStatus(statusEnum);
        }

        if (ObjectUtils.isNotEmpty(categoryEnum)) {
            filter.setCategoryEnum(categoryEnum);
        }

        if (ObjectUtils.isNotEmpty(startAdoptionDate) && ObjectUtils.isNotEmpty(finalAdoptionDate)) {
            if (!DateUtils.isValidPeriodDate(startAdoptionDate, finalAdoptionDate))
                throw new BadRequestException("Data ínicio e fim inválidas.");

            filter.setStartAdoptionDate(startAdoptionDate);
            filter.setFinalAdoptionDate(finalAdoptionDate);

        }
        filter.setSize(size);
        filter.setPage(page);

        return filter;
    }

}
