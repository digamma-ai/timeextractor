package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Date;
import com.codeminders.labs.timeextractor.temporal.entities.DayOfWeek;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.Utils;

// Sunday 16 2014
public class DayOfWeekRule3 extends Rule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private String rule = "((" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")[s]?[.]?[,]?[\\s]*([1-2][0-9]|[3][0-1]|[1-9])[,]?[\\s]*([12][0-9]\\d\\d))";
    private int priority = 3;

    public DayOfWeekRule3() {
    }

    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        DayOfWeek dayOfWeek = null;
        int dayOfMonth = 0;
        int year = 0;

        dayOfWeek = TemporalBasicCaseParser.getDayOfWeek(m.group(2));
        dayOfMonth = Integer.parseInt(m.group(5));
        year = Integer.parseInt(m.group(6));

        Date date = new Date();
        date.setDayOfWeek(dayOfWeek);
        date.setDay(dayOfMonth);
        date.setYear(year);

        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);

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

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    @Override
    public int compareTo(Rule o) {
        return super.compare(this, o);
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

}
