package ai.digamma.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.Rule;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.MonthOfYear;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.TimeDate;
import ai.digamma.temporal.entities.Type;

public class DateInterval4 extends Rule {
    private String rule = "(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + ")" + "[\\s]*((\\b[1-9]\\b)|(\\b[1-2][0-9]\\b)|(\\b[3][0-1]\\b))[\\s]*(-|to)[\\s]*"
            + "(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + ")" + "[\\s]*((\\b[1-9]\\b)|(\\b[1-2][0-9]\\b)|(\\b[3][0-1]\\b))" + "([\\s,.]*([12][0-9])\\d\\d)?";

    private double confidence = 0.9;
    private int priority = 5;
    protected String example = "January 22 - March 26, 2014";
    protected UUID id = UUID.fromString("3d2d6ff8-3057-4d61-9564-01f98124739c");

    public DateInterval4() {
    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        int monthFrom = 0;
        int monthTo = 0;

        int year = 0;
        MonthOfYear monthEnumFrom = TemporalBasicCaseParser.getMonthOfYear(m.group(1));
        if (monthEnumFrom != null) {
            monthFrom = monthEnumFrom.getValue();
        }
        int startDay = Integer.parseInt(m.group(4));
        int endDay = Integer.parseInt(m.group(12));

        MonthOfYear monthEnumTo = TemporalBasicCaseParser.getMonthOfYear(m.group(9));
        if (monthEnumTo != null) {
            monthTo = monthEnumTo.getValue();
        }
        if (m.group(17) != null) {
            year = Integer.parseInt(m.group(17).trim());
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
    public int compareTo(Rule o) {
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
