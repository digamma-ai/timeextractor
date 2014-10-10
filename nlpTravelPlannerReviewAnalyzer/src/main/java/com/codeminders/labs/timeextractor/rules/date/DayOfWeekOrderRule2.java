package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import org.joda.time.LocalDateTime;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Date;
import com.codeminders.labs.timeextractor.temporal.entities.DayOfWeek;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.temporal.entities.WeekOfMonth;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalParser;
import com.codeminders.labs.timeextractor.utils.Utils;

//1st Tuesday of the month
public class DayOfWeekOrderRule2 extends Rule {

    protected double confidence = 0.9;
    private String rule = "\\b(the[\\s]*)?(([1-5])(th|st|nd|rd)?[\\s]*)" + "((" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY
            + "))([s]?[\\s]*(of[\\s]*)(the[\\s]*)?(month))?\\b";
    private int priority = 3;
    private TemporalParser parser;

    public DayOfWeekOrderRule2() {
        parser = new TemporalParser();

    }

    public Type getType() {
        return Type.DAY_OF_WEEK;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        DayOfWeek dayOfWeek = null;
        WeekOfMonth weekOfMonth = null;
        dayOfWeek = TemporalBasicCaseParser.getDayOfWeek(m.group(5));
        weekOfMonth = TemporalBasicCaseParser.getWeekOfMonth(m.group(3));
        LocalDateTime currentDate = new LocalDateTime();

        Date date = new Date();
        date.setDayOfWeek(dayOfWeek);
        date.setWeekOfMonth(weekOfMonth);

        Temporal temporal = parser.getRelativeTemporalObjectByWeekOfMonth(dayOfWeek, weekOfMonth, currentDate);

        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);

        return temporalList;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Rule o) {
        return super.compare(this, o);
    }
}
