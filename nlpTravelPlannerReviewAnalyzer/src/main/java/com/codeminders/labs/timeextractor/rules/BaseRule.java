package com.codeminders.labs.timeextractor.rules;

import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public abstract class BaseRule {

    protected double confidence;
    protected Locale locale = Locale.US;
    protected Type type;

    public abstract Type getType();

    public abstract List<Temporal> getTemporal();

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
