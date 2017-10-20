package ai.digamma.rules.season;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.MonthOfYear;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalParser;
import ai.digamma.utils.Utils;

public class MonthToMonthInterval extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.6;
    private String rule = "\\b((between|from)[\\s]*(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + "|" + TemporalConstants.SEASON + ")([\\s]*(and|to)[\\s]*)("
            + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + "|" + TemporalConstants.SEASON + "))";
    protected int priority = 6;
    private TemporalParser parser;
    protected String example = "from winter to summer, from January to spring";
    protected UUID id = UUID.fromString("cde98bf8-262b-4491-af63-e25d26522de5");

    public MonthToMonthInterval() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        String m1 = m.group(3);
        String m2 = m.group(9);
        Temporal temporalStart = new Temporal();
        Temporal temporalEnd = new Temporal();

        if (TemporalConstants.SEASON.contains(m1.toLowerCase())) {
            temporalStart = parser.getSeason(m1, 0);
        } else {
            MonthOfYear monthStartEnum = TemporalBasicCaseParser.getMonthOfYear(m1);
            if (monthStartEnum != null) {
                int monthStart = monthStartEnum.getValue();
                Date date = new Date(0, monthStart, 0);
                temporalStart = TemporalObjectGenerator.generateTemporalDate(type, date);
            }
        }
        if (TemporalConstants.SEASON.contains(m2.toLowerCase())) {
            temporalEnd = parser.getSeason(m2, 0);
        } else {
            MonthOfYear monthEndEnum = TemporalBasicCaseParser.getMonthOfYear(m2);
            if (monthEndEnum != null) {
                int monthEnd = monthEndEnum.getValue();
                Date date = new Date(0, monthEnd, 0);
                temporalEnd = TemporalObjectGenerator.generateTemporalDate(type, date);
            }
        }
        temporalEnd.getStartDate().setDate(temporalStart.getStartDate().getDate());
        List<Temporal> result = new ArrayList<Temporal>();
        result.add(temporalEnd);
        return result;
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
}
