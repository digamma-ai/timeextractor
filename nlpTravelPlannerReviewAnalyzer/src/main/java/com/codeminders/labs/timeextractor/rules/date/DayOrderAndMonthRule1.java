package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeminders.labs.timeextractor.constants.Constants.*;

import com.codeminders.labs.timeextractor.constants.MonthOfYear;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class DayOrderAndMonthRule1 extends BaseRule {

    public static String rule = "\\b(the[\\s]*)?" + BASIC_ORDER_DAY_OF_MONTH + " +[\\s]*(of[\\s]*)?" + MONTH_OF_YEAR + "\\s*[.,;]?\\s*" + YEAR + "?" + "\\b";
    private String extractedText;
    protected Locale locale = Locale.US;
    protected double confidence = 0.83;

    public DayOrderAndMonthRule1(String extractedText) {
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
        int dayOfMonth = 0;
        MonthOfYear monthOfYear = null;
        int year = 0;

        while (matcher.find()) {
            monthOfYear = TemporalBasicCaseParser.getMonthOfYear((matcher.group(4)));
            dayOfMonth = TemporalBasicCaseParser.getDayOfWeekFromOrder((matcher.group(2)));
            if (matcher.group(5) != null) {
                year = Integer.parseInt(matcher.group(5));
            }
        }

        Date date = new Date();
        date.setMonth(monthOfYear.getValue());
        date.setDay(dayOfMonth);
        date.setYear(year);
        Temporal temporal = TemporalObjectGenerator.generateTemporalObject(type, date);

        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);

        return temporalList;
    }
}
