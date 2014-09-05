package com.codeminders.labs.timeextractor.utils;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;
import com.codeminders.labs.timeextractor.constants.MonthOfYear;
import com.codeminders.labs.timeextractor.constants.WeekOfMonth;

public class TemporalBasicCaseParser {

    public static DayOfWeek getDayOfWeek(String text) {

        if (text.equalsIgnoreCase("Monday") || text.equalsIgnoreCase("Mon") || text.equalsIgnoreCase("Mo")) {
            return DayOfWeek.MONDAY;
        }
        if (text.equalsIgnoreCase("Tuesday") || text.equalsIgnoreCase("Tue")) {
            return DayOfWeek.TUESDAY;
        }
        if (text.equalsIgnoreCase("Wednesday") || text.equalsIgnoreCase("Wed")) {
            return DayOfWeek.WEDNESDAY;
        }
        if (text.equalsIgnoreCase("Thursday") || text.equalsIgnoreCase("Thur")) {
            return DayOfWeek.THURSDAY;
        }
        if (text.equalsIgnoreCase("Friday") || text.equalsIgnoreCase("Fri")) {
            return DayOfWeek.FRIDAY;
        }
        if (text.equalsIgnoreCase("Saturday") || text.equalsIgnoreCase("Sat")) {
            return DayOfWeek.SATURDAY;
        }
        if (text.equalsIgnoreCase("Sunday") || text.equalsIgnoreCase("Sun")) {
            return DayOfWeek.SUNDAY;
        }
        return null;
    }

    public static MonthOfYear getMonthOfYear(String text) {
        if (text.equalsIgnoreCase("January") || text.equalsIgnoreCase("Jan")) {
            return MonthOfYear.JANUARY;
        }
        if (text.equalsIgnoreCase("February") || text.equalsIgnoreCase("Feb")) {
            return MonthOfYear.FEBRUARY;
        }
        if (text.equalsIgnoreCase("March") || text.equalsIgnoreCase("Mar")) {
            return MonthOfYear.MARCH;
        }
        if (text.equalsIgnoreCase("April") || text.equalsIgnoreCase("Apr")) {
            return MonthOfYear.APRIL;
        }
        if (text.equalsIgnoreCase("May")) {
            return MonthOfYear.MAY;
        }
        if (text.equalsIgnoreCase("June") || text.equalsIgnoreCase("Jun")) {
            return MonthOfYear.JUNE;
        }
        if (text.equalsIgnoreCase("July") || text.equalsIgnoreCase("Jul")) {
            return MonthOfYear.JULY;
        }
        if (text.equalsIgnoreCase("August") || text.equalsIgnoreCase("Aug")) {
            return MonthOfYear.AUGUST;
        }
        if (text.equalsIgnoreCase("September") || text.equalsIgnoreCase("Sep")) {
            return MonthOfYear.SEPTEMBER;
        }
        if (text.equalsIgnoreCase("October") || text.equalsIgnoreCase("Oct")) {
            return MonthOfYear.OCTOBER;
        }
        if (text.equalsIgnoreCase("November") || text.equalsIgnoreCase("Nov")) {
            return MonthOfYear.NOVEMBER;
        }
        if (text.equalsIgnoreCase("December") || text.equalsIgnoreCase("Dec")) {
            return MonthOfYear.DECEMBER;
        }
        return null;
    }

    public static WeekOfMonth getWeekOfMonth(String text) {

        if (text.equalsIgnoreCase("first")) {
            return WeekOfMonth.FIRST;
        }
        if (text.equalsIgnoreCase("second")) {
            return WeekOfMonth.SECOND;
        }
        if (text.equalsIgnoreCase("third")) {
            return WeekOfMonth.THIRD;
        }
        if (text.equalsIgnoreCase("fourth")) {
            return WeekOfMonth.FOURTH;
        }
        if (text.equalsIgnoreCase("fifth")) {
            return WeekOfMonth.FIFTH;
        }

        if (text.equalsIgnoreCase("last")) {
            return WeekOfMonth.LAST;
        }

        return null;
    }

    public static int getDayOfWeekFromOrder(String text) {

        if (text.equalsIgnoreCase("first")) {
            return 1;
        }
        if (text.equalsIgnoreCase("second")) {
            return 2;
        }
        if (text.equalsIgnoreCase("third")) {
            return 3;
        }
        if (text.equalsIgnoreCase("fourth")) {
            return 4;
        }
        if (text.equalsIgnoreCase("fifth")) {
            return 5;
        }

        if (text.equalsIgnoreCase("sixth")) {
            return 6;
        }

        if (text.equalsIgnoreCase("seventh")) {
            return 7;
        }

        if (text.equalsIgnoreCase("eighth")) {
            return 8;
        }
        if (text.equalsIgnoreCase("ninth")) {
            return 9;
        }
        if (text.equalsIgnoreCase("tenth")) {
            return 10;
        }
        if (text.equalsIgnoreCase("eleventh")) {
            return 11;
        }
        if (text.equalsIgnoreCase("twelfth")) {
            return 12;
        }
        if (text.equalsIgnoreCase("thirteenth")) {
            return 13;
        }
        if (text.equalsIgnoreCase("fourteenth")) {
            return 14;
        }
        if (text.equalsIgnoreCase("fifteenth")) {
            return 15;
        }
        if (text.equalsIgnoreCase("sixteenth")) {
            return 16;
        }
        if (text.equalsIgnoreCase("seventeenth")) {
            return 17;
        }
        if (text.equalsIgnoreCase("eighteenth")) {
            return 18;
        }
        if (text.equalsIgnoreCase("nineteenth")) {
            return 19;
        }

        if (text.equalsIgnoreCase("twentieth")) {
            return 20;
        }

        if (text.equalsIgnoreCase("twenty-first")) {
            return 21;
        }

        if (text.equalsIgnoreCase("twenty-second")) {
            return 22;
        }

        if (text.equalsIgnoreCase("twenty-third")) {
            return 23;
        }

        if (text.equalsIgnoreCase("twenty-fourth")) {
            return 24;
        }

        if (text.equalsIgnoreCase("twenty-fifth")) {
            return 25;
        }
        if (text.equalsIgnoreCase("twenty-sixth")) {
            return 26;
        }
        if (text.equalsIgnoreCase("twenty-seventh")) {
            return 27;
        }
        if (text.equalsIgnoreCase("twenty-eights")) {
            return 28;
        }
        if (text.equalsIgnoreCase("twenty-ninth")) {
            return 29;
        }
        if (text.equalsIgnoreCase("thirtieth")) {
            return 30;
        }
        if (text.equalsIgnoreCase("thirty-first")) {
            return 31;
        }

        return 0;
    }

}
