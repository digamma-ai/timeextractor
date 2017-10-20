package ai.digamma.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.Date;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.temporal.entities.MonthOfYear;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;

// Jan-2013

public class MonthAndDayRule6 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.6;
    private int priority = 5;
    private String rule = "(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + ")[-](([12][0-9])?(\\d\\d))";
    protected String example = "Jan-2013, February-2015";
    protected UUID id = UUID.fromString("b4c91529-7e21-4926-b3f7-1cd19bb90134");

    public MonthAndDayRule6() {
    }

    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        MonthOfYear monthOfYear = TemporalBasicCaseParser.getMonthOfYear(m.group(1));
        int month = 0;
        if (monthOfYear != null) {
            month = monthOfYear.getValue();
        }

        int year = 0;
        String yearString = m.group(4);
        if (yearString != null && yearString.length() == 2) {
            year = Integer.parseInt("20" + yearString);
        } else if (yearString != null) {
            year = Integer.parseInt(yearString);
        }

        Date date = new Date(year, month, 0);
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

    @Override
    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public int compareTo(ExtractionRule o) {
        return super.compare(this, o);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
