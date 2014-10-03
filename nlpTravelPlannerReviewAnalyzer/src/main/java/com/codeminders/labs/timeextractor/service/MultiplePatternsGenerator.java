package com.codeminders.labs.timeextractor.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import com.codeminders.labs.timeextractor.entities.Rule;

public class MultiplePatternsGenerator {
    private static RulesFactory factory = new RulesFactory();
    private static String defaultFile = "/factory_EN_US";
    private static String file;
    private TreeSet<Rule> rules;
    private static List<String> ruleClassesNames = new ArrayList<String>();

    public MultiplePatternsGenerator(String file) {
        if (file != null) {
            MultiplePatternsGenerator.setFile(file);
        }
        rules = getRulesFromFile();
    }

    public static TreeSet<Rule> getRulesFromFile() {
        populateRuleClassesFromFile();
        TreeSet<Rule> rules = new TreeSet<Rule>(Collections.reverseOrder());
        for (int i = 0; i < ruleClassesNames.size(); i++) {
            Rule rule = factory.createRule(ruleClassesNames.get(i));
            rules.add(rule);
        }
        return rules;

    }

    private static void populateRuleClassesFromFile() {
        if (file == null) {
            URL url = MultiplePatternsGenerator.class.getResource(defaultFile);
            file = url.getPath();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                ruleClassesNames.add(line);
                line = br.readLine();
            }
        } catch (Exception ex) {
            System.out.println("Logger will be here...");
        }

    }

    public TreeSet<Rule> getRules() {
        return rules;
    }

    public void setRules(TreeSet<Rule> rules) {
        this.rules = rules;
    }

    public String getDefaultFile() {
        return defaultFile;
    }

    public String getFile() {
        return file;
    }

    public static void setFile(String file) {
        MultiplePatternsGenerator.file = file;
    }

}
