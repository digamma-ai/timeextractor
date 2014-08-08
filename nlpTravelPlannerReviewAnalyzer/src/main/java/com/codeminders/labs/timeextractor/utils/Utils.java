package com.codeminders.labs.timeextractor.utils;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Utils {

    public static String currentLocalDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        return localDate.toString(fmt);
    }

}
