package com.git.SergioHolovati.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDate;

@Slf4j
public class DateUtils {

    public static Integer dateToAge(LocalDate birth) {
        return LocalDate.now().compareTo(birth);
    }

    public static Boolean isValidPeriodDate(LocalDate startDate, LocalDate endDate){
        if ((ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate))
                && (!startDate.isAfter(endDate)))
            return true;

        return false;
    }
}
