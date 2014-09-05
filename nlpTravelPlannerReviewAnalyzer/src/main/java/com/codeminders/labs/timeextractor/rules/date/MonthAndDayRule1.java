package com.codeminders.labs.timeextractor.rules.date;

import static com.codeminders.labs.timeextractor.constants.Constants.*;

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

// July the 14th 2014, July 14 2014, July 14

public class MonthAndDayRule1 extends BaseRule {

    public static String rule = "(" + MONTH_OF_YEAR + "|" + MONTH_OF_YEAR_EASY + ")" + "\\s*[,]?\\s*" + "(the[\\s]*)?" + DAY_OF_MONTH + "\\s*[,]?\\s*" + "((th|st|nd)[,]?\\s*)?" + YEAR + "?";
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
        int year = 0;

        while (matcher.find()) {
            if (matcher.group(3) != null) {
                month = TemporalBasicCaseParser.getMonthOfYear(matcher.group(3)).getValue();
            } else {
                month = TemporalBasicCaseParser.getMonthOfYear(matcher.group(2)).getValue();

            }
            day = Integer.parseInt(matcher.group(5));
            if (matcher.group(8) != null) {
                year = Integer.parseInt(matcher.group(8));
            }
        }

        Date date = new Date(year, month, day);
        Temporal temporal = TemporalObjectGenerator.generateTemporalObject(type, date);

        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);

        return temporalList;
    }
}