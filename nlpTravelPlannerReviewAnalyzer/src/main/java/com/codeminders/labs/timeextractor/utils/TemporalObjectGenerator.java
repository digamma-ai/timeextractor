package com.codeminders.labs.timeextractor.utils;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;

public class TemporalObjectGenerator {

    public static Temporal generateTemporalObject(Type type, Date date) {

        Temporal temporal = new Temporal();
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        start.setDate(date);
        end.setDate(date);

        temporal.setStartDate(start);
        temporal.setStartDate(start);
        temporal.setEndDate(end);
        temporal.setType(Type.DATE);

        return temporal;

    }

}
