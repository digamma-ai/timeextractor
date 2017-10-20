package ai.digamma.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.TimeDate;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.temporal.entities.DayOfWeek;
import ai.digamma.utils.Utils;

// Sat/Sun
public class DayOfWeekIntervalRule2 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.6;
    private int priority = 2;
    private String rule = "\\b" + "(" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")" + "\\/" + "(" + TemporalConstants.DAY_OF_WEEK + "|"
            + TemporalConstants.DAY_OF_WEEK_EASY + ")";
    protected String example = "Mon/Tue";
    protected UUID id = UUID.fromString("c91a59c6-3b4f-49ad-a695-4d8abdf69da3");

    public DayOfWeekIntervalRule2() {
    }

    @Override
    public Type getType() {
        return Type.DAY_OF_WEEK_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Date startDate = new Date();
        Date endDate = new Date();

        DayOfWeek startDayOfWeek = TemporalBasicCaseParser.getDayOfWeek(m.group(3));
        DayOfWeek endDayOfWeek = TemporalBasicCaseParser.getDayOfWeek(m.group(4));

        if (startDayOfWeek != null && endDayOfWeek != null) {
            startDate.setDayOfWeek(startDayOfWeek);
            endDate.setDayOfWeek(endDayOfWeek);

        }

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
}
