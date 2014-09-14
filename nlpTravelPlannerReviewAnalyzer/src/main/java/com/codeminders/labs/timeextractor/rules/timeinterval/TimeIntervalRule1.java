package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.Time;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entites.TimeTag;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class TimeIntervalRule1 extends BaseRule {
	private TemporalBasicCaseParser parser;
	private String time;
	private String timeZone;
	private String tag;

	public TimeIntervalRule1(String tag, String time, String timeZone) {
		parser = new TemporalBasicCaseParser();
		this.tag = tag;
		this.time = time;
		this.timeZone = timeZone;
	}

	@Override
	public Type getType() {
		return Type.TIMEINTERVAL;
	}

	@Override
	public List<Temporal> getTemporal() {
		TimeDate start = new TimeDate();
		TimeDate end = new TimeDate();
		Time time = new Time();
		Temporal temporal = null;
		int timezone = 0;
		if (this.timeZone != null) {
			timezone = parser.getTimeZone(this.timeZone);
			time.setTimezoneOffset(timezone);
		}
		if (this.time != null) {
			time.setHours(Integer.parseInt(this.time));
		}
		TimeTag tag = TemporalBasicCaseParser.getTimeTag(this.tag);
		if (tag == tag.BEFORE) {
			start.setTime(time);
			temporal = TemporalObjectGenerator.generateTemporalTime(
					Type.TIMEINTERVAL, start, null);

		} else {
			end.setTime(time);
			temporal = TemporalObjectGenerator.generateTemporalTime(
					Type.TIMEINTERVAL, null, end);

		}
		List<Temporal> temporalList = new ArrayList<Temporal>();
		temporalList.add(temporal);
		return temporalList;
	}

}
