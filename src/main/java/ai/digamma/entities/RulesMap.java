package ai.digamma.entities;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ai.digamma.exceptions.ExceptionMessages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class RulesMap {
    protected List<RulesGroup> rulesGroups;
    protected String rulesFilePath = "/home/anna/time/timeextractor/data/rules.txt";

    public RulesMap(){

        this.rulesGroups = readRules();
    }
    public List<RulesGroup> getRulesGroups(){
        return this.rulesGroups;
    }

    private List<RulesGroup> readRules() {
        {
            List<RulesGroup> rulesGroups = new ArrayList<>();
            try{
                byte[] file = Files.readAllBytes(Paths.get(this.rulesFilePath));
                String fileContent = new String(file);
                try{
                    JSONArray jsonArray = new JSONArray(fileContent);
                    int groupCount = jsonArray.length();
                    for (int i = 0; i < groupCount; i++) {
                        List<Rule> rules = new ArrayList<>();
                        JSONObject groupObject = jsonArray.getJSONObject(i);
                        String groupName = groupObject.get("group").toString();
                        String groupRules = groupObject.get("rules").toString();
                        JSONArray jsonArrayRules = new JSONArray(groupRules);
                        for(int j = 0; j < jsonArrayRules.length(); j++){
                            JSONObject rule = jsonArrayRules.getJSONObject(j);
                            String ruleName = rule.get("rule").toString();
                            String uuid = rule.get("uuid").toString();
                            JSONArray jsonArrayUUIDS = new JSONArray(uuid);
                            List<String> uuids_list = new ArrayList<>();
                            for(int k = 0; k < jsonArrayUUIDS.length(); k++){
                                String str_uuid = jsonArrayUUIDS.getString(k);
                                uuids_list.add(str_uuid);
                            }
                            Rule curr_rule = new Rule(ruleName, uuids_list);
                            rules.add(curr_rule);
                        }
                        RulesGroup group = new RulesGroup(groupName, rules);
                        rulesGroups.add(group);
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
            catch(IOException e) {
                System.out.println("No such file or directory");
            }

           return rulesGroups;
        }
    }

    public RulesGroup getRulesGroup(String groupName){
        for(RulesGroup group : this.rulesGroups){
            if(group.getGroupName().equals(groupName)){
                return group;
            }
        }
        return null;
    }

    private HashMap<String, String> getRulesMap(){
        HashMap<String, String> rules_map;
        List<RulesGroup> groups = this.rulesGroups;
        rules_map = new HashMap<>();
        String curr_rule;
        for (RulesGroup group : groups){
            List<Rule> rules = group.getGroupRules();
            for (Rule rule : rules){
                curr_rule = rule.getRuleName();
                List<String> uuids = rule.getUuidList();
                for (String uuid : uuids){
                    rules_map.put(uuid, curr_rule);
                }
            }
        }
        return rules_map;
     }

     private HashMap<String, String> getRulesGroupMap(){
         HashMap<String, String> rules_group_map;
         List<RulesGroup> groups = this.rulesGroups;
         rules_group_map = new HashMap<>();
         String curr_rule_group, curr_rule;
         for (RulesGroup group : groups){
             curr_rule_group = group.getGroupName();
             List<Rule> rules = group.getGroupRules();
             for (Rule rule : rules){
                 curr_rule = rule.getRuleName();
                 rules_group_map.put(curr_rule, curr_rule_group);
             }
         }
         return rules_group_map;
     }

     public HashMap<String, String> getRuleInfo(String uuid){
         uuid = "6617d13e-f1cb-475e-8d73-a2cf1a42742e";
         HashMap<String, String> rules_map = getRulesMap();
         Set<String> rul = rules_map.keySet();
         String el = rul.iterator().next();
         HashMap<String, String> groups_map = getRulesGroupMap();
         HashMap<String, String> result = new HashMap<>();
         String rule = rules_map.get(uuid);
         String group = groups_map.get(rule);
         result.put("rule", rule);
         result.put("group", group);
         return result;
     }
}