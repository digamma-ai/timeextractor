package ai.digamma.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.temporal.entities.TimeDate;
import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.MonthOfYear;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;

public class DateInterval2 extends ExtractionRule {
    private String rule = "\\b(from[\\s]?)?((\\b[1-9]\\b)|(\\b[1-2][0-9]\\b)|(\\b[3][0-1]\\b))[\\s]*((to)|[-])[\\s]*((\\b[1-9]\\b)|(\\b[1-2][0-9]\\b)|(\\b[3][0-1]\\b))[\\s]*("
            + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + ")[\\s,.]*(([12][0-9])\\d\\d)?";

    private double confidence = 0.9;
    private int priority = 5;
    protected String example = "from 22 to 26 January 2014";
    protected UUID id = UUID.fromString("364d8630-4592-4a24-9397-2634b0623bd7");

    public DateInterval2() {
    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        int startDay = Integer.parseInt(m.group(2));
        int endDay = Integer.parseInt(m.group(8));
        int monthFrom = 0;
        int monthTo = 0;

        int year = 0;
        MonthOfYear monthEnumFrom = TemporalBasicCaseParser.getMonthOfYear(m.group(12));
        if (monthEnumFrom != null) {
            monthFrom = monthEnumFrom.getValue();
        }
        MonthOfYear monthEnumTo = TemporalBasicCaseParser.getMonthOfYear(m.group(12));
        if (monthEnumTo != null) {
            monthTo = monthEnumTo.getValue();
        }
        if (m.group(15) != null) {
            year = Integer.parseInt(m.group(15));
        }
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Date startDate = new Date();
        Date endDate = new Date();

        startDate.setDay(startDay);
        startDate.setMonth(monthFrom);
        startDate.setYear(year);
        endDate.setDay(endDay);
        endDate.setMonth(monthTo);
        endDate.setYear(year);

        start.setDate(startDate);
        end.setDate(endDate);

        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE_INTERVAL, start, end);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
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
