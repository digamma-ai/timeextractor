package ai.digamma.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.joda.time.LocalDateTime;

import ai.digamma.utils.Utils;

public class Settings {

    private LocalDateTime date;
    private int timezoneOffset = 0;
    private Set<UUID> rulesToIgnore = new HashSet<UUID>();
    private boolean includeOnlyLatestDates;

    public Settings(String date, String timezoneOffset, String rulesToIgnore, int includeOnlyLatestDates) throws Exception {
        // validation comes here
        if(date != null){
            this.date = Utils.convertInputDate(date);
        }
        if (timezoneOffset != null) {
            this.timezoneOffset = Integer.parseInt(timezoneOffset);
        }
        if (rulesToIgnore != null && rulesToIgnore.length() > 4) {
            this.rulesToIgnore = Utils.getSetofUUIDsFromString(rulesToIgnore);
        }
        if (includeOnlyLatestDates == 1) {
            this.includeOnlyLatestDates = true;
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

    public boolean isIncludeOnlyLatestDates() {
        return includeOnlyLatestDates;
    }

    public void setIncludeOnlyLatestDates(boolean includeOnlyLatestDates) {
        this.includeOnlyLatestDates = includeOnlyLatestDates;
    }

}
