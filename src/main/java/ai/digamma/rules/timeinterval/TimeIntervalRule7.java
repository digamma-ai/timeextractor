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
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;

//11.30 am-12.30 pm

public class TimeIntervalRule7 extends Rule {
    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 6;
    private String rule = "\\b((from|between)[\\s]*)?(([01]?[0-9]|2[0-3]|1[0-9])([:.,]([0-5][0-9]))?)[\\s]*(([p,P][.]?[m,M]?[.]?)|([a,A][.]?[m,M]?[.]?))?[\\s]*(�|-|to|until|till|til|up to|through|thru)[\\s]*(\\b([01]?[0-9]|2[0-3]|1[0-9])([:.,]([0-5][0-9]))?)[\\s]*(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M][.]?))";
    protected String example = "11.30 am-12.30 pm";
    protected UUID id = UUID.fromString("3ab0f1b9-c85d-415b-8f62-0c355bf8de70");

    public TimeIntervalRule7() {

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

        if (m.group(4) != null) {
            timeFrom.setHours(Integer.parseInt(m.group(4)));
            if (m.group(7) != null) {
                timeFrom.setHours(Utils.convertTime(timeFrom.getHours(), m.group(7)));
            }
            if (m.group(7) == null && m.group(15) != null) {
                timeFrom.setHours(Utils.convertTime(timeFrom.getHours(), m.group(15)));
            }
        }
        if (m.group(6) != null) {
            timeFrom.setMinutes(Integer.parseInt(m.group(6)));
        }

        if (m.group(12) != null) {
            timeTo.setHours(Integer.parseInt(m.group(12)));
            if (m.group(15) != null) {
                timeTo.setHours(Utils.convertTime(timeTo.getHours(), m.group(15)));
            }
        }

        if (m.group(14) != null) {
            timeTo.setMinutes(Integer.parseInt(m.group(14)));
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
