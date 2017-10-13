package com.codeminders.labs.timeextractor.entities;

import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.dto.DTOTemporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;

public class AnnotationInterval implements Comparable<AnnotationInterval> {

    protected int from;
    protected int to;
    protected double confidence;
    protected Locale locale;
    protected List<DTOTemporal> extractedTemporal;
    protected Type temporalType;

    public AnnotationInterval() {
    }

    public AnnotationInterval(int from, int to, Locale locale, List<DTOTemporal> extractedTemporal) {
        this.from = from;
        this.to = to;
        this.locale = locale;
        this.extractedTemporal = extractedTemporal;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

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

    public List<DTOTemporal> getExtractedTemporal() {
        return extractedTemporal;
    }

    public void setExtractedTemporal(List<DTOTemporal> extractedTemporal) {
        this.extractedTemporal = extractedTemporal;
    }

    public Type getTemporalType() {
        return temporalType;
    }

    public void setTemporalType(Type temporalType) {
        this.temporalType = temporalType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(confidence);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((extractedTemporal == null) ? 0 : extractedTemporal.hashCode());
        result = prime * result + from;
        result = prime * result + ((locale == null) ? 0 : locale.hashCode());
        result = prime * result + ((temporalType == null) ? 0 : temporalType.hashCode());
        result = prime * result + to;
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
        AnnotationInterval other = (AnnotationInterval) obj;
        if (Double.doubleToLongBits(confidence) != Double.doubleToLongBits(other.confidence))
            return false;
        if (extractedTemporal == null) {
            if (other.extractedTemporal != null)
                return false;
        } else if (!extractedTemporal.equals(other.extractedTemporal))
            return false;
        if (from != other.from)
            return false;
        if (locale == null) {
            if (other.locale != null)
                return false;
        } else if (!locale.equals(other.locale))
            return false;
        if (temporalType != other.temporalType)
            return false;
        if (to != other.to)
            return false;
        return true;
    }

    public int compareTo(AnnotationInterval o) {
        if (this.equals(o)) {
            return 0;
        } else {
            return 1;
        }
    }

}
