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
                            List<String> uuids = Arrays.asList(rule.get("uuid").toString().split(","));
                            Rule curr_rule = new Rule(ruleName, uuids);
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
}