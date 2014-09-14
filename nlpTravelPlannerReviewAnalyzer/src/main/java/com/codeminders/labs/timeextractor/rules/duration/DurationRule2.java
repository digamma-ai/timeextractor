package com.codeminders.labs.timeextractor.rules.duration;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalParser;

public class DurationRule2 extends BaseRule {
	private String durationOrder;
	private String durationPeriod;
	private TemporalParser parser;

	public DurationRule2(String durationOrder, String durationPeriod) {
		parser = new TemporalParser();
		this.durationOrder = durationOrder;
		this.durationPeriod = durationPeriod;
	}

	@Override
	public Type getType() {
		return Type.DURATION;
	}

	@Override
	public List<Temporal> getTemporal() {
		int durationLength = 0;
		if ((this.durationOrder) != null) {
			durationLength = TemporalBasicCaseParser
					.getIntFromBasicTerm(this.durationOrder);
		}
		Temporal temporal = parser.getDuration(durationPeriod, durationLength);
		List<Temporal> temporalList = new ArrayList<Temporal>();
		temporalList.add(temporal);
		return temporalList;

	}
}
