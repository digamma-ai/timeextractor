package com.codeminders.labs.timeextractor.entities;

import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.dto.DTOTemporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;

public class AnnotationIntervalHtml extends AnnotationInterval {

    private List<DTOTemporal> extractedTemporal;
    private String temporalId;
    private Locale locale;
    private double confidence;
    private Type temporalType;

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

    public String getTemporalId() {
        return temporalId;
    }

    public void setTemporalId(String temporalId) {
        this.temporalId = temporalId;
    }

    @Override
    public int hashCode() {
        final int prime = 1;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(confidence);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((extractedTemporal == null) ? 0 : extractedTemporal.hashCode());
        result = prime * result + ((locale == null) ? 0 : locale.hashCode());
        result = prime * result + ((temporalId == null) ? 0 : temporalId.hashCode());
        result = prime * result + ((temporalType == null) ? 0 : temporalType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
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
        if (locale == null) {
            if (other.locale != null)
                return false;
        } else if (!locale.equals(other.locale))
            return false;
        if (temporalId == null) {
            if (other.temporalId != null)
                return false;
        } else if (!temporalId.equals(other.temporalId))
            return false;
        if (temporalType != other.temporalType)
            return false;
        return true;
    }

    public int compare(AnnotationIntervalHtml ext1, AnnotationIntervalHtml ext2) {
        if (this.equals(ext2)) {
            return 1;
        }
        String a = ext1.getTemporalId();
        String b = ext2.getTemporalId();

        return a.compareTo(b);
    }

}
