package ai.digamma.rules.date.dayofweek;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.DayOfWeek;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;

public class DayOfWeekRule1 extends ExtractionRule {

    private String rule = "\\b(" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")[s]?\\b" + "[.]?";
    protected double confidence = 0.362;
    protected int priority = 1;
    protected String example = "Tuesday, Wednesday, Friday, etc.";
    protected UUID id = UUID.fromString("8c01e067-822f-4d96-ae21-39ec70021d52");

    public DayOfWeekRule1() {
    }

    @Override
    public Type getType() {
        return Type.DAY_OF_WEEK;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        DayOfWeek dayOfWeek = null;
        dayOfWeek = TemporalBasicCaseParser.getDayOfWeek(m.group(1));
        Date date = new Date();
        date.setDayOfWeek(dayOfWeek);
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
