package com.codeminders.labs.timeextractor.temporal.entites;

import org.joda.time.DateTime;

/*  Class for storing 'time' objects, like 1 pm, 10:30 am, etc, and 'date+time': January 30, 2014 13:00 */

public class CustomDateTime implements CustomTemporal {

    private DateTime dateTime;

    public CustomDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

}
