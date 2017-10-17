package ai.digamma.rules.date.dayofweek;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.Rule;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.DayOfWeek;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;

// Sunday 16 
public class DayOfWeekRule2 extends Rule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.55;
    private int priority = 2;
    private String rule = "\\b(" + "(" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")" + "[.]?[s]?[\\s]*([1-2][0-9]|[3][0-1]|[1-9]))\\b";
    protected String example = "Sunday 16, Monday 14";
    protected UUID id = UUID.fromString("c02638f4-1fe0-4fe8-93bc-c3d934ab31df");

    public DayOfWeekRule2() {
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        DayOfWeek dayOfWeek = null;
        int dayOfMonth = 0;

        dayOfWeek = TemporalBasicCaseParser.getDayOfWeek((m.group(2)));
        dayOfMonth = Integer.parseInt(m.group(5));

        Date date = new Date();
        date.setDayOfWeek(dayOfWeek);
        date.setDay(dayOfMonth);
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
