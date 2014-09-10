package com.codeminders.labs.timeextractor.utils;

import java.util.Calendar;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;
import com.codeminders.labs.timeextractor.constants.MonthOfYear;
import com.codeminders.labs.timeextractor.constants.WeekOfMonth;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;

public class TemporalBasicCaseParser {

    public static DayOfWeek getDayOfWeek(String text) {

        if (text.equalsIgnoreCase("Monday") || text.equalsIgnoreCase("Mon") || text.equalsIgnoreCase("Mo") || text.equalsIgnoreCase("Mondays")) {
            return DayOfWeek.MONDAY;
        }
        if (text.equalsIgnoreCase("Tuesday") || text.equalsIgnoreCase("Tue") || text.equalsIgnoreCase("Tuesdays")) {
            return DayOfWeek.TUESDAY;
        }
        if (text.equalsIgnoreCase("Wednesday") || text.equalsIgnoreCase("Wed") || text.equalsIgnoreCase("Wednesdays")) {
            return DayOfWeek.WEDNESDAY;
        }
        if (text.equalsIgnoreCase("Thursday") || text.equalsIgnoreCase("Thur") || text.equalsIgnoreCase("Thursdays")) {
            return DayOfWeek.THURSDAY;
        }
        if (text.equalsIgnoreCase("Friday") || text.equalsIgnoreCase("Fri") || text.equalsIgnoreCase("Fridays")) {
            return DayOfWeek.FRIDAY;
        }
        if (text.equalsIgnoreCase("Saturday") || text.equalsIgnoreCase("Sat") || text.equalsIgnoreCase("Saturdays")) {
            return DayOfWeek.SATURDAY;
        }
        if (text.equalsIgnoreCase("Sunday") || text.equalsIgnoreCase("Sun") || text.equalsIgnoreCase("Sundays")) {
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
        if (text.equalsIgnoreCase("September") || text.equalsIgnoreCase("Sep") || text.equalsIgnoreCase("Sept")) {
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

        if (text.equalsIgnoreCase("first") || text.equalsIgnoreCase("1")) {
            return WeekOfMonth.FIRST;
        }
        if (text.equalsIgnoreCase("second") || text.equalsIgnoreCase("2")) {
            return WeekOfMonth.SECOND;
        }
        if (text.equalsIgnoreCase("third") || text.equalsIgnoreCase("3")) {
            return WeekOfMonth.THIRD;
        }
        if (text.equalsIgnoreCase("fourth") || text.equalsIgnoreCase("4")) {
            return WeekOfMonth.FOURTH;
        }
        if (text.equalsIgnoreCase("fifth") || text.equalsIgnoreCase("5")) {
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

    public static Temporal getSeason(String season, int year) {
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        if (season.equalsIgnoreCase("Summer")) {
            Date startDate = new Date(year, 6, 1);
            Date endDate = new Date(year, 8, 31);
            start.setDate(startDate);
            end.setDate(endDate);
            return new Temporal(start, end);

        }
        if (season.equalsIgnoreCase("Winter")) {
            Date startDate = new Date(year, 12, 1);
            // as end date will be in new year
            year = year + 1;
            boolean leap = isLeapYear(year);
            Date endDate = new Date(year, 2, 28);
            if (leap) {
                endDate = new Date(2, 29);
            }
            start.setDate(startDate);
            end.setDate(endDate);
            return new Temporal(start, end);
        }
        if (season.equalsIgnoreCase("Autumn") || season.equalsIgnoreCase("Fall")) {
            Date startDate = new Date(year, 9, 1);
            Date endDate = new Date(year, 11, 30);
            start.setDate(startDate);
            end.setDate(endDate);
            return new Temporal(start, end);
        }
        if (season.equalsIgnoreCase("Spring")) {
            Date startDate = new Date(year, 3, 1);
            Date endDate = new Date(year, 5, 31);
            start.setDate(startDate);
            end.setDate(endDate);
            return new Temporal(start, end);
        }
        return null;
    }

    public static boolean isLeapYear(int year) {
        if (year == 0) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(cal.DAY_OF_YEAR) > 365;
    }
}
