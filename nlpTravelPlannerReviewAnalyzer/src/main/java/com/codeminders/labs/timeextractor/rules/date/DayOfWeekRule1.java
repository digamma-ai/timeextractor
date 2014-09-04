package com.codeminders.labs.timeextractor.rules.date;

import static com.codeminders.labs.timeextractor.constants.Constants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

// Sunday, Sundays

public class DayOfWeekRule1 extends BaseRule {

	public static String rule = "(" + DAY_OF_WEEK + "|" + DAY_OF_WEEK_EASY
			+ ")";
	private String extractedText;
	protected Locale locale = Locale.US;
	protected double confidence = 0.83;

	public DayOfWeekRule1(String extractedText) {
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
		DayOfWeek dayOfWeek = null;

		while (matcher.find()) {
			dayOfWeek = TemporalBasicCaseParser
					.getDayOfWeek((matcher.group(1)));
		}

		Date date = new Date();
		date.setDayOfWeek(dayOfWeek);
		Temporal temporal = TemporalObjectGenerator.generateTemporalObject(
				type, date);

		List<Temporal> temporalList = new ArrayList<Temporal>();
		temporalList.add(temporal);

		return temporalList;
	}
}
