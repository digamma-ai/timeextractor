package com.codeminders.labs.timeextractor.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import com.codeminders.labs.timeextractor.dto.DTORule;
import com.codeminders.labs.timeextractor.entities.Rule;

public class GetRulesService {

    public Map<String, TreeSet<DTORule>> getAllAvailableRules() {
        TreeSet<Rule> rules = MultipleExtractionService.getGenerator().getRules();
        return convertRuleToDto(rules);
    }

    private Map<String, TreeSet<DTORule>> convertRuleToDto(TreeSet<Rule> rules) {
        Map<String, TreeSet<DTORule>> map = new HashMap<String, TreeSet<DTORule>>();
        TreeSet<String> rulePackages = new TreeSet<String>();
        Iterator<Rule> itr = rules.iterator();
        while (itr.hasNext()) {
            Rule rule = itr.next();
            DTORule dtoRule = new DTORule(rule);
            String packageName = rule.getClass().getPackage().getName();
            if (rulePackages.contains(packageName)) {
                TreeSet<DTORule> dtoRules = map.get(packageName);
                dtoRules.add(dtoRule);
            } else {
                TreeSet<DTORule> dtoRules = new TreeSet<DTORule>();
                dtoRules.add(dtoRule);
                rulePackages.add(packageName);
                map.put(packageName, dtoRules);
            }
        }
        return map;

    }
}
