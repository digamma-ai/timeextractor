package ai.digamma.utils;

import java.util.List;

import ai.digamma.temporal.entities.TimeDate;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.Duration;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Time;
import ai.digamma.temporal.entities.Type;

public class TemporalObjectGenerator {

    public static Temporal generateTemporalDate(Type type, Date date) {

        Temporal temporal = new Temporal();
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        start.setDate(date);
        end.setDate(date);

        temporal.setStartDate(start);
        temporal.setStartDate(start);
        temporal.setEndDate(end);
        if (type == null) {
            temporal.setType(Type.DATE);
        } else {
            temporal.setType(type);
        }

        return temporal;

    }

    public static List<Temporal> setTemporalType(List<Temporal> temporals, Type type) {
        for (Temporal temporal : temporals) {
            if (temporal != null) {
                temporal.setType(type);
            }
        }
        return temporals;
    }

    public static Temporal generateTemporalTime(Type type, Time time) {

        Temporal temporal = new Temporal();
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        start.setTime(time);
        end.setTime(time);

        temporal.setStartDate(start);
        temporal.setStartDate(start);
        temporal.setEndDate(end);
        temporal.setType(Type.TIME);

        return temporal;

    }

    public static Temporal generateTemporalTime(Type type, TimeDate start, TimeDate end) {
        Temporal temporal = new Temporal();
        temporal.setStartDate(start);
        temporal.setEndDate(end);
        temporal.setType(type);
        return temporal;

    }

    public static Temporal generateTemporalTime(Type type, TimeDate date) {
        Temporal temporal = new Temporal();
        temporal.setStartDate(date);
        temporal.setEndDate(date);
        temporal.setType(type);
        return temporal;

    }

    public static Temporal generateTemporalDuration(Type type, Duration duration) {
        if (duration == null) {
            return null;
        }
        Temporal temporal = new Temporal();
        temporal.setDuration(duration);
        temporal.setType(type);
        return temporal;

    }

}
