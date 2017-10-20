package ai.digamma.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.*;
import ai.digamma.utils.TemporalBasicCaseParser;
import org.joda.time.LocalDate;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.TimeDate;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;

public class MidLateMonthRule extends ExtractionRule {
    private double confidence = 0.9;
    private String rule = "\\b(mid|mid-late)[-\\s]*((" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + ")[s]?([.])?)";
    protected int priority = 3;
    protected String example = "mid May, mid-late June";
    protected UUID id = UUID.fromString("507de84b-7da1-416a-9264-d0ab29b7da4e");

    public MidLateMonthRule() {
    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        MonthOfYear currentMonth = TemporalBasicCaseParser.getMonthOfYear(m.group(2));
        int maxDay = 0;
        int month = 0;

        if (currentMonth != null) {
            month = currentMonth.getValue();
            LocalDate date = new LocalDate(new LocalDate().getYear(), month, 1);
            maxDay = date.dayOfMonth().withMaximumValue().getDayOfMonth();
        }

        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Date startDate = new Date();
        Date endDate = new Date();

        start.setRelative(true);
        end.setRelative(true);

        startDate.setMonth(month);
        startDate.setDay(maxDay / 2);
        endDate.setMonth(month);
        endDate.setDay(maxDay);

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
