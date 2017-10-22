package ai.digamma.entities;

import java.io.IOException;
import java.util.*;
import com.google.gson.Gson;
import java.io.FileReader;
import java.util.ArrayList;


public class RulesMap {
    protected List<RulesGroup> rulesGroups;
    protected String rulesFilePath = "/home/anna/time/timeextractor/src/main/resources/rule.json";

    public RulesMap() {
        this.rulesGroups = readRules();
    }


    private List<RulesGroup> readRules() {
        Gson gson = new Gson();
        try {
            RulesGroup[] groups = gson.fromJson(new FileReader(this.rulesFilePath), RulesGroup[].class);
            List<RulesGroup> groups_list = Arrays.asList(groups);
            return groups_list;
        }
        catch(IOException e){
            throw new RuntimeException("", e);
        }
    }

    public RulesGroup getRulesGroup(String groupName) {
        for (RulesGroup group : this.rulesGroups) {
            if (group.getGroupName().equals(groupName)) {
                return group;
            }
        }
        return null;
    }


    public List<String> getGroupUUID(String str_group){
        List<RulesGroup> groups = this.rulesGroups;
        List<String> uuids = new ArrayList<>();
        for (RulesGroup group : groups) {
            if (group.getGroupName().equals(str_group)) {
                List<Rule> rules = group.getGroupRules();
                for (Rule rule : rules){
                    List<String> curr_uuids = rule.getUuidList();
                    for (String uuid : curr_uuids){
                        uuids.add(uuid);
                    }
                }
                break;
            }
        }
        return uuids;
    }

    public List<String> getRuleUUID(String str_rule){
        List<RulesGroup> groups = this.rulesGroups;
        List<String> uuids = new ArrayList<>();
        for (RulesGroup group : groups) { {
                List<Rule> rules = group.getGroupRules();
                for (Rule rule : rules){
                    if (rule.getRuleName().equals(str_rule)) {
                        List<String> curr_uuids = rule.getUuidList();
                        for (String uuid : curr_uuids) {
                            uuids.add(uuid);
                        }
                        break;
                    }
                }
            }
        }
        return uuids;
    }
}
