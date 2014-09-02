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
            String timeString = date.replace("/", "-") + "T00:00:00+00:00";
            String timeString2 = date.replace("/", "-") + "T23:59:59+00:00";

            DateTime dt = new DateTime(timeString);
            DateTime dt2 = new DateTime(timeString2);

            System.out.println(dt);
            System.out.println(dt2);

            break;
        }

        return customDateTime;
    }

}
