package ai.digamma.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.Utils;

public class MonthDayYearRule1 extends ExtractionRule {

    private double confidence = 0.8;
    private int priority = 5;
    protected String rule = "\\b((in|on|by|until)[\\s]*)?((1[012]|0?[1-9]))[-.\\/]((3[01]|[12][0-9]|0[1-9]))[-.\\/]((([12][0-9])\\d\\d)|\\d\\d)\\b";
    protected String example = "by 10/30/2012, 01-29-2014";
    protected UUID id = UUID.fromString("2e81e051-8721-4085-889d-bd0fa740febf");

    public MonthDayYearRule1() {
    }

    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        String yearString = m.group(7);
        String dayString = m.group(5);
        String monthString = m.group(3);

        int day = Integer.parseInt(dayString);
        int month = Integer.parseInt(monthString);
        int year = 0;
        if (yearString.length() == 4) {
            year = Integer.parseInt(yearString);
        }
        if (yearString.length() == 2) {
            year = Integer.parseInt("20" + yearString);
        }
        Date date = new Date();
        date.setDay(day);
        date.setMonth(month);
        date.setYear(year);
        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);

        List<Temporal> result = new ArrayList<Temporal>();
        result.add(temporal);
        return result;
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
    public int compareTo(ExtractionRule o) {
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

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
