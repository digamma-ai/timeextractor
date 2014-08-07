package com.codeminders.labs.timeextractor.entities;

import java.util.Locale;

public class AnnotationInterval {

	private int from;
	private int to;
	private double confidence;
	private Locale locale;

	public AnnotationInterval() {
	}

	public AnnotationInterval(int from, int to, String value, Locale locale) {
		super();
		this.from = from;
		this.to = to;
		this.locale = locale;
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

}
