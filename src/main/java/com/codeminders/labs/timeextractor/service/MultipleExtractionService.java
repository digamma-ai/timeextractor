package com.codeminders.labs.timeextractor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codeminders.labs.timeextractor.entities.RegexResult;
import com.codeminders.labs.timeextractor.entities.Rule;

/**
 * <h1>Multiple Extraction Service Class</h1> is designed to extract information
 * from texts by specified regex patterns with respect to their priority levels.
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-28
 */
public class MultipleExtractionService {

    private static MultiplePatternsGenerator generator;

    public MultipleExtractionService(String file) {
        generator = new MultiplePatternsGenerator(file);
    }

    public List<RegexResult> getTemporals(String text) {
        TreeSet<Rule> rules = generator.getRules();
        List<RegexResult> results = new ArrayList<RegexResult>();
        for (Rule rule : rules) {
            Pattern p = Pattern.compile(rule.getRule(), Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(text);
            while (m.find()) {
                boolean overlap = false;
                String temporal = m.group();
                int start = m.start();
                int end = m.end();
                String ruleName = rule.getClass().getSimpleName();
                RegexResult result = new RegexResult(temporal, rule, ruleName, start, end);
                if (results.size() == 0) {
                    results.add(result);
                    continue;
                }

                for (int i = 0; i < results.size(); i++) {
                    if (overlap(result, results.get(i))) {
                        overlap = true;
                    }
                }
                if (!overlap) {
                    results.add(result);
                }
            }
        }

        return results;
    }

    /**
     * Method checks if two results overlap
     * 
     * @param RegexResult
     *            result1
     * @param RegexResult
     *            result2
     * @return boolean
     */
    private boolean overlap(RegexResult result1, RegexResult result2) {
        return Math.max(result1.getStart(), result2.getStart()) <= Math.min(result1.getEnd(), result2.getEnd());
    }

    public static MultiplePatternsGenerator getGenerator() {
        return generator;
    }

}
