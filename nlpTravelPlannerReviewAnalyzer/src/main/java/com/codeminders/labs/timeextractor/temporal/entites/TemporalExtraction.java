package com.codeminders.labs.timeextractor.temporal.entites;

/* Received temporal expressions class */

public class TemporalExtraction {

	public TemporalExtraction() {
	}

	public TemporalExtraction(String temporalExpression, int fromPosition,
			int toPosition, String classOfRuleType) {
		this.temporalExpression = temporalExpression;
		this.fromPosition = fromPosition;
		this.toPosition = toPosition;
		this.classOfRuleType = classOfRuleType;
	}

	private String temporalExpression;
	private int fromPosition;
	private int toPosition;
	private String classOfRuleType;

	public String getTemporalExpression() {
		return temporalExpression;
	}

	public void setTemporalExpression(String temporalExpression) {
		this.temporalExpression = temporalExpression;
	}

	public int getFromPosition() {
		return fromPosition;
	}

	public void setFromPosition(int fromPosition) {
		this.fromPosition = fromPosition;
	}

	public int getToPosition() {
		return toPosition;
	}

	public void setToPosition(int toPosition) {
		this.toPosition = toPosition;
	}

	public String getClassOfRuleType() {
		return classOfRuleType;
	}

	public void setClassOfRuleType(String classOfRuleType) {
		this.classOfRuleType = classOfRuleType;
	}

	@Override
	public String toString() {
		return temporalExpression;
	}

}
