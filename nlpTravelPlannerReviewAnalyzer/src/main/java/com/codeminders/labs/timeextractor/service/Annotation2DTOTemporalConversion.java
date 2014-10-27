package com.codeminders.labs.timeextractor.service;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.dto.DTODuration;
import com.codeminders.labs.timeextractor.dto.DTODurationInterval;
import com.codeminders.labs.timeextractor.dto.DTOSet;
import com.codeminders.labs.timeextractor.dto.DTOTemporal;
import com.codeminders.labs.timeextractor.dto.DTOTimeDate;
import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;

/* Class transforms annotation object to htmltemporal object */

public class Annotation2DTOTemporalConversion {

    public List<DTOTemporal> convert(TemporalExtraction temporals) {
        List<DTOTemporal> htmlTemporals = new ArrayList<DTOTemporal>();
        if (temporals == null || temporals.getTemporal() == null) {
            return null;
        }
        List<Temporal> annotations = temporals.getTemporal();
        for (Temporal temporal : annotations) {
            if (temporal == null) {
                continue;
            }
            if (temporal.getType() == Type.DURATION) {
                DTODuration duration = new DTODuration(temporal);
                htmlTemporals.add(duration);
            } else if (temporal.getType() == Type.SET || temporal.getType() == Type.DAY_OF_WEEK_SET || temporal.getType() == Type.DAY_OF_WEEK_WEEK_OF_MONTH_SET) {
                DTOSet set = new DTOSet(temporal);
                htmlTemporals.add(set);
            }

            else if (temporal.getType() == Type.DURATION_INTERVAL) {
                DTODurationInterval interval = new DTODurationInterval(temporal);
                htmlTemporals.add(interval);
            } else {
                DTOTimeDate timeDate = new DTOTimeDate(temporal);
                htmlTemporals.add(timeDate);
            }
        }
        return htmlTemporals;
    }

    public Type getGeneralType(Type type) {
        if (type == null) {
            return null;
        } else if (type == Type.SET) {
            return Type.SET;
        } else if (type == Type.DATE_INTERVAL) {
            return Type.DATE_INTERVAL;
        } else if (type == Type.DURATION) {
            return Type.DURATION;
        } else if (type == Type.PERIODIC) {
            return Type.PERIODIC;
        } else {
            return Type.TIME_DATE;
        }

    }

}
