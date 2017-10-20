package ai.digamma.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalParser;

public class Holidays extends ExtractionRule {
    protected double confidence = 0.99;
    private TemporalParser parser;
    private int priority = 1;
    private String rule = TemporalConstants.HOLIDAYS;
    protected String example = "Christmas, New Year, Thanksgiving Day, Memorial Day, etc.";
    protected UUID id = UUID.fromString("fdc63959-88e4-4859-bbed-7ba071d90593");
    protected Type type;

    public Holidays() {
        parser = new TemporalParser();
    }

    public Type getType() {
        return type;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        List<Temporal> result = new ArrayList<Temporal>();
        Temporal holiday = parser.getHoliday(text);
        result.add(holiday);
        type = holiday.getType();
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
