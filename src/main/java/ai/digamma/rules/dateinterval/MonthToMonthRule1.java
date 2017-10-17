package ai.digamma.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.Rule;
import ai.digamma.temporal.entities.*;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.TimeDate;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.Utils;

public class MonthToMonthRule1 extends Rule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.9;
    private String rule = "(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + ")([.]?)[\\s]*(through|thru|to|-)[\\s]*(" + TemporalConstants.MONTH_OF_YEAR + "|"
            + TemporalConstants.MONTH_OF_YEAR_EASY + ")";
    protected int priority = 7;
    protected String example = "January through February";
    protected UUID id = UUID.fromString("54a376f6-c3c1-4ca9-a128-b481c99585b5");

    public MonthToMonthRule1() {
    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        int month1 = 0;
        int month2 = 0;
        int year = 0;
        MonthOfYear monthEnum1 = TemporalBasicCaseParser.getMonthOfYear(m.group(1));
        MonthOfYear monthEnum2 = TemporalBasicCaseParser.getMonthOfYear(m.group(6));

        if (monthEnum1 != null) {
            month1 = monthEnum1.getValue();
        }
        if (monthEnum2 != null) {
            month2 = monthEnum2.getValue();
        }

        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Date startDate = new Date();
        Date endDate = new Date();

        startDate.setMonth(month1);
        startDate.setYear(year);
        endDate.setMonth(month2);
        endDate.setYear(year);

        start.setDate(startDate);
        end.setDate(endDate);

        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE_INTERVAL, start, end);
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

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public UUID getId() {
        return id;
    }
}
