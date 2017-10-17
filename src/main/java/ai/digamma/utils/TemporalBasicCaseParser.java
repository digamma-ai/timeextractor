package ai.digamma.utils;

import ai.digamma.temporal.entities.WeekOfMonth;
import ai.digamma.temporal.entities.DayOfWeek;
import ai.digamma.temporal.entities.MonthOfYear;
import ai.digamma.temporal.entities.TimeTag;

public class TemporalBasicCaseParser {

    public static DayOfWeek getDayOfWeek(String text) {

        if (text.equalsIgnoreCase("Monday") || text.equalsIgnoreCase("Mon") || text.equalsIgnoreCase("Mo") || text.equalsIgnoreCase("Mondays")) {
            return DayOfWeek.MO;
        }
        if (text.equalsIgnoreCase("Tuesday") || text.equalsIgnoreCase("Tue") || text.equalsIgnoreCase("Tuesdays")) {
            return DayOfWeek.TU;
        }
        if (text.equalsIgnoreCase("Wednesday") || text.equalsIgnoreCase("Wed") || text.equalsIgnoreCase("Wednesdays")) {
            return DayOfWeek.WE;
        }
        if (text.equalsIgnoreCase("Thursday") || text.equalsIgnoreCase("Thur") || text.equalsIgnoreCase("Thursdays") || text.equalsIgnoreCase("Thu")) {
            return DayOfWeek.TH;
        }
        if (text.equalsIgnoreCase("Friday") || text.equalsIgnoreCase("Fri") || text.equalsIgnoreCase("Fridays")) {
            return DayOfWeek.FR;
        }
        if (text.equalsIgnoreCase("Saturday") || text.equalsIgnoreCase("Sat") || text.equalsIgnoreCase("Saturdays")) {
            return DayOfWeek.SA;
        }
        if (text.equalsIgnoreCase("Sunday") || text.equalsIgnoreCase("Sun") || text.equalsIgnoreCase("Sundays")) {
            return DayOfWeek.SU;
        }
        return null;
    }

    public static MonthOfYear getMonthOfYear(String text) {
        if (text == null) {
            return null;
        }
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

    public static int getIntFromBasicTerm(String text) {

        if (text.equalsIgnoreCase("one")) {
            return 1;
        }
        if (text.equalsIgnoreCase("two")) {
            return 2;
        }
        if (text.equalsIgnoreCase("three")) {
            return 3;
        }
        if (text.equalsIgnoreCase("four")) {
            return 4;
        }
        if (text.equalsIgnoreCase("five")) {
            return 5;
        }

        if (text.equalsIgnoreCase("six") || text.equalsIgnoreCase("sixth")) {
            return 6;
        }

        if (text.equalsIgnoreCase("seven")) {
            return 7;
        }

        if (text.equalsIgnoreCase("eight")) {
            return 8;
        }
        if (text.equalsIgnoreCase("nine")) {
            return 9;
        }
        if (text.equalsIgnoreCase("ten")) {
            return 10;
        }
        if (text.equalsIgnoreCase("eleven")) {
            return 11;
        }
        if (text.equalsIgnoreCase("twelve")) {
            return 12;
        }
        if (text.equalsIgnoreCase("thirteen")) {
            return 13;
        }
        if (text.equalsIgnoreCase("fourteen")) {
            return 14;
        }
        if (text.equalsIgnoreCase("fifteen")) {
            return 15;
        }
        if (text.equalsIgnoreCase("sixteen")) {
            return 16;
        }
        if (text.equalsIgnoreCase("seventeen")) {
            return 17;
        }
        if (text.equalsIgnoreCase("eighteen")) {
            return 18;
        }
        if (text.equalsIgnoreCase("nineteen")) {
            return 19;
        }

        if (text.equalsIgnoreCase("twenty")) {
            return 20;
        }

        if (text.equalsIgnoreCase("twenty-one")) {
            return 21;
        }

        if (text.equalsIgnoreCase("twenty-two")) {
            return 22;
        }

        if (text.equalsIgnoreCase("twenty-three")) {
            return 23;
        }

        if (text.equalsIgnoreCase("twenty-four")) {
            return 24;
        }

        if (text.equalsIgnoreCase("twenty-five")) {
            return 25;
        }
        if (text.equalsIgnoreCase("twenty-six")) {
            return 26;
        }
        if (text.equalsIgnoreCase("twenty-seven")) {
            return 27;
        }
        if (text.equalsIgnoreCase("twenty-eight")) {
            return 28;
        }
        if (text.equalsIgnoreCase("twenty-nine")) {
            return 29;
        }
        if (text.equalsIgnoreCase("thirty")) {
            return 30;
        }
        if (text.equalsIgnoreCase("forty")) {
            return 40;
        }
        if (text.equalsIgnoreCase("fifty")) {
            return 50;
        }
        if (text.equalsIgnoreCase("sixty")) {
            return 60;
        }
        if (text.equalsIgnoreCase("seventy")) {
            return 70;
        }
        if (text.equalsIgnoreCase("eighty")) {
            return 80;
        }
        if (text.equalsIgnoreCase("ninety")) {
            return 90;
        }
        if (text.equalsIgnoreCase("hundred")) {
            return 100;
        }

        return 0;
    }

    public int getTimeZone(String zone) {
        if (zone.equalsIgnoreCase("PST") || zone.equalsIgnoreCase("Pacific Time")) {
            return +480;
        }

        else if (zone.equalsIgnoreCase("PDT") || zone.equalsIgnoreCase("PT")) {
            return +420;
        }

        else if (zone.equalsIgnoreCase("EST")) {
            return +300;
        }

        else if (zone.equalsIgnoreCase("EDT") || zone.equalsIgnoreCase("ET") || zone.equalsIgnoreCase("Eastern Daylight Time")) {
            return +240;
        }

        else if (zone.equalsIgnoreCase("CST")) {
            return +360;
        } else if (zone.equalsIgnoreCase("CET") || zone.equalsIgnoreCase("Central European Time")) {
            return -60;
        } else if (zone.equalsIgnoreCase("CEST") || zone.equalsIgnoreCase("EET")) {
            return -120;
        }

        else if (zone.equalsIgnoreCase("BST")) {
            return -60;
        }

        else if (zone.equalsIgnoreCase("EEST") || zone.equalsIgnoreCase("Eastern Europe Summer Time")) {
            return -180;
        }

        else if (zone.equalsIgnoreCase("GMT")) {
            return 0;
        } else if (zone.equalsIgnoreCase("IST")) {
            return -60;
        } else if (zone.equalsIgnoreCase("MSD")) {
            return -240;
        } else if (zone.equalsIgnoreCase("MSK")) {
            return -240;
        } else if (zone.equalsIgnoreCase("WEST")) {
            return -60;
        } else if (zone.equalsIgnoreCase("WET")) {
            return 0;
        } else if (zone.equalsIgnoreCase("CST")) {
            return -480;
        } else if (zone.equalsIgnoreCase("HKT")) {
            return -480;
        } else if (zone.equalsIgnoreCase("IDT")) {
            return -180;
        } else if (zone.equalsIgnoreCase("JST")) {
            return -540;
        } else if (zone.equalsIgnoreCase("CST")) {
            return +360;
        } else if (zone.equalsIgnoreCase("EST")) {
            return +300;
        }
        return 0;

    }

    public static TimeTag getTimeTag(String tag) {
        if (tag.equalsIgnoreCase("after") || tag.equalsIgnoreCase("past")) {
            return TimeTag.AFTER;
        }
        return TimeTag.BEFORE;
    }

}
