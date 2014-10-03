package com.codeminders.labs.timeextractor.rules.combine;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entities.Frequency;
import com.codeminders.labs.timeextractor.temporal.entities.Set;
import com.codeminders.labs.timeextractor.temporal.entities.Time;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
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
            if (next.getFromPosition() - start.getToPosition() <= 4 && !midText.contains(".") && !midText.contains("&") && !midText.contains(";")) {

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

        for (int i = 0; i < list.size(); i++) {
            TemporalExtraction next = list.get(i);
            if (next.getTemporal() != null && next.getTemporal().get(0) != null && next.getTemporal().get(0).getType() != null) {
                if (next.getTemporal().get(0).getType() == Type.TIMEZONE || next.getTemporal().get(0).getType() == Type.EVERY) {
                    list.remove(i);
                    i = i - 1;
                }
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

        else if ((typeA == Type.DATE || typeA == Type.DAY_OF_WEEK || typeA == Type.DATE_INTERVAL || typeA == Type.DATE_TIME_INTERVAL) && typeB == Type.YEAR) {
            temporal = joinDateAndYear(temporalA, temporalB);
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
            temporal.getTemporal().get(0).setType(Type.DATE_TIME_INTERVAL);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if ((typeA == Type.TIME_INTERVAL || typeA == Type.TIME) && typeB == Type.DAY_OF_WEEK) {
            temporal = joinDayOfWeekAndTimeInterval(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DATE_TIME_INTERVAL);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if ((typeA == Type.DATE || typeA == Type.DATE_INTERVAL || typeA == Type.DAY_OF_WEEK || typeA == Type.DATE_TIME_INTERVAL_INDIRECT) && typeB == Type.SET) {
            if (temporalB.getTemporal().get(0).getStartDate() == null) {
                temporal = joinDateAndSet(temporalA, temporalB);
                temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
                temporal.getTemporal().get(0).setType(Type.SET);
                temporal.setFromPosition(temporalA.getFromPosition());
                temporal.setToPosition(temporalB.getToPosition());
                return temporal;
            }

        }

        else if ((typeA == Type.SET) && (typeB == Type.TIME || typeB == Type.TIME_INTERVAL || typeB == Type.TIME_INTERVAL_INDIRECT)) {
            if (temporalA.getTemporal().get(0).getStartDate() == null || temporalA.getTemporal().get(0).getStartDate().getTime() == null) {
                temporal = temporalJoinTimeDate(temporalA, temporalB);
                temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
                temporal.getTemporal().get(0).setType(Type.SET);
                temporal.setFromPosition(temporalA.getFromPosition());
                temporal.setToPosition(temporalB.getToPosition());
                return temporal;
            }

        }

        else if ((typeB == Type.SET) && (typeA == Type.TIME || typeA == Type.TIME_INTERVAL || typeA == Type.TIME_INTERVAL_INDIRECT)) {
            if (temporalA.getTemporal().get(0).getStartDate() != null && temporalA.getTemporal().get(0).getStartDate().getTime() == null) {
                temporal = temporalJoinTimeDate(temporalB, temporalA);
                temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
                temporal.getTemporal().get(0).setType(Type.SET);
                temporal.setFromPosition(temporalA.getFromPosition());
                temporal.setToPosition(temporalB.getToPosition());
                return temporal;
            }

        }

        else if ((typeA == Type.DATE || typeA == Type.DATE_INTERVAL || typeA == Type.DAY_OF_WEEK) && typeB == Type.TIME_INTERVAL_INDIRECT) {
            temporal = joinDayOfWeekAndTimeInterval(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DATE_TIME_INTERVAL_INDIRECT);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;

        }

        else if ((typeB == Type.DATE || typeB == Type.DATE_INTERVAL || typeB == Type.DAY_OF_WEEK) && typeA == Type.TIME_INTERVAL_INDIRECT) {
            temporal = joinDayOfWeekAndTimeInterval(temporalB, temporalA);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.getTemporal().get(0).setType(Type.DATE_TIME_INTERVAL_INDIRECT);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;

        }

        else if ((typeA == Type.TIME_DATE || typeA == Type.TIME || typeA == Type.DATE_TIME_INTERVAL || typeA == Type.TIME_DATE_INTERVAl || typeA == Type.DATE_INTERVAL_TIME_INTERVAL)
                && typeB == Type.TIMEZONE) {
            temporal = joinTimeZoneAndDate(temporalA, temporalB);
            temporal.setTemporalExpression(temporalA.getTemporalExpression() + midText + temporalB.getTemporalExpression());
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());

            return temporal;
        }

        else if (typeA == Type.EVERY && typeB == Type.DAY_OF_WEEK) {
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
            temporal.getTemporal().get(0).setType(Type.SET);
            temporal.setFromPosition(temporalA.getFromPosition());
            temporal.setToPosition(temporalB.getToPosition());
            return temporal;

        }

        return null;

    }

    private TemporalExtraction joinEvery(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        Set set = new Set();
        if (temporalB.getTemporal().get(0).getStartDate().getDate().getWeekOfMonth() != null) {
            set.setFrequency(Frequency.MONTHLY);
        } else if (temporalB.getTemporal().get(0).getStartDate().getDate().getYear() != 0) {
            set.setFrequency(Frequency.YEARLY);
        } else if (temporalB.getTemporal().get(0).getStartDate().getDate().getDay() != 0) {
            set.setFrequency(Frequency.MONTHLY);
        } else {
            set.setFrequency(Frequency.WEEKLY);
        }
        temporalB.getTemporal().get(0).setSet(set);
        temporalB.getTemporal().get(0).setType(Type.SET);
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
        return temporalA;
    }

    private TemporalExtraction joinTimeAndTime(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        Time endTime = temporalB.getTemporal().get(0).getStartDate().getTime();

        if (endTime != null) {
            temporalA.getTemporal().get(0).getEndDate().setTime(endTime);

        }
        temporalA.setConfidence(0.8);
        temporalA.getTemporal().get(0).setType(Type.TIME_INTERVAL);
        return temporalA;
    }

    private TemporalExtraction joinTimeZoneAndDate(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        int timezone = temporalB.getTemporal().get(0).getStartDate().getTime().getTimezoneOffset();
        if (temporalA.getTemporal().get(0).getStartDate().getTime().getTimezoneOffset() == 0) {
            temporalA.getTemporal().get(0).getStartDate().getTime().setTimezone(timezone);
        }
        if (temporalA.getTemporal().get(0).getEndDate().getTime().getTimezoneOffset() == 0) {
            temporalA.getTemporal().get(0).getEndDate().getTime().setTimezone(timezone);

        }
        temporalA.setConfidence(0.8);

        return temporalA;
    }

    private TemporalExtraction joinDateAndYear(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        int year = temporalB.getTemporal().get(0).getStartDate().getDate().getYear();
        if (temporalA.getTemporal().get(0).getStartDate().getDate().getYear() == 0) {
            temporalA.getTemporal().get(0).getStartDate().getDate().setYear(year);
        }
        if (temporalA.getTemporal().get(0).getEndDate().getDate().getYear() == 0) {

            temporalA.getTemporal().get(0).getEndDate().getDate().setYear(year);
        }
        temporalA.setConfidence(0.8);

        return temporalA;
    }

    private TemporalExtraction joinDateAndSet(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        temporalB.getTemporal().get(0).setStartDate(temporalA.getTemporal().get(0).getStartDate());
        temporalB.getTemporal().get(0).setEndDate((temporalA.getTemporal().get(0).getEndDate()));
        temporalB.getTemporal().get(0).setType(Type.SET);

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
        temporalB.getTemporal().get(0).setType(Type.TIME_INTERVAL);
        temporalB.setConfidence(0.8);
        return temporalB;

    }

    private TemporalExtraction joinDayOfWeekAndDate(TemporalExtraction temporalA, TemporalExtraction temporalB) {
        temporalB.getTemporal().get(0).getStartDate().getDate().setDayOfWeek(temporalA.getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        temporalB.getTemporal().get(0).getEndDate().getDate().setDayOfWeek(temporalA.getTemporal().get(0).getEndDate().getDate().getDayOfWeek());
        temporalB.getTemporal().get(0).getStartDate().getDate().setWeekOfMonth(temporalA.getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());
        temporalB.getTemporal().get(0).getEndDate().getDate().setWeekOfMonth(temporalA.getTemporal().get(0).getEndDate().getDate().getWeekOfMonth());

        temporalB.getTemporal().get(0).setType(Type.DATE);
        temporalB.setConfidence(0.8);

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
