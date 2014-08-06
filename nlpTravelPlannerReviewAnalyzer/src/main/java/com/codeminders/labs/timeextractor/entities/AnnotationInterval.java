package com.codeminders.labs.timeextractor.entities;

public class AnnotationInterval {

	private int from;
	private int to;
	private String value;
	private double confidence;

	public AnnotationInterval() {
	}

	public AnnotationInterval(int from, int to, String value) {
		super();
		this.from = from;
		this.to = to;
		this.value = value;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

}
