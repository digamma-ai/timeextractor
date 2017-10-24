package ai.digamma.entities;

import java.io.IOException;
import java.util.*;

import ai.digamma.exceptions.ExceptionMessages;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class RulesMap {
    protected List<RulesGroup> rulesGroups;
    protected InputStream in = String.class.getResourceAsStream("/rule.json");

    public RulesMap() {
        this.rulesGroups = readRules();
    }


    private List<RulesGroup> readRules() {
        Gson gson = new Gson();
        RulesGroup[] groups = gson.fromJson(new InputStreamReader(this.in), RulesGroup[].class);
        List<RulesGroup> groups_list = Arrays.asList(groups);
        return groups_list;
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


    public Map<String,String> getRuleGRoupByUUID(String uuid) {
        Map<String, String> ruleGroupMap = new HashMap<>();
        List<RulesGroup> groups = this.rulesGroups;
        for (RulesGroup group : groups) {
            {
                List<Rule> rules = group.getGroupRules();
                for (Rule rule : rules) {
                    List<String> curr_uuids = rule.getUuidList();
                    for (String curr_uuid : curr_uuids) {
                        if (uuid.equals(curr_uuid)){
                            ruleGroupMap.put("group", group.getGroupName());
                            ruleGroupMap.put("rule", rule.getRuleName());
                            break;
                        }
                    }
                }
            }
        }
        return ruleGroupMap;
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

    public static void main(String[] args) throws Exception {
        RulesMap r = new RulesMap();
        System.out.println(r.rulesGroups);

    }
}
