package ai.digamma.entities;

import java.io.IOException;
import java.util.*;

import ai.digamma.exceptions.ExceptionMessages;
import com.google.gson.Gson;

import java.io.FileReader;
import java.util.ArrayList;


public class RulesMap {
    protected List<RulesGroup> rulesGroups;
    protected String rulesFilePath = String.class.getResource("/rule.json").getPath();

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
            throw new RuntimeException("Library is broken", e);
        }
    }

    public List<String> getGroupUUID(String str_group) throws Exception{
        List<RulesGroup> groups = this.rulesGroups;
        List<String> uuids = new ArrayList<>();
        boolean isExist = false;
        for (RulesGroup group : groups) {
            if (group.getGroupName().equals(str_group)) {
                isExist = true;
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
        if(!isExist) { throw new Exception(ExceptionMessages.GROUP_NOT_FOUND); }
        return uuids;
    }

    public List<String> getRuleUUID(String str_rule) throws Exception{
        List<RulesGroup> groups = this.rulesGroups;
        List<String> uuids = new ArrayList<>();
        boolean isExist = false;
        for (RulesGroup group : groups) { {
                List<Rule> rules = group.getGroupRules();
                for (Rule rule : rules){
                    if (rule.getRuleName().equals(str_rule)) {
                        List<String> curr_uuids = rule.getUuidList();
                        for (String uuid : curr_uuids) {
                            uuids.add(uuid);
                        }
                        isExist = true;
                        break;
                    }
                }
            }
        }
        if(!isExist) { throw new Exception(ExceptionMessages.RULE_NOT_FOUND); }
        return uuids;
    }
}
