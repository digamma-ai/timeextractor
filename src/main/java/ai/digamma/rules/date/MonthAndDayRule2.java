package ai.digamma.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.MonthOfYear;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;

// 14 July 2012
public class MonthAndDayRule2 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int month;
    private int day;
    private int year;
    private int priority = 4;
    private String rule = "\\b(the[\\s]*)?((([1-2][0-9]|[3][0-1]|[0]?[1-9]))(th|st|rd|nd)?)[\\s]*(of|[,])?[\\s]*(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY
            + ")([\\s]*[,]?(of|[,])?[\\s]*([12][0-9]\\d\\d))?\\b";
    protected String example = "14 July 2012";
    protected UUID id = UUID.fromString("78a4b452-bf2f-4ec6-8d35-5aba4739b3ee");

    public MonthAndDayRule2() {
    }

    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        day = Integer.parseInt(m.group(3));
        MonthOfYear monthOfYear = TemporalBasicCaseParser.getMonthOfYear(m.group(7));
        if (monthOfYear != null) {
            month = monthOfYear.getValue();
        }

        if (!(m.group(12) == null)) {
            year = Integer.parseInt(m.group(12));
        }

        Date date = new Date(year, month, day);
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
