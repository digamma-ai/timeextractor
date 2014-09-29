package com.codeminders.labs.timeextractor.rules.combine;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entities.Type;

public class CombineRules {

    public TreeSet<TemporalExtraction> combinationRule(TreeSet<TemporalExtraction> rules, String text) {
        List<TemporalExtraction> list = new ArrayList<TemporalExtraction>(rules);
        if (rules == null || rules.size() == 0) {
            return rules;
        }
        TemporalExtraction start = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            TemporalExtraction next = list.get(i);
            String midText = text.substring(start.getToPosition(), next.getFromPosition());
            if (next.getFromPosition() - start.getToPosition() <= 4) {

                TemporalExtraction temporal = joinRules(start, next, midText);
                if (temporal != null) {
                    list.remove(i);
                    list.remove(i - 1);
                    list.add(i - 1, temporal);
                    start = list.get(0);
                    i = i - 1;
                    continue;
                }

            } else {
                start = list.get(i);
            }

        }

        return new TreeSet<TemporalExtraction>(list);
    }

    private TemporalExtraction joinRules(TemporalExtraction temporalA, TemporalExtraction temporalB, String midText) {
        if (temporalA == null | temporalB == null || temporalA.getTemporal() == null || temporalB.getTemporal() == null || temporalA.getTemporal().get(0) == null
                || temporalB.getTemporal().get(0) == null) {
            return null;
        }
        TemporalExtraction temporal = new TemporalExtraction();
        Type typeA = temporalA.getTemporal().get(0).getType();
        Type typeB = temporalB.getTemporal().get(0).getType();

        if (typeA == Type.DATE && typeB == Type.TIME_INTERVAL) {
            temporal = temporalJoinTimeDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DATE_TIME_INTERVAL);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if (typeA == Type.TIME_INTERVAL && typeB == Type.DATE) {
            temporal = temporalJoinTimeDate(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DATE_TIME_INTERVAL);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;

        } else if (typeA == Type.TIME && typeB == Type.DATE) {
            temporal = temporalJoinTimeDate(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.TIME_DATE);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        } else if (typeA == Type.DATE && typeB == Type.TIME) {
            temporal = temporalJoinTimeDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.TIME_DATE);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        } else if (typeA == Type.DATE && typeB == Type.DATE && (midText.contains("to") || (midText.contains("-")))) {
            temporal = temporalJoinDates(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        } else if (typeA == Type.DAY_OF_WEEK && typeB == Type.DATE) {
            temporal = joinDayOfWeekAndDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if (typeA == Type.DATE && typeB == Type.DAY_OF_WEEK) {
            temporal = joinDayOfWeekAndDate(temporalB, temporalA);
            temporal.setTemporalExpression(temporalB.getTemporalExpression() + midText + temporalA.getTemporalExpression());
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if (typeA == Type.DAY_OF_WEEK && (typeB == Type.TIME_INTERVAL || typeB == Type.TIME)) {
            temporal = joinDayOfWeekAndTimeInterval(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.TIME_INTERVAL);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if ((typeA == Type.TIME_INTERVAL || typeA == Type.TIME) && typeB == Type.DAY_OF_WEEK) {
            temporal = joinDayOfWeekAndTimeInterval(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.TIME_INTERVAL);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if ((typeA == Type.DATE || typeA == Type.DAY_OF_WEEK) && typeB == Type.SET) {
            temporal = joinDateAndSet(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.SET);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else {
            return null;
        }

    }

    private TemporalExtraction joinDateAndSet(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        temporalB.getTemporal().get(0).setStartDate(temporalA.getTemporal().get(0).getStartDate());
        temporalB.getTemporal().get(0).setEndDate((temporalA.getTemporal().get(0).getEndDate()));
        temporalB.getTemporal().get(0).setType(Type.SET);

        return temporalB;

    }

    private TemporalExtraction joinDayOfWeekAndTimeInterval(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        temporalB.getTemporal().get(0).setStartDate(temporalA.getTemporal().get(0).getStartDate());
        temporalB.getTemporal().get(0).setEndDate((temporalA.getTemporal().get(0).getEndDate()));
        temporalB.getTemporal().get(0).setType(Type.TIME_INTERVAL);
        return temporalB;

    }

    private TemporalExtraction joinDayOfWeekAndDate(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        temporalB.getTemporal().get(0).getStartDate().getDate().setDayOfWeek(temporalA.getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        temporalB.getTemporal().get(0).getEndDate().getDate().setDayOfWeek(temporalA.getTemporal().get(0).getEndDate().getDate().getDayOfWeek());
        temporalB.getTemporal().get(0).setType(Type.DATE);
        return temporalB;

    }

    /*
     * Method that combines Time and Date intervals, for ex. Friday until 10,
     * Friday morning
     */

    private TemporalExtraction temporalJoinTimeDate(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        if (temporalA.getTemporal().get(0).getStartDate() != null && temporalB.getTemporal().get(0).getStartDate() != null) {
            temporalA.getTemporal().get(0).getStartDate().setTime(temporalB.getTemporal().get(0).getStartDate().getTime());
        }
        if (temporalB.getTemporal().get(0).getEndDate() != null && temporalA.getTemporal().get(0).getEndDate() != null) {
            temporalA.getTemporal().get(0).getEndDate().setTime(temporalB.getTemporal().get(0).getEndDate().getTime());
        }
        temporalA.setConfidence(0.9);
        temporalA.setToPosition(temporalB.getToPosition());
        return temporalA;
    }

    /*
     * Method that combines Date as Date interval
     */

    private TemporalExtraction temporalJoinDates(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        if (temporalB.getTemporal().get(0).getEndDate() != null && temporalA.getTemporal().get(0).getEndDate() != null) {
            temporalA.getTemporal().get(0).setEndDate(temporalB.getTemporal().get(0).getEndDate());
        }
        temporalA.setConfidence(0.9);
        temporalA.setToPosition(temporalB.getToPosition());
        return temporalA;
    }
}
