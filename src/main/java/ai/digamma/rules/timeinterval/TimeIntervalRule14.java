package ai.digamma.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.Rule;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.DayOfWeek;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Time;
import ai.digamma.temporal.entities.TimeDate;
import ai.digamma.temporal.entities.Type;

public class TimeIntervalRule14 extends Rule

{
    private String rule = "(" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")[,]?[\\s]*\\b([01]?[0-9]|2[0-3])[-]([01]?[0-9]|2[0-3])\\b";
    protected Locale locale = Locale.US;
    protected double confidence = 0.5;
    private int priority = 4;
    protected String example = "Monday, 11-16";
    protected UUID id = UUID.fromString("f4ffb605-f71d-4860-b9cd-8cde74cb603b");

    public TimeIntervalRule14() {
    }

    @Override
    public Type getType() {
        return Type.DATE_TIME_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Time timeFrom = new Time();
        Time timeTo = new Time();
        Date datefrom = new Date();
        Date dateTo = new Date();

        if (m.group(1) != null) {
            DayOfWeek dayOfWeek = null;
            dayOfWeek = TemporalBasicCaseParser.getDayOfWeek(m.group(1));

            if (dayOfWeek != null) {
                datefrom.setDayOfWeek(dayOfWeek);
                dateTo.setDayOfWeek(dayOfWeek);

            }
        }
        int hoursFrom = Integer.parseInt(m.group(4));
        int hoursTo = Integer.parseInt(m.group(5));
        timeFrom.setHours(hoursFrom);
        timeTo.setHours(hoursTo);
        start.setDate(datefrom);
        start.setTime(timeFrom);
        end.setDate(dateTo);
        end.setTime(timeTo);

        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, start, end);
        List<Temporal> result = new ArrayList<Temporal>();
        result.add(temporal);
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

    @Override
    public int compareTo(Rule o) {
        return super.compare(this, o);
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
