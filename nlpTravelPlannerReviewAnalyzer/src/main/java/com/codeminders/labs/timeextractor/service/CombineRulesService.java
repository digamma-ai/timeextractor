package com.codeminders.labs.timeextractor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entities.Duration;
import com.codeminders.labs.timeextractor.temporal.entities.Frequency;
import com.codeminders.labs.timeextractor.temporal.entities.Set;
import com.codeminders.labs.timeextractor.temporal.entities.Time;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entities.Type;

/* Class is responsible for combining rules by rule types */

public class CombineRulesService {

    private static final Type DAY_OF_WEEK_INTERVAL_TIME = null;

    public TreeSet<TemporalExtraction> combinationRule(TreeSet<TemporalExtraction> rules, String text) {
        List<TemporalExtraction> list = new ArrayList<TemporalExtraction>(rules);
        if (rules == null || rules.size() == 0) {
            return rules;
        }
        TemporalExtraction start = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            TemporalExtraction next = list.get(i);
            String midText = text.substring(start.getToPosition(), next.getFromPosition());
            if (next.getFromPosition() - start.getToPosition() <= 4 && !midText.contains(".") && !midText.contains("&") && !midText.contains("!") && !midText.contains(";")) {
                TemporalExtraction temporal = joinRules(start, next, midText);
                if (temporal != null) {
                    list.remove(i);
                    list.remove(i - 1);
                    list.add(i - 1, temporal);
                    start = list.get(0);
                    i = 0;
                    continue;
                }
                start = list.get(i);

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

        if ((typeA == Type.DATE || typeA == Type.MONTH) && typeB == Type.TIME_INTERVAL) {
            temporal = temporalJoinTimeDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DATE_TIME_INTERVAL);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if (typeA == Type.TIME_INTERVAL && (typeB == Type.DATE || typeB == Type.MONTH)) {
            temporal = temporalJoinTimeDate(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DATE_TIME_INTERVAL);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;

        }

        else if (typeA == Type.DATE_INTERVAL && typeB == Type.TIME_INTERVAL) {
            temporal = temporalJoinTimeDate(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DATE_INTERVAL_TIME_INTERVAL);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;

        }

        else if (typeA == Type.TIME_INTERVAL && typeB == Type.DATE_INTERVAL) {
            temporal = temporalJoinTimeDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DATE_INTERVAL_TIME_INTERVAL);
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
        } else if ((typeA == Type.DATE || typeA == Type.DATE_INTERVAL) && typeB == Type.TIME) {
            temporal = temporalJoinTimeDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.TIME_DATE);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if ((typeA == Type.DATE || typeA == Type.DATE_INTERVAL || typeA == Type.DATE_TIME_INTERVAL || typeA == Type.MONTH) && typeB == Type.YEAR) {
            temporal = joinDateAndYear(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DATE);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if ((typeB == Type.DATE || typeB == Type.DATE_INTERVAL || typeB == Type.DATE_TIME_INTERVAL || typeB == Type.MONTH) && typeA == Type.YEAR) {
            temporal = joinDateAndYear(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DATE);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if (typeA == Type.DATE && typeB == Type.DATE && (midText.contains("to") || midText.contains("-") || midText.contains("–") || midText.contains("—"))) {
            temporal = temporalJoinDates(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        } else if (typeA == Type.YEAR && typeB == Type.YEAR && (midText.contains("to") || midText.contains("-") || midText.contains("–") || midText.contains("—"))) {
            temporal = temporalJoinDates(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            temporal.getTemporal().get(0).setType(Type.YEAR_INTERVAL);

            return temporal;
        }

        else if (typeA == Type.TIME && typeB == Type.TIME && (midText.contains("to") || midText.contains("-") || midText.contains("–") || midText.contains("—"))) {
            temporal = joinTimeAndTime(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if (typeA == Type.DAY_OF_WEEK && typeB == Type.DATE) {
            temporal = joinDayOfWeekAndDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if (typeA == Type.DATE && typeB == Type.DAY_OF_WEEK) {
            if (temporalA.getTemporal().get(0).getStartDate().getDate().getDayOfWeek() == null) {
                temporal = joinDayOfWeekAndDate(temporalB, temporalA);
                temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
                temporal.setFromPosition(temporalA.getFromPosition());
                temporal.setToPosition(temporalB.getToPosition());

                return temporal;
            }
        }

        else if (typeA == Type.DAY_OF_WEEK && (typeB == Type.TIME_INTERVAL || typeB == Type.TIME)) {
            temporal = joinDayOfWeekAndTimeInterval(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if ((typeA == Type.TIME_INTERVAL || typeA == Type.TIME) && typeB == Type.DAY_OF_WEEK) {
            temporal = joinDayOfWeekAndTimeInterval(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if ((typeA == Type.DATE || typeA == Type.DATE_INTERVAL || typeA == Type.DATE_TIME_INTERVAL_INDIRECT || typeA == Type.MONTH) && (typeB == Type.SET || typeB == Type.PERIODIC)) {
            if (temporalB.getTemporal().get(0).getStartDate() == null) {
                temporal = joinDateAndSet(temporalA, temporalB);
                temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
                temporal.getTemporal().get(0).setType(Type.SET);
                temporal.setFromPosition(temporalA.getFromPosition());
                temporal.setToPosition(temporalB.getToPosition());
                return temporal;
            }

        }

        else if ((typeA == Type.DAY_OF_WEEK || typeA == Type.DAY_OF_WEEK_WEEK_OF_MONTH || typeA == Type.DAY_OF_WEEK_TIME_INTERVAL_INDIRECT || typeA == Type.DAY_OF_WEEK_WEEK_OF_MONTH_TIME_INTERVAL_INDIRECT)
                && (typeB == Type.SET || typeB == Type.PERIODIC)) {
            if (temporalB.getTemporal().get(0).getStartDate() == null) {
                temporal = joinDateAndSet(temporalA, temporalB);
                temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
                if (typeA == Type.DAY_OF_WEEK) {
                    temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK_SET);
                } else if (typeA == Type.DAY_OF_WEEK_WEEK_OF_MONTH) {
                    temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK_WEEK_OF_MONTH_SET);
                } else if (typeA == Type.DAY_OF_WEEK_TIME_INTERVAL_INDIRECT) {
                    temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK_TIME_INTERVAL_INDIRECT_SET);
                } else if (typeA == Type.DAY_OF_WEEK_WEEK_OF_MONTH_TIME_INTERVAL_INDIRECT) {
                    temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK_WEEK_OF_MONTH_TIME_INTERVAL_INDIRECT_SET);
                }
                temporal.setFromPosition(temporalA.getFromPosition());
                temporal.setToPosition(temporalB.getToPosition());
                return temporal;
            }

        }

        else if ((typeA == Type.SET) && (typeB == Type.TIME || typeB == Type.TIME_INTERVAL || typeB == Type.TIME_INTERVAL_INDIRECT)) {
            temporal = joinTimeAndSet(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK_SET);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeB == Type.SET) && (typeA == Type.TIME || typeA == Type.TIME_INTERVAL || typeA == Type.TIME_INTERVAL_INDIRECT)) {
            if (temporalA.getTemporal().get(0).getStartDate() != null && temporalA.getTemporal().get(0).getStartDate().getTime() != null) {
                temporal = joinTimeAndSet(temporalA, temporalB);
                temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
                temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK_SET);
                temporal.setFromPosition(temporalA.getFromPosition());
                temporal.setToPosition(temporalB.getToPosition());
                return temporal;
            }
        }

        else if ((typeA == Type.PERIODIC) && (typeB == Type.TIME || typeB == Type.TIME_INTERVAL || typeB == Type.TIME_INTERVAL_INDIRECT)) {
            temporal = joinDateAndSet(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.SET);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeB == Type.PERIODIC) && (typeA == Type.TIME || typeA == Type.TIME_INTERVAL || typeA == Type.TIME_INTERVAL_INDIRECT)) {
            if (temporalA.getTemporal().get(0).getStartDate() != null && temporalA.getTemporal().get(0).getStartDate().getTime() != null) {
                temporal = joinDateAndSet(temporalA, temporalB);
                temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
                temporal.getTemporal().get(0).setType(Type.SET);
                temporal.setFromPosition(temporalA.getFromPosition());
                temporal.setToPosition(temporalB.getToPosition());
                return temporal;
            }
        }

        else if ((typeA == Type.DATE || typeA == Type.DATE_INTERVAL || typeA == Type.DAY_OF_WEEK_AND_TIME || typeA == Type.DAY_OF_WEEK_AND_TIME) && typeB == Type.TIME_INTERVAL_INDIRECT) {
            temporal = joinDayOfWeekAndTimeInterval(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DATE_TIME_INTERVAL_INDIRECT);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeA == Type.DAY_OF_WEEK || typeA == Type.DAY_OF_WEEK_WEEK_OF_MONTH || typeA == Type.DAY_OF_WEEK_INTERVAL || typeA == Type.DAY_OF_WEEK_SET || typeA == Type.DAY_OF_WEEK_WEEK_OF_MONTH_SET)
                && typeB == Type.TIME_INTERVAL_INDIRECT) {
            temporal = joinDayOfWeekAndTimeInterval(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            if (typeA == Type.DAY_OF_WEEK) {
                temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK_TIME_INTERVAL_INDIRECT);
            } else if (typeA == Type.DAY_OF_WEEK_WEEK_OF_MONTH) {
                temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK_WEEK_OF_MONTH_TIME_INTERVAL_INDIRECT);

            }
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeA == Type.DAY_OF_WEEK_SET || typeA == Type.DAY_OF_WEEK_WEEK_OF_MONTH_SET) && (typeB == Type.TIME_INTERVAL_INDIRECT || typeB == Type.TIME_INTERVAL || typeB == Type.TIME)) {
            temporal = joinDayOfWeekAndTimeInterval(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            if (typeA == Type.DAY_OF_WEEK_SET) {
                temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK_TIME_INTERVAL_SET);
            } else if (typeA == Type.DAY_OF_WEEK_WEEK_OF_MONTH_SET) {
                temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK_WEEK_OF_MONTH_TIME_INTERVAL_SET);

            }
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeB == Type.DATE || typeB == Type.DATE_INTERVAL || typeB == Type.DAY_OF_WEEK || typeB == Type.DAY_OF_WEEK_INTERVAL) && typeA == Type.TIME_INTERVAL_INDIRECT) {
            temporal = joinDayOfWeekAndTimeInterval(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DATE_TIME_INTERVAL_INDIRECT);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeA == Type.TIME_DATE || typeA == Type.TIME || typeA == Type.DATE_TIME_INTERVAL || typeA == Type.TIME_INTERVAL || typeA == Type.TIME_DATE_INTERVAl
                || typeA == Type.DATE_INTERVAL_TIME_INTERVAL || typeA == Type.DATE)
                && typeB == Type.TIMEZONE) {
            temporal = joinTimeZoneAndDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if (typeA == Type.EVERY && (typeB == Type.DAY_OF_WEEK || typeB == Type.DAY_OF_WEEK_WEEK_OF_MONTH)) {
            temporal = joinEvery(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if (typeA == Type.EVERY && typeB == Type.TIME) {
            temporal = joinEvery(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if (typeA == Type.EVERY && (typeB == Type.DATE_INTERVAL || typeB == Type.DATE)) {
            temporal = joinEvery(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeA == Type.DATE_TIME_INTERVAL_INDIRECT || typeA == Type.TIME_INTERVAL_INDIRECT) && (typeB == Type.TIME || typeB == Type.TIME_INTERVAL)) {
            temporal = joinIndirectAndDirect(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(typeB);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeA == Type.RELATIVE_TODAY) && (typeB == Type.DATE || typeB == Type.TIME_DATE_INTERVAl || typeB == Type.DATE_TIME_INTERVAL || typeB == Type.DATE_INTERVAL_TIME_INTERVAL)) {
            temporal = joinTodayAndDate(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(typeB);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeB == Type.RELATIVE_TODAY) && (typeA == Type.DATE || typeA == Type.TIME_DATE_INTERVAl || typeA == Type.DATE_TIME_INTERVAL || typeA == Type.DATE_INTERVAL_TIME_INTERVAL)) {
            temporal = joinTodayAndDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(typeB);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeA == Type.RELATIVE_TODAY) && (typeB == Type.TIME || typeB == Type.TIME_INTERVAL || typeB == Type.TIME_INTERVAL_INDIRECT)) {
            temporal = temporalJoinTimeDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(typeB);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeB == Type.RELATIVE_TODAY) && (typeA == Type.TIME || typeA == Type.TIME_INTERVAL || typeA == Type.TIME_INTERVAL_INDIRECT)) {
            temporal = temporalJoinTimeDate(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(typeB);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeA == Type.DAY_OF_WEEK_INTERVAL) && (typeB == Type.TIME || typeB == Type.TIME_INTERVAL || typeB == Type.TIME_INTERVAL_INDIRECT)) {
            temporal = temporalJoinTimeDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK_INTERVAL_TIME);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeB == Type.DAY_OF_WEEK_INTERVAL) && (typeA == Type.TIME || typeA == Type.TIME_INTERVAL || typeA == Type.TIME_INTERVAL_INDIRECT)) {
            temporal = temporalJoinTimeDate(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DAY_OF_WEEK_INTERVAL_TIME);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeA == Type.DAY_OF_WEEK_WEEK_OF_MONTH) && typeB == Type.YEAR) {
            temporal = temporalJoinTimeDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(typeB);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeA == Type.DAY_OF_WEEK_WEEK_OF_MONTH) && (typeB == Type.TIME || typeB == Type.TIME_INTERVAL || typeB == Type.TIME_INTERVAL_INDIRECT)) {
            temporal = temporalJoinTimeDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(typeB);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeA == Type.DAY_OF_WEEK_WEEK_OF_MONTH) && (typeB == Type.SET || typeB == Type.PERIODIC)) {
            temporal = temporalJoinTimeDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(typeB);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeA == Type.DAY_OF_WEEK_AND_TIME) && typeB == Type.DATE) {
            temporal = temporalJoinTimeDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(typeB);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeA == Type.DAY_OF_WEEK_WEEK_OF_MONTH) && typeB == Type.MONTH) {
            temporal = temporalJoinWeekOfMonthAndMonth(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(typeA);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;
        }

        else if ((typeA == Type.DURATION) && (typeB == Type.DURATION)) {
            temporal = joinDuration(temporalA, temporalB);
            if (temporal != null) {
                temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
                temporal.setFromPosition(temporalA.getFromPosition());
                temporal.setToPosition(temporalB.getToPosition());
                return temporal;
            }
            return null;
        }

        return null;

    }

    /* join durations: 1 hour + 30 seconds, etc. */

    private TemporalExtraction joinDuration(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        boolean changed = false;
        Duration a = temporalA.getTemporal().get(0).getDuration();
        Duration b = temporalB.getTemporal().get(0).getDuration();
        if (a.getYears() == 0 && b.getYears() != 0) {
            a.setYears(b.getYears());
            changed = true;
        }
        if (a.getMonths() == 0 && b.getMonths() != 0) {
            a.setMonths(b.getMonths());
            changed = true;
        }
        if (a.getWeeks() == 0 && b.getWeeks() != 0) {
            a.setWeeks(b.getWeeks());
            changed = true;
        }
        if (a.getHours() == 0 && b.getHours() != 0) {
            a.setHours(b.getHours());
            changed = true;
        }
        if (a.getMinutes() == 0 && b.getMinutes() != 0) {
            a.setMinutes(b.getMinutes());
            changed = true;
        }
        if (a.getSeconds() == 0 && b.getSeconds() != 0) {
            a.setSeconds(b.getSeconds());
            changed = true;
        }
        if (changed) {
            return temporalA;

        }
        return null;

    }

    private TemporalExtraction joinEvery(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        Set set = new Set();
        if (temporalB.getTemporal().get(0).getStartDate().getDate() != null) {
            if (temporalB.getTemporal().get(0).getStartDate().getDate().getWeekOfMonth() != null) {
                set.setFrequency(Frequency.MONTHLY);
            } else if (temporalB.getTemporal().get(0).getStartDate().getDate().getYear() != 0) {
                set.setFrequency(Frequency.YEARLY);
            } else if (temporalB.getTemporal().get(0).getStartDate().getDate().getDay() != 0) {
                set.setFrequency(Frequency.MONTHLY);
            } else {
                set.setFrequency(Frequency.WEEKLY);
            }
        }
        temporalB.getTemporal().get(0).setSet(set);
        temporalB.getTemporal().get(0).setType(Type.SET);
        temporalB.setClassOfRuleType(temporalA.getClassOfRuleType() + " join " + temporalB.getClassOfRuleType());
        return temporalB;
    }

    private TemporalExtraction joinIndirectAndDirect(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        TimeDate dateAStart = temporalA.getTemporal().get(0).getStartDate();
        TimeDate dateAEnd = temporalA.getTemporal().get(0).getEndDate();
        TimeDate dateBStart = temporalB.getTemporal().get(0).getStartDate();
        TimeDate dateBEnd = temporalB.getTemporal().get(0).getEndDate();

        if (dateAStart != null && dateAStart.getTime() != null && dateBStart != null && dateBStart.getTime() != null) {
            temporalA.getTemporal().get(0).getStartDate().setTime(temporalB.getTemporal().get(0).getStartDate().getTime());
        }
        if (dateAEnd != null && dateAEnd.getTime() != null && dateBEnd != null && dateBEnd.getTime() != null) {
            temporalA.getTemporal().get(0).getEndDate().setTime(temporalB.getTemporal().get(0).getEndDate().getTime());
        }
        temporalA.getTemporal().get(0).setType(Type.DATE_TIME_INTERVAL);
        temporalA.setClassOfRuleType(temporalA.getClassOfRuleType() + " join " + temporalB.getClassOfRuleType());
        return temporalA;
    }

    private TemporalExtraction joinTodayAndDate(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        temporalA.setClassOfRuleType(temporalA.getClassOfRuleType() + " join " + temporalB.getClassOfRuleType());
        return temporalA;
    }

    private TemporalExtraction joinTimeAndTime(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        Time endTime = temporalB.getTemporal().get(0).getStartDate().getTime();

        if (endTime != null) {
            temporalA.getTemporal().get(0).getEndDate().setTime(endTime);

        }
        temporalA.setConfidence(0.8);
        temporalA.getTemporal().get(0).setType(Type.TIME_INTERVAL);
        temporalA.setClassOfRuleType(temporalA.getClassOfRuleType() + " join " + temporalB.getClassOfRuleType());
        return temporalA;
    }

    private TemporalExtraction joinTimeZoneAndDate(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        int timezone = temporalB.getTemporal().get(0).getStartDate().getTime().getTimezoneOffset();
        TimeDate timeDateStart = temporalA.getTemporal().get(0).getStartDate();
        TimeDate timeDateEnd = temporalA.getTemporal().get(0).getEndDate();

        if (timeDateStart != null && timeDateStart.getTime() != null && timeDateStart.getTime().getTimezoneOffset() == -1000) {
            temporalA.getTemporal().get(0).getStartDate().getTime().setTimezone(timezone);
            temporalA.getTemporal().get(0).getStartDate().getTime().setTimezoneName(temporalB.getTemporal().get(0).getStartDate().getTime().getTimezoneName());
        }
        if (timeDateEnd != null && timeDateEnd.getTime() != null && temporalA.getTemporal().get(0).getEndDate().getTime().getTimezoneOffset() == -1000) {
            temporalA.getTemporal().get(0).getEndDate().getTime().setTimezone(timezone);
            temporalA.getTemporal().get(0).getEndDate().getTime().setTimezoneName(temporalB.getTemporal().get(0).getStartDate().getTime().getTimezoneName());

        }
        temporalA.setConfidence(0.8);
        temporalA.setClassOfRuleType(temporalA.getClassOfRuleType() + " join " + temporalB.getClassOfRuleType());
        return temporalA;
    }

    private TemporalExtraction joinDateAndYear(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        int year = temporalB.getTemporal().get(0).getStartDate().getDate().getYear();
        temporalA.getTemporal().get(0).getStartDate().getDate().setYear(year);
        temporalA.getTemporal().get(0).getEndDate().getDate().setYear(year);
        temporalA.setConfidence(0.8);
        temporalA.setClassOfRuleType(temporalA.getClassOfRuleType() + " join " + temporalB.getClassOfRuleType());
        return temporalA;
    }

    private TemporalExtraction joinDateAndSet(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        temporalB.getTemporal().get(0).setStartDate(temporalA.getTemporal().get(0).getStartDate());
        temporalB.getTemporal().get(0).setEndDate((temporalA.getTemporal().get(0).getEndDate()));
        temporalB.getTemporal().get(0).setType(Type.SET);
        temporalB.setClassOfRuleType(temporalA.getClassOfRuleType() + " join " + temporalB.getClassOfRuleType());
        return temporalB;

    }

    private TemporalExtraction joinTimeAndSet(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        if (temporalB.getTemporal().get(0).getStartDate() != null && temporalA.getTemporal().get(0).getStartDate() != null) {
            temporalB.getTemporal().get(0).getStartDate().setTime(temporalA.getTemporal().get(0).getStartDate().getTime());
        }
        if (temporalB.getTemporal().get(0).getEndDate() != null && temporalA.getTemporal().get(0).getEndDate() != null) {
            temporalB.getTemporal().get(0).getEndDate().setTime(temporalA.getTemporal().get(0).getEndDate().getTime());
        }
        temporalB.getTemporal().get(0).setType(Type.SET);
        temporalB.setClassOfRuleType(temporalA.getClassOfRuleType() + " join " + temporalB.getClassOfRuleType());
        return temporalB;

    }

    private TemporalExtraction joinDayOfWeekAndTimeInterval(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        TimeDate temporalBStartDate = temporalB.getTemporal().get(0).getStartDate();
        TimeDate temporalAStartDate = temporalA.getTemporal().get(0).getStartDate();
        TimeDate temporalAEndDate = temporalB.getTemporal().get(0).getEndDate();
        TimeDate temporalBEndDate = temporalA.getTemporal().get(0).getEndDate();

        if (temporalAStartDate != null && temporalBStartDate != null) {
            temporalB.getTemporal().get(0).getStartDate().setDate(temporalA.getTemporal().get(0).getStartDate().getDate());
        }
        if (temporalAEndDate != null && temporalBEndDate != null) {
            temporalB.getTemporal().get(0).getEndDate().setDate(temporalA.getTemporal().get(0).getEndDate().getDate());
        }
        temporalB.setConfidence(0.8);
        temporalB.setClassOfRuleType(temporalA.getClassOfRuleType() + " join " + temporalB.getClassOfRuleType());
        return temporalB;

    }

    private TemporalExtraction joinDayOfWeekAndDate(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        temporalB.getTemporal().get(0).getStartDate().getDate().setDayOfWeek(temporalA.getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        temporalB.getTemporal().get(0).getEndDate().getDate().setDayOfWeek(temporalA.getTemporal().get(0).getEndDate().getDate().getDayOfWeek());
        temporalB.getTemporal().get(0).getStartDate().getDate().setWeekOfMonth(temporalA.getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());
        temporalB.getTemporal().get(0).getEndDate().getDate().setWeekOfMonth(temporalA.getTemporal().get(0).getEndDate().getDate().getWeekOfMonth());
        temporalB.getTemporal().get(0).setType(Type.DATE);
        temporalB.setConfidence(0.8);
        temporalB.setClassOfRuleType(temporalA.getClassOfRuleType() + " join " + temporalB.getClassOfRuleType());
        return temporalB;
    }

    private TemporalExtraction temporalJoinWeekOfMonthAndMonth(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        if (temporalA.getTemporal().get(0).getStartDate() != null && temporalB.getTemporal().get(0).getStartDate() != null) {
            temporalA.getTemporal().get(0).getStartDate().getDate().setMonth(temporalB.getTemporal().get(0).getStartDate().getDate().getMonth());
        }
        if (temporalB.getTemporal().get(0).getEndDate() != null && temporalA.getTemporal().get(0).getEndDate() != null) {
            temporalA.getTemporal().get(0).getEndDate().getDate().setMonth(temporalB.getTemporal().get(0).getEndDate().getDate().getMonth());
        }
        temporalA.setConfidence(0.8);
        temporalA.setClassOfRuleType(temporalA.getClassOfRuleType() + " join " + temporalB.getClassOfRuleType());
        return temporalA;
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
        temporalA.setClassOfRuleType(temporalA.getClassOfRuleType() + " join " + temporalB.getClassOfRuleType());
        return temporalA;
    }

    /*
     * Method that combines Date as Date interval
     */

    private TemporalExtraction temporalJoinDates(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        if (temporalB.getTemporal().get(0).getEndDate() != null && temporalA.getTemporal().get(0).getEndDate() != null) {
            if (temporalA.getTemporal().get(0).getStartDate().getDate().getYear() == 0) {
                temporalA.getTemporal().get(0).getStartDate().getDate().setYear(temporalB.getTemporal().get(0).getEndDate().getDate().getYear());
            }
            temporalA.getTemporal().get(0).setEndDate(temporalB.getTemporal().get(0).getEndDate());
        }
        temporalA.setConfidence(0.9);
        temporalA.setToPosition(temporalB.getToPosition());
        temporalA.setClassOfRuleType(temporalA.getClassOfRuleType() + " join " + temporalB.getClassOfRuleType());
        return temporalA;
    }
}
