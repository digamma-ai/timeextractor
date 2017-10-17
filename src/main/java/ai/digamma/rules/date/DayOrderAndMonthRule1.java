package ai.digamma.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.Rule;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.temporal.entities.MonthOfYear;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;

// the second of December
public class DayOrderAndMonthRule1 extends Rule {

    protected double confidence = 0.99;
    private int priority = 2;
    private String rule = "\\b((the)?[\\s]*)" + TemporalConstants.BASIC_ORDER + "([\\s]*(of)?[\\s]*(the)?[\\s]*)?(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY
            + ")[,;\\s]?(([12][0-9]\\d\\d))?\\b";
    protected String example = "the second of December";
    protected UUID id = UUID.fromString("fae4f73e-6257-40aa-a66c-93773d175675");

    public DayOrderAndMonthRule1() {

    }

    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        int dayOfMonth = 0;
        MonthOfYear monthOfYear = null;
        int year = 0;

        monthOfYear = TemporalBasicCaseParser.getMonthOfYear((m.group(7)));
        dayOfMonth = TemporalBasicCaseParser.getDayOfWeekFromOrder((m.group(3)));
        if (m.group(10) != null) {
            year = Integer.parseInt(m.group(10).trim());
        }
        Date date = new Date();
        if (monthOfYear != null) {
            date.setMonth(monthOfYear.getValue());
        }
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
