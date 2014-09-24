package com.codeminders.labs.timeextractor.entities;

import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.dto.DTOTemporal;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.Type;

public class AnnotationIntervalHtml extends AnnotationInterval {

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
		return "AnnotationIntervalHtml [htmlTagFrom=" + htmlTagFrom
				+ ", htmlTagTo=" + htmlTagTo + " extractedTemporal="
				+ extractedTemporal + ", tag=" + tag + ", locale=" + locale
				+ ", confidence=" + confidence + ", temporalType="
				+ temporalType + "]";
	}

}
