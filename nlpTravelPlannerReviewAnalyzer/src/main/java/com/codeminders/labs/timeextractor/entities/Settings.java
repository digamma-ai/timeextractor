package com.codeminders.labs.timeextractor.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.joda.time.LocalDateTime;

import com.codeminders.labs.timeextractor.utils.Utils;

public class Settings {

    private LocalDateTime date;
    private int timezoneOffset = 0;
    private Set<UUID> rulesToIgnore = new HashSet<UUID>();

    public Settings(String date, String timezoneOffset, String rulesToIgnore) throws Exception {
        // validation comes here
        // this.date = date;
        if (timezoneOffset != null) {
            this.timezoneOffset = Integer.parseInt(timezoneOffset);
        }
        if (rulesToIgnore != null) {
            this.rulesToIgnore = Utils.getSetofUUIDsFromString(rulesToIgnore);
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

    public Set<UUID> getRulesToIgnore() {
        return rulesToIgnore;
    }

    public void setRulesToIgnore(Set<UUID> rulesToIgnore) {
        this.rulesToIgnore = rulesToIgnore;
    }

}
