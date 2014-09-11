package com.codeminders.labs.timeextractor.rules.composite;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

import edu.stanford.nlp.util.CoreMap;

// Friday night, Tuesday morning

public class CompositeDayOfWeekTimeOfDay extends BaseRule {

	public CompositeDayOfWeekTimeOfDay(ArrayList<CoreMap> dayOfWeek,
			ArrayList<CoreMap> timeOfDay) {

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
