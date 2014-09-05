package com.codeminders.labs.timeextractor.constants;

public enum WeekOfMonth {
    FIRST(1), SECOND(2), THIRD(3), FOURTH(4), FIFTH(5), LAST(6);

    private int value;

    private WeekOfMonth(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
