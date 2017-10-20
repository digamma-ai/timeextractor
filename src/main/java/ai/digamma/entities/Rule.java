package ai.digamma.entities;

import java.util.List;

public class Rule {
    private String ruleName;
    private List<String> uuidList;

    public Rule(String ruleName, List<String> uuidList){
        this.ruleName = ruleName;
        this.uuidList = uuidList;
    }

    public List<String> getUuidList(){
        return this.uuidList;
    }

    public String getRuleName(){
        return this.ruleName;
    }

}
