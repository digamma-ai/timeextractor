package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Date;
import com.codeminders.labs.timeextractor.temporal.entities.DayOfWeek;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Time;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.Utils;

public class TimeIntervalRule14 extends Rule

{
    private String rule = "(" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")[,]?[\\s]*\\b([01]?[0-9]|2[0-3])[-]([01]?[0-9]|2[0-3])\\b";
    protected Locale locale = Locale.US;
    protected double confidence = 0.6;
    private int priority = 4;

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

}
