package ai.digamma.dto;

import java.util.UUID;

import ai.digamma.entities.ExtractionRule;

public class DTORule implements Comparable<DTORule> {
    private UUID id;
    private String example;
    private String name;
    private double confidencce;

    public DTORule(ExtractionRule rule) {
        this.id = rule.getId();
        this.example = rule.getExample();
        this.name = rule.getClass().getSimpleName();
        this.confidencce = rule.getConfidence();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConfidencce() {
        return confidencce;
    }

    public void setConfidencce(double confidencce) {
        this.confidencce = confidencce;
    }

    @Override
    public int compareTo(DTORule rule) {
        int compare = this.getName().compareTo(rule.getName());
        if (compare == 0) {
            return this.id.compareTo(rule.getId());
        }
        return compare;

    }
}
