package ai.digamma.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import ai.digamma.exceptions.ExceptionMessages;
import org.joda.time.LocalDateTime;

import ai.digamma.utils.Utils;

public class Settings {

    private LocalDateTime date;
    private int timezoneOffset = 0;
    private Set<UUID> rulesToIgnore = new HashSet<>();
    private Set<UUID> rulesToInclude = new HashSet<>();
    private boolean includeRuleIsExist;
    private boolean includeOnlyLatestDates;

    public Settings(){
        this.date = null;
        this.includeOnlyLatestDates = false;
        this.includeRuleIsExist = false;
    }

    public Settings(String date, String timezoneOffset, List<String> rulesToIgnore, List<String> rulesToInclude, int includeOnlyLatestDates) throws Exception {
        // validation comes here
        if(date != null){
            this.date = Utils.convertInputDate(date);
        }
        if (timezoneOffset != null) {
            this.timezoneOffset = Integer.parseInt(timezoneOffset);
        }
        if (rulesToIgnore.size() != 0) {
            this.rulesToIgnore = Utils.getSetofUUIDsFromString(rulesToIgnore);
        }
        if(rulesToInclude.size() != 0){
            this.rulesToInclude = Utils.getSetofUUIDsFromString(rulesToInclude);
            this.includeRuleIsExist = true;
        }
        if (includeOnlyLatestDates == 1) {
            if(this.date!=null) {
                this.includeOnlyLatestDates = true;
            }
            else{
                throw new Exception(ExceptionMessages.LOCAL_DATE_NOT_EXIST);
            }
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

    public Set<UUID> getRulesToIgnore() { return rulesToIgnore; }

    public Set<UUID> getRulesToInclude(){ return rulesToInclude; }

    public boolean isIncludeOnlyLatestDates() {
        return includeOnlyLatestDates;
    }

    public boolean isUserIncludeRuleExist() { return this.includeRuleIsExist; }


}
