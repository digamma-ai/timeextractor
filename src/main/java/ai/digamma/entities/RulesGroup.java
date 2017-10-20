package ai.digamma.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class RulesGroup {
    private String groupName;
    private List<Rule> rules;

    public RulesGroup(String groupName,List<Rule> rules){
        this.groupName = groupName;
        this.rules = rules;
    }

    public String getGroupName(){
        return this.groupName;
    }

    public List<Rule> getGroupRules(){
        return this.rules;
    }
}

