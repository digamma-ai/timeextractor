package ai.digamma.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.entities.Rule;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.TimeDate;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.temporal.entities.Time;
import ai.digamma.utils.Utils;

//5-9pm

public class TimeIntervalRule6 extends Rule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 5;
    private String rule = "\\b(from[\\s]*)?(([01]?[0-9]|2[0-3]|1[0-9])[-]([01]?[0-9]|2[0-3]|1[0-9])(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M][.]?)\\b))";
    protected String example = "from 5-9pm, 7-10am";
    protected UUID id = UUID.fromString("b2360b5c-59cc-4689-91f1-9325ac219e06");

    public TimeIntervalRule6() {

    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;

    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Time timeFrom = new Time();
        Time timeTo = new Time();

        Temporal temporal = null;

        if (m.group(3) != null) {
            timeFrom.setHours(Integer.parseInt(m.group(3)));
            if (m.group(5) != null) {
                timeFrom.setHours(Utils.convertTime(timeFrom.getHours(), m.group(5)));
            }
        }
        if (m.group(4) != null) {
            timeTo.setHours(Integer.parseInt(m.group(4)));
            if (m.group(5) != null) {
                timeTo.setHours(Utils.convertTime(timeTo.getHours(), m.group(5)));
            }
        }

        start.setTime(timeFrom);
        end.setTime(timeTo);

        temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, start, end);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;

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

}
