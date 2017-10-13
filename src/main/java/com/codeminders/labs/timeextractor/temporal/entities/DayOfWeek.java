package com.codeminders.labs.timeextractor.temporal.entities;

public enum DayOfWeek {
    SU(7), MO(1), TU(2), WE(3), TH(4), FR(5), SA(6);

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
