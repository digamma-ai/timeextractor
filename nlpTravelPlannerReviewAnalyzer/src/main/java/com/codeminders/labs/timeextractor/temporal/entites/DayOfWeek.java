package com.codeminders.labs.timeextractor.temporal.entites;

public enum DayOfWeek {
	SU(1), MO(2), TU(3), WE(4), TH(5), FR(6), SA(7);

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
