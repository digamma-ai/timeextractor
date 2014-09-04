package com.codeminders.labs.timeextractor.constants;

public enum DayOfWeek {
	SUNDAY(1), MONDAY(2), TUESDAY(3), WEDNESDAY(4), THURSDAY(5), FRIDAY(6), SATURDAY(
			7);

	private int value;

	private DayOfWeek(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static DayOfWeek getByValue(int n) {
		DayOfWeek[] values = DayOfWeek.values();
		for (DayOfWeek value : values) {
			if (n == value.getValue()) {
				return value;
			}
		}
		return null;

	}
}
