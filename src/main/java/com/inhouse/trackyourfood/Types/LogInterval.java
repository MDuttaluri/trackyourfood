package com.inhouse.trackyourfood.Types;

public enum LogInterval {
    SINGLE(0), DAILY(1), WEEKLY(2), MONTHLY(3), QUATERLY(4), YEARLY(5);

    public final int value;

    private LogInterval(int val) {
        this.value = val;
    }
}
