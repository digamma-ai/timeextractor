package com.codeminders.labs.timeextractor.rules.date;

import static com.codeminders.labs.timeextractor.constants.Constants.MONTH_OF_YEAR;
import static com.codeminders.labs.timeextractor.constants.Constants.MONTH_OF_YEAR_EASY;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class MonthAndDayRule1 extends BaseRule {

	public static String rule = "(" + MONTH_OF_YEAR + "|" + MONTH_OF_YEAR_EASY
			+ ")" + "\\s*([1-2][0-9]|[3][0-1]|[1-9])";
	private String extractedText;
	protected Locale locale = Locale.US;
	protected double confidence = 0.83;

	public MonthAndDayRule1(String extractedText) {
		this.extractedText = extractedText;
	}

	@Override
	public Type getType() {
		return Type.DATE;
	}

	@Override
	public List<Temporal> getTemporal() {
		Pattern pattern = Pattern.compile(rule);
		Matcher matcher = pattern.matcher(extractedText);
		int month = 0;
		int day = 0;

		while (matcher.find()) {
			month = TemporalBasicCaseParser.getMonthOfYear(matcher.group(1))
					.getValue();
			day = Integer.parseInt(matcher.group(2));
		}

		Date date = new Date();
		date.setMonth(month);
		date.setDay(day);

		Temporal temporal = TemporalObjectGenerator.generateTemporalObject(
				type, date);

		List<Temporal> temporalList = new ArrayList<Temporal>();
		temporalList.add(temporal);

		return temporalList;
	}
}