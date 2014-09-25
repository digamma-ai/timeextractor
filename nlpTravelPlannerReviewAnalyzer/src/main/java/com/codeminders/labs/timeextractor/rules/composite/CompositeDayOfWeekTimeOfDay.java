package com.codeminders.labs.timeextractor.rules.composite;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.service.TemporalExtractionService;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entites.Type;

// Friday night, Tuesday morning

public class CompositeDayOfWeekTimeOfDay extends BaseRule {
	private TemporalExtractionService service = new TemporalExtractionService();
	private Temporal temporal;
    private double confidence = 0.9;

	public CompositeDayOfWeekTimeOfDay(ArrayList<String> dayofWeekList,
			ArrayList<String> timeOfDayList) {
		String dayOfWeek = "";
		String timeOfDay = "";

		for (String dateParts : dayofWeekList) {
			dayOfWeek = dayOfWeek + dateParts + " ";
		}
		for (String dateParts : timeOfDayList) {
			timeOfDay = timeOfDay + dateParts + " ";
		}
		List<TemporalExtraction> one = service.extractDatesAndTimeFromText(
				dayOfWeek, null);
		List<TemporalExtraction> two = service.extractDatesAndTimeFromText(
				timeOfDay, null);

		Temporal dayOfWeekTemporal = one.get(0).getTemporal().get(0);
		Temporal timeOfDayTemporal = two.get(0).getTemporal().get(0);

		timeOfDayTemporal.getStartDate().setDate(
				dayOfWeekTemporal.getStartDate().getDate());
		timeOfDayTemporal.getEndDate().setDate(
				dayOfWeekTemporal.getEndDate().getDate());

		this.temporal = timeOfDayTemporal;

	}

	public CompositeDayOfWeekTimeOfDay(String dayOfWeek, String timeOfDay) {
		System.out.println(dayOfWeek + " " + timeOfDay);

		List<TemporalExtraction> one = service.extractDatesAndTimeFromText(
				dayOfWeek, null);
		List<TemporalExtraction> two = service.extractDatesAndTimeFromText(
				timeOfDay, null);

		Temporal dayOfWeekTemporal = one.get(0).getTemporal().get(0);
		Temporal timeOfDayTemporal = two.get(0).getTemporal().get(0);

		timeOfDayTemporal.getStartDate().setDate(
				dayOfWeekTemporal.getStartDate().getDate());
		timeOfDayTemporal.getEndDate().setDate(
				dayOfWeekTemporal.getEndDate().getDate());

		this.temporal = timeOfDayTemporal;

	}

	@Override
	public Type getType() {
		return Type.DATE_TIME_INTERVAL;
	}

	@Override
	public List<Temporal> getTemporal() {
		List<Temporal> temporalList = new ArrayList<Temporal>();
		temporalList.add(temporal);
		return temporalList;
	}
	
    @Override
    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

}
