package com.codeminders.labs.timeextractor.utils;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;

public class TemporalObjectGenerator {

	public static Temporal generateTemporalObject(Type type, Date date) {

		Temporal temporal = new Temporal();
		TimeDate timeDate = new TimeDate();
		timeDate.setDate(date);
		temporal.setStartDate(timeDate);
		temporal.setEndDate(timeDate);
		temporal.setType(Type.DATE);

		return temporal;

	}

}
