package com.codeminders.labs.timeextractor.temporal.parser;

import org.joda.time.DateTime;

import com.codeminders.labs.timeextractor.temporal.entites.CustomDateTime;
import edu.stanford.nlp.time.SUTime.Temporal;

public class AnnotationToObjectParser {

    public CustomDateTime getCustomDateAndTime(String currentDate, Temporal temporal) {

        CustomDateTime customDateTime = null;

        switch (temporal.getTimexValue()) {

        // 2012/09/01
        case ("DateEventPattern1"):
            String date = temporal.getTimeLabel();
            DateTime dateTime = DateTime.parse(date.replaceAll("/", "-"));
            customDateTime = new CustomDateTime(dateTime);
            break;
        }

        return customDateTime;
    }

}
