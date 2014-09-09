package com.codeminders.labs.timeextractor.utils;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.Time;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;

public class TemporalObjectGenerator {

	public static Temporal generateTemporalDate(Type type, Date date) {

		Temporal temporal = new Temporal();
		TimeDate start = new TimeDate();
		TimeDate end = new TimeDate();

		start.setDate(date);
		end.setDate(date);

		temporal.setStartDate(start);
		temporal.setStartDate(start);
		temporal.setEndDate(end);
		temporal.setType(Type.DATE);

		return temporal;

	}

	public static Temporal generateTemporalTime(Type type, Time time) {

		Temporal temporal = new Temporal();
		TimeDate start = new TimeDate();
		TimeDate end = new TimeDate();

		start.setTime(time);
		end.setTime(time);

		temporal.setStartDate(start);
		temporal.setStartDate(start);
		temporal.setEndDate(end);
		temporal.setType(Type.TIME);

		return temporal;

	}

	public static Temporal generateTemporalTime(TimeDate start, TimeDate end) {

		Temporal temporal = new Temporal();

		temporal.setStartDate(start);
		temporal.setEndDate(end);
		temporal.setType(Type.DATEINTERVAL);

		return temporal;

	}

}
