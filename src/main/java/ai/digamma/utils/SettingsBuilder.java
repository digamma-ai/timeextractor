package ai.digamma.utils;

import ai.digamma.entities.RulesMap;
import ai.digamma.entities.Settings;
import ai.digamma.exceptions.ExceptionMessages;

import java.util.ArrayList;
import java.util.List;

public class SettingsBuilder {
    private List<String> includeGroups;
    private List<String> excludeRules;
    private String date;
    private String timeZoneOffset;
    private int includeOnlyLatestDate = 0;

    public SettingsBuilder(){
        this.includeGroups = new ArrayList<>();
        this.excludeRules = new ArrayList<>();
    }

    public SettingsBuilder addRulesGroup(String group){
        this.includeGroups.add(group);
        return this;
    }

    public SettingsBuilder excludeRules(String rule){
        this.excludeRules.add(rule);
        return this;
    }

    public SettingsBuilder addUserDate(String date){
        this.date = date;
        return this;
    }

    public SettingsBuilder addTimeZoneOffset(String offset){
        this.timeZoneOffset = offset;
        return this;
    }

    public SettingsBuilder includeOnlyLatestDates(boolean include){
        if(include) {
            this.includeOnlyLatestDate = 1;
        }
        return this;
    }

    public Settings build() throws Exception{
        boolean include = !includeGroups.isEmpty();
        boolean exclude = !excludeRules.isEmpty();
        List<String> includeUuids = new ArrayList<>();
        List<String> excludeUuids = new ArrayList<>();
        if(include || exclude){
            RulesMap map = new RulesMap();
            if(!(includeGroups.isEmpty())){
                for (String group : this.includeGroups){
                        List<String> curr_uuids = map.getGroupUUID(group);
                        for (String uuid : curr_uuids) {
                            includeUuids.add(uuid);
                        }
                }
            }
            if(!(excludeRules.isEmpty())){
                for (String rule : this.excludeRules){
                        List<String> curr_uuids = map.getRuleUUID(rule);
                        for (String uuid : curr_uuids) {
                            excludeUuids.add(uuid);
                        }
                }
            }
        }
        
        Settings settings = new Settings(this.date, this.timeZoneOffset, excludeUuids, includeUuids, this.includeOnlyLatestDate);
        return settings;
    }
}
