package com.codeminders.labs.timeextractor.rules.date;

import static com.codeminders.labs.timeextractor.constants.Constants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.constants.WeekOfMonth;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class DayOfWeekOrderRule1 extends BaseRule {

    public static String rule = "\\b(the)?[\\s]*" + BASIC_ORDER_WEEK_OF_MONTH + "[\\s]*(of)?[\\s]*" + "(" + DAY_OF_WEEK + "|" + DAY_OF_WEEK_EASY + ")" + "[s]?[\\s]*(of (the)? month)?";
    private String extractedText;
    protected Locale locale = Locale.US;
    protected double confidence = 0.83;

    public DayOfWeekOrderRule1(String extractedText) {
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
        WeekOfMonth weekOfMonth = null;
        while (matcher.find()) {
            dayOfWeek = TemporalBasicCaseParser.getDayOfWeek((matcher.group(4)));
            weekOfMonth = TemporalBasicCaseParser.getWeekOfMonth((matcher.group(2)));
        }

        Date date = new Date();
        date.setDayOfWeek(dayOfWeek);
        date.setWeekOfMonth(weekOfMonth);

        Temporal temporal = TemporalObjectGenerator.generateTemporalObject(type, date);

        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);

        return temporalList;
    }
}
