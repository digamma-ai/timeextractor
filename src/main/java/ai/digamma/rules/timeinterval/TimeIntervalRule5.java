package ai.digamma.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.entities.Rule;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Time;
import ai.digamma.temporal.entities.TimeDate;
import ai.digamma.temporal.entities.TimeTag;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;

// until 5.33 am, after 5pm 

public class TimeIntervalRule5 extends Rule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 5;
    private String rule = "((after|before|until|till|til|by|past)[\\s]*([01]?[0-9]|2[0-3])(([.|:])([0-5][0-9]))?[\\s]*(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M][.]?)))";
    protected String example = "after 19pm, until 5.33 am";
    protected UUID id = UUID.fromString("1a7522ee-7abe-4595-af9e-8cff79d8d88a");

    public TimeIntervalRule5() {
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
        Time time = new Time();
        Temporal temporal = null;
        if (m.group(3) != null) {
            int hours = Integer.parseInt(m.group(3));
            hours = Utils.convertTime(hours, m.group(7));
            time.setHours(hours);
        }
        if (m.group(6) != null) {
            time.setMinutes(Integer.parseInt(m.group(6)));
        }
        TimeTag tag = TemporalBasicCaseParser.getTimeTag(m.group(2));
        if (tag == TimeTag.AFTER) {
            start.setTime(time);
            temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, start, null);
        } else {
            end.setTime(time);
            temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, null, end);
        }
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
