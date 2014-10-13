package com.codeminders.labs.timeextractor.entities;

import org.joda.time.LocalDateTime;

public class Settings {

    private LocalDateTime date;
    private int timezoneOffset = 0;

    public Settings(String date, String timezoneOffset) {
        // validation comes here
        // this.date = date;
        if (timezoneOffset != null) {
            this.timezoneOffset = Integer.parseInt(timezoneOffset);
        }
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(int timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

}
