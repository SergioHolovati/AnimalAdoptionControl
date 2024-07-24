package com.git.SergioHolovati.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.text.WordUtils;

@Slf4j
public class StringUtils {

    public static String format(String value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return WordUtils.capitalize(value.toLowerCase().trim());
    }
}
