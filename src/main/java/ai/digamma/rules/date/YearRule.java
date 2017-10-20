package ai.digamma.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;

// year rule (2014, 2013, 1989, etc.)

public class YearRule extends ExtractionRule {

    protected double confidence = 0.3;
    protected String rule = "\\b(in[\\s]*|until[\\s]*|till[\\s]*|til[\\s]*)?\\b((([1][8-9])|([2][01]))\\d\\d)\\b";
    protected int priority = 1;
    protected String example = "2013, 2014, 1989, etc.";
    protected UUID id = UUID.fromString("c39a0bda-8298-4d69-bcc2-ce23583f5c85");

    public YearRule() {
    }

    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        int year = Integer.parseInt(m.group(2));
        Date date = new Date(year, 0, 0);
        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

    public Type getType() {
        return Type.YEAR;
    }

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

    public void setId(UUID id) {
        this.id = id;
    }
}
