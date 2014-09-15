package com.codeminders.labs.timeextractor.rest.entities;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

/* Class transforms annotation object to htmltemporal object */

public class Annotation2HtmlTemporalConversion {

    public List<HtmlTemporal> convert(List<Temporal> temporals) {
        List<HtmlTemporal> htmlTemporals = new ArrayList<HtmlTemporal>();
        if (temporals == null) {
            return null;
        }
        for (Temporal temporal : temporals) {
            if (temporal.getType() == Type.DURATION) {
                DurationHtml duration = new DurationHtml(temporal);
                htmlTemporals.add(duration);
            } else if (temporal.getType() == Type.SET) {
                SetHtml set = new SetHtml(temporal);
                htmlTemporals.add(set);
            } else if (temporal.getType() == Type.DATE_INTERVAL_TIME_INTERVAL) {
                // TODO:
            } else {
                TimeDateHtml timeDate = new TimeDateHtml(temporal);
                htmlTemporals.add(timeDate);
            }
        }
        return htmlTemporals;
    }

}
