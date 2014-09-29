package com.codeminders.labs.timeextractor.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Utils {

    public static Matcher getMatch(String rule, String text) {
        Pattern p = Pattern.compile(rule, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        m.find();
        return m;
    }

    public static String currentLocalDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        return localDate.toString(fmt);
    }

    public static int convertTime(int time, String aMPm) {
        if (aMPm.contains("p") || aMPm.contains("P")) {
            if (time >= 12 && time < 24) {
                return time;
            } else if (time == 24) {
                return 0;
            } else if (time >= 1 && time < 12) {
                return time + 12;
            } else if (time > 24) {
                return -1;
            }

        } else {
            if (time == 12 || time == 24) {
                return 0;
            } else if (time > 24) {
                return -1;
            } else {
                return time;
            }
        }
        return time;

    }

}
