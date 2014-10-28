package com.codeminders.labs.timeextractor.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.codeminders.labs.timeextractor.entities.Rule;

/**
 * <h1>Multiple Patterns Generator Class</h1> is designed to upload in memory
 * all available rules from file and return TreeSet<Rule> of all available rule
 * objects
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-28
 */

public class MultiplePatternsGenerator {
    private static final Logger logger = Logger.getLogger(TemporalExtractionService.class);

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
        } catch (FileNotFoundException ex) {
            logger.error("File not found, provide the right file location", ex);
        } catch (IOException ex) {
            logger.error("File is wrongly specified: each line of file should contain exactly one class, class package should be specified as well. ", ex);
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
