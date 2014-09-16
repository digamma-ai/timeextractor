package com.codeminders.labs.timeextractor.utils;

public class TimeConvertor {

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
