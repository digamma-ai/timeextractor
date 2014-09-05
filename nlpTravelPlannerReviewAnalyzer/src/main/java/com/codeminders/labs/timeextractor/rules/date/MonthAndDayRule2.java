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

// 14 July 2012
public class MonthAndDayRule2 extends BaseRule {

    public static String rule = DAY_OF_MONTH + "(th)?" + "[\\s]*(of|[,])?[\\s]*" + "(" + MONTH_OF_YEAR + "|" + MONTH_OF_YEAR_EASY + ")" + "[\\s]*(of|[,])?[\\s]*" + YEAR + "?";
    private String extractedText;
    protected Locale locale = Locale.US;
    protected double confidence = 0.83;

    public MonthAndDayRule2(String extractedText) {
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
            day = Integer.parseInt(matcher.group(1));
            month = TemporalBasicCaseParser.getMonthOfYear(matcher.group(4)).getValue();

            if (!(matcher.group(6) == null)) {
                year = Integer.parseInt(matcher.group(6));
            }
        }

        Date date = new Date(year, month, day);
        Temporal temporal = TemporalObjectGenerator.generateTemporalObject(type, date);

        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);

        return temporalList;
    }
}
