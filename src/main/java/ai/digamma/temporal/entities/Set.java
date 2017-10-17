package ai.digamma.temporal.entities;

import java.util.List;

public class Set {

	private Frequency frequency;
	private DaysOfRepetition daysOfRepetition;
	private List<DayOfWeek> byDay;
	private int interval;

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public DaysOfRepetition getDaysOfRepetition() {
		return daysOfRepetition;
	}

	public void setDaysOfRepetition(DaysOfRepetition daysOfRepetition) {
		this.daysOfRepetition = daysOfRepetition;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public List<DayOfWeek> getByDay() {
		return byDay;
	}

	public void setByDay(List<DayOfWeek> byDay) {
		this.byDay = byDay;
	}

	@Override
	public String toString() {
		return "Set [frequency=" + frequency + ", daysOfRepetition="
				+ daysOfRepetition + ", interval=" + interval + "]";
	}

}
