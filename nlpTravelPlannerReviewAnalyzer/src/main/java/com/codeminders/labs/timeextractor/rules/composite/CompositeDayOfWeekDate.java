package com.codeminders.labs.timeextractor.rules.composite;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.service.SUTimeService;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class CompositeDayOfWeekDate extends BaseRule {
	private SUTimeService service;
	private Temporal temporal;

	{
		service = new SUTimeService();
	}

	public CompositeDayOfWeekDate(String dayOfWeek, String date) {
		List<TemporalExtraction> one = service.extractDatesAndTimeFromText(
				dayOfWeek, null);
		List<TemporalExtraction> two = service.extractDatesAndTimeFromText(
				date, null);

		Temporal dayOfWeekTemporal = one.get(0).getTemporal().get(0);
		Temporal dateTemporal = two.get(0).getTemporal().get(0);

		dateTemporal
				.getStartDate()
				.getDate()
				.setDayOfWeek(
						dayOfWeekTemporal.getStartDate().getDate()
								.getDayOfWeek());
		dateTemporal
				.getEndDate()
				.getDate()
				.setDayOfWeek(
						dayOfWeekTemporal.getEndDate().getDate().getDayOfWeek());

		this.temporal = dateTemporal;

	}

	@Override
	public Type getType() {
		return Type.DATE;
	}

	@Override
	public List<Temporal> getTemporal() {
		List<Temporal> temporalList = new ArrayList<Temporal>();
		temporalList.add(temporal);
		return temporalList;
	}
}