package com.codeminders.labs.timeextractor.entities;

import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.dto.DTOTemporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;

public class AnnotationIntervalHtml extends AnnotationInterval implements Comparable<AnnotationIntervalHtml> {

    private int htmlTagFrom;
    private int htmlTagTo;
    private List<DTOTemporal> extractedTemporal;
    private String tag;
    private Locale locale;
    private double confidence;
    private Type temporalType;

    public int getHtmlTagFrom() {
        return htmlTagFrom;
    }

    public void setHtmlTagFrom(int htmlTagFrom) {
        this.htmlTagFrom = htmlTagFrom;
    }

    public int getHtmlTagTo() {
        return htmlTagTo;
    }

    public void setHtmlTagTo(int htmlTagTo) {
        this.htmlTagTo = htmlTagTo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<DTOTemporal> getExtractedTemporal() {
        return extractedTemporal;
    }

    public void setExtractedTemporal(List<DTOTemporal> extractedTemporal) {
        this.extractedTemporal = extractedTemporal;
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

    public Type getTemporalType() {
        return temporalType;
    }

    public void setTemporalType(Type temporalType) {
        this.temporalType = temporalType;
    }

    @Override
    public String toString() {
        return "AnnotationIntervalHtml [htmlTagFrom=" + htmlTagFrom + ", htmlTagTo=" + htmlTagTo + " extractedTemporal=" + extractedTemporal + ", tag=" + tag + ", locale=" + locale + ", confidence="
                + confidence + ", temporalType=" + temporalType + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(confidence);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((extractedTemporal == null) ? 0 : extractedTemporal.hashCode());
        result = prime * result + htmlTagFrom;
        result = prime * result + htmlTagTo;
        result = prime * result + ((locale == null) ? 0 : locale.hashCode());
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        result = prime * result + ((temporalType == null) ? 0 : temporalType.hashCode());
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
        AnnotationIntervalHtml other = (AnnotationIntervalHtml) obj;
        if (Double.doubleToLongBits(confidence) != Double.doubleToLongBits(other.confidence))
            return false;
        if (extractedTemporal == null) {
            if (other.extractedTemporal != null)
                return false;
        } else if (!extractedTemporal.equals(other.extractedTemporal))
            return false;
        if (htmlTagFrom != other.htmlTagFrom)
            return false;
        if (htmlTagTo != other.htmlTagTo)
            return false;
        if (locale == null) {
            if (other.locale != null)
                return false;
        } else if (!locale.equals(other.locale))
            return false;
        if (tag == null) {
            if (other.tag != null)
                return false;
        } else if (!tag.equals(other.tag))
            return false;
        if (temporalType != other.temporalType)
            return false;
        return true;
    }

    public int compare(AnnotationIntervalHtml ext1, AnnotationIntervalHtml ext2) {
        if (this.equals(ext2)) {
            return 1;
        }
        int a = ext1.getHtmlTagFrom();
        int b = ext2.getHtmlTagFrom();
        int cmp = a > b ? +1 : a < b ? -1 : 0;
        if (cmp == 0) {
            return 1;
        }
        return cmp;
    }

    @Override
    public int compareTo(AnnotationIntervalHtml o) {
        return compare(this, o);
    }

}
