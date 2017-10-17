package ai.digamma.entities;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Type;

public abstract class Rule implements Comparable<Rule> {

    protected String rule;
    protected int priority;
    protected double confidence;
    protected Locale locale = Locale.US;
    protected Type type;
    protected UUID id;
    private String example;

    public abstract Type getType();

    public abstract List<Temporal> getTemporal(String text);

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((locale == null) ? 0 : locale.hashCode());
        result = prime * result + priority;
        result = prime * result + ((rule == null) ? 0 : rule.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rule other = (Rule) obj;
        if (locale == null) {
            if (other.locale != null)
                return false;
        } else if (!locale.equals(other.locale))
            return false;
        if (priority != other.priority)
            return false;
        if (rule == null) {
            if (other.rule != null)
                return false;
        } else if (!rule.equals(other.rule))
            return false;
        return true;
    }

    public int compare(Rule rule1, Rule rule2) {
        int a = rule1.getPriority();
        int b = rule2.getPriority();
        int cmp = a > b ? +1 : a < b ? -1 : 0;
        if (cmp == 0) {
            return 1;
        }
        return cmp;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public void setType(Type type) {
        this.type = type;
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

}
