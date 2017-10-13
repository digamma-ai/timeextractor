package com.codeminders.labs.timeextractor.entities;

import java.util.List;

import com.codeminders.labs.timeextractor.temporal.entities.Temporal;

public class RegexResult {

    private String text;
    private Rule rule;
    private int start;
    private int end;
    private String ruleName;
    private List<Temporal> temporal;

    public RegexResult(String text, Rule rule, String ruleName, int start, int end) {
        super();
        this.text = text;
        this.rule = rule;
        this.ruleName = ruleName;
        this.start = start;
        this.end = end;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public List<Temporal> getTemporal() {
        return temporal;
    }

    public void setTemporal(List<Temporal> temporal) {
        this.temporal = temporal;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    @Override
    public String toString() {
        return "RegexResult [text=" + text + ", rule=" + rule + ", start=" + start + ", end=" + end + "]";
    }

}
