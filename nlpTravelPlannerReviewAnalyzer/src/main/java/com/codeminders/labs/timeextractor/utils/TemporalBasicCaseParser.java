package com.codeminders.labs.timeextractor.utils;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;
import com.codeminders.labs.timeextractor.constants.MonthOfYear;

public class TemporalBasicCaseParser {

	public static DayOfWeek getDayOfWeek(String text) {

		if (text.equalsIgnoreCase("Monday") || text.equalsIgnoreCase("Mon")
				|| text.equalsIgnoreCase("Mo")) {
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

}
