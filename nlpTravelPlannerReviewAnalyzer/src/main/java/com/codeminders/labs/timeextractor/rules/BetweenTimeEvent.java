package com.codeminders.labs.timeextractor.rules;

import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

import edu.stanford.nlp.time.SUTime.TimexType;

public class BetweenTimeEvent extends BaseRule {
	private static final long serialVersionUID = 1;
	String values;

	public BetweenTimeEvent(String label, String a) {
		System.out.println(label + " " + a);
		this.values = label;
	}

	public BetweenTimeEvent(String label) {
		System.out.println(label);
		this.values = label;
	}

	public TimexType getTimexType() {
		return TimexType.DURATION;
	}

	public org.joda.time.Interval getJodaTimeInterval() {
		return null;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Temporal> getTemporal() {
		// TODO Auto-generated method stub
		return null;
	}

}
