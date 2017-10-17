package ai.digamma.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.Rule;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.Utils;

// October 2012  or Oct. 2012
public class MonthAndYearRule1 extends Rule {

    private int priority = 2;
    private String rule = "\\b(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + ")" + "[.;,]?\\s*([2][0-9]\\d\\d)\\b";
    protected String example = "October 2012, Oct. 2014, Feb. 2014";

    protected Locale locale = Locale.US;
    protected double confidence = 0.9;
    protected UUID id = UUID.fromString("a8648aaa-6147-42cf-872b-5a2f6e19f35a");

    public MonthAndYearRule1() {
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        int month = TemporalBasicCaseParser.getMonthOfYear(m.group(1)).getValue();
        int year = Integer.parseInt(m.group(4));

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
    public int compareTo(Rule o) {
        return super.compare(this, o);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}